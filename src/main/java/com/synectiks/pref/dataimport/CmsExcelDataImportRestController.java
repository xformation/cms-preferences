package com.synectiks.pref.dataimport;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.vo.QueryResult;
import com.synectiks.pref.service.util.CommonUtil;


@RestController
@RequestMapping("/api")
public class CmsExcelDataImportRestController {
	private final Logger logger = LoggerFactory.getLogger(CmsExcelDataImportRestController.class);

	@Autowired
	private DataLoaderFactory dataLoaderFactory;

	@Autowired
	private AllRepositories allRepositories;

	private String [] allEntities =
		{
			"college","branch","authorized_signatory","bank_accounts","legal_entity",
			"academic_year","holiday","term","department","teacher","subject","student","fee_category","transport_route","facility",
            "fee_details","admission_enquiry","admission_application","contract","due_date"
//				"attendance_master",
		};

	@RequestMapping(method = RequestMethod.POST, value = "/cmsdataimport/{tableName}")
	public List<QueryResult> doImport(@RequestParam("file") MultipartFile file, @PathVariable String tableName) throws URISyntaxException {
		String msg = "Data successfully imported for entity - "+tableName;
		List<QueryResult> list = new ArrayList<>();
		QueryResult qres = null;
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		if(CommonUtil.isNullOrEmpty(tableName)) {
			throw new RuntimeException("Invalid excel file name: " + fileName);
		}

		if(fileName.contains("..")) {
            throw new RuntimeException("Filename contains invalid path sequence: " + fileName);
        }

		if(!fileName.contains(CmsConstants.XLS_FILE_EXTENSION) || !fileName.contains(CmsConstants.XLSX_FILE_EXTENSION)) {
			throw new RuntimeException("Invalid excel file. File extension not found: " + fileName);
		}

		if("ALL".equalsIgnoreCase(tableName)){
			for(int i=0; i<allEntities.length; i++) {
				DataLoader dataLoader = this.dataLoaderFactory.getLoader(allEntities[i], this.allRepositories);
				if(dataLoader == null) {
					msg = "Application does not support data import for entity - "+allEntities[i];
					logger.warn(msg);
				}
				try {
					dataLoader.load(file,this.dataLoaderFactory.getClassName(allEntities[i]));
					qres = new QueryResult(allEntities[i], "SUCCESS");
					list.add(qres);
				}catch(Exception e) {
					msg = "Due to some error data import failed for entity - "+allEntities[i];
					logger.error(msg, e);
					qres = new QueryResult(allEntities[i], "FAILED");
					list.add(qres);
				}
			}
		}else {
			DataLoader dataLoader = this.dataLoaderFactory.getLoader(tableName, this.allRepositories);
			if(dataLoader == null) {
				msg = "Application does not support data import for entity - "+tableName;
				logger.warn(msg);
			}
			try {
				dataLoader.load(file,this.dataLoaderFactory.getClassName(tableName));
				qres = new QueryResult(tableName, "SUCCESS");
				list.add(qres);
			}catch(Exception e) {
				msg = "Due to some error data import failed for entity - "+tableName;
				qres = new QueryResult(tableName, "FAILED");
				list.add(qres);
				logger.error(msg, e);
			}
		}
		logger.info("Master data uploaded successfully....");
		return list;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cmsdataimport")
	public List<String> getTableLilst(){
		return CmsConstants.TABLE_LIST;
	}
}
