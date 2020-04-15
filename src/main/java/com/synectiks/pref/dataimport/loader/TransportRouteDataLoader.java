package com.synectiks.pref.dataimport.loader;

import com.synectiks.pref.PreferencesApp;
import com.synectiks.pref.config.ApplicationProperties;
import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.ExceptionRecord;
import com.synectiks.pref.domain.enumeration.RouteFrequency;
import com.synectiks.pref.domain.vo.CmsTransportVo;
import com.synectiks.pref.exceptions.DuplicateRecordFoundException;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.service.util.CommonUtil;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TransportRouteDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public TransportRouteDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, DuplicateRecordFoundException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        CmsTransportVo obj = CommonUtil.createCopyProperties(cls.newInstance(), CmsTransportVo.class);

        String routeName = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(routeName)) {
            sb.append("route_name, ");
            logger.warn("Mandatory field missing. Field name - route_name");
        } else {
            obj.setRouteName(routeName);
        }

        String routeDetails = row.getCellAsString(1).orElse(null);
        obj.setRouteDetails(routeDetails);

        String routeMapUrl = row.getCellAsString(2).orElse(null);
        obj.setRouteMapUrl(routeMapUrl);

        String routeFrequency = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(routeFrequency)) {
            sb.append("route_frequency, ");
            logger.warn("Mandatory field missing. Field name - route_frequency");
        }else {
            obj.setRouteFrequency(routeFrequency);
        }
        return (T)obj;
    }


    public <T> void saveCmsData(ReadableWorkbook wb, Class<T> cls) {
        logger.debug(String.format("Saving %s data started.",this.sheetName));

        Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
        try {
//			T instance = cls.newInstance();
            ApplicationProperties applicationProperties = PreferencesApp.getBean(ApplicationProperties.class);
            RestTemplate restTemplate = PreferencesApp.getBean(RestTemplate.class);

            try (Stream<Row> rows = sheet.openStream()) {
                List<T> list = new ArrayList<>();
                List<ExceptionRecord> exceptionList = new ArrayList<>();
                StringBuilder sb = new StringBuilder(String.format("\nInvalid records found for table - %s: \n", this.sheetName));
                sb.append("Rows having invalid records\n");
                rows.forEach(row -> {

                    if (list.size() == CmsConstants.BATCH_SIZE) {
                        List expList = postTransportData(list, applicationProperties, restTemplate);
                        exceptionList.addAll(expList);
                        list.clear();
                    }
                    if (exceptionList.size() == CmsConstants.BATCH_SIZE) {
                        allRepositories.findRepository("exception_record").saveAll(exceptionList);
                        exceptionList.clear();
                    }

                    // Skip first header row
                    if (row.getRowNum() > 1) {
                        try {
                            T obj = getObject(row, cls);
                            if(obj != null) {
                                list.add(obj);
                            }
                        } catch (InstantiationException | IllegalAccessException e) {
                            logger.error("Exception in loading data from excel file :"+e.getMessage(), e);
                        } catch(Exception e) {
                            ExceptionRecord expObj = getExceptionObject(row, e);
                            sb.append(String.format("%s : %s  , %s\n", e.getClass().getSimpleName(), e.getMessage(), row.toString()));
                            if(expObj != null) {
                                exceptionList.add(expObj);
                            }
                        }
                    }
                });
                // Save remaining items
                List expList = postTransportData(list, applicationProperties, restTemplate);
                list.clear();
                exceptionList.addAll(expList);
                if(exceptionList.size() > 0) {
                    logger.warn(sb.toString());
                    logger.info("Saving records having exceptions/errors in exception_record table");
                    allRepositories.findRepository("exception_record").saveAll(exceptionList);
                }
                exceptionList.clear();
            }
        } catch (Exception e) {
            logger.error(String.format("Failed to iterate %s sheet rows ", this.sheetName), e);
        }
        logger.debug(String.format("Saving %s data completed.", this.sheetName));
    }

    private List<CmsTransportVo> postTransportData(List list, ApplicationProperties applicationProperties, RestTemplate restTemplate){
        logger.debug("Posting transport data to TransportRestController of cms-backend");
        String url = applicationProperties.getCmsBackEndUrl()+"/api/cmstransport-bulk-load";
        List<CmsTransportVo> ls = null;
        try {
            ls = restTemplate.postForObject(url, list, List.class);
            if(ls != null && ls.size() >0 ) {
                logger.debug("List of transport records could not be saved : ",ls);
            }
        }catch(Exception e) {
            logger.error("Transport records could not be saved. Exception : ", e);
        }
        return ls;
    }

}
