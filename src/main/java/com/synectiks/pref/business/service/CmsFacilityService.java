package com.synectiks.pref.business.service;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Facility;
import com.synectiks.pref.domain.Holiday;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsBranchVo;
import com.synectiks.pref.domain.vo.CmsFacility;
import com.synectiks.pref.domain.vo.CmsHolidayVo;
import com.synectiks.pref.graphql.types.facility.FacilityInput;
import com.synectiks.pref.graphql.types.holiday.HolidayInput;
import com.synectiks.pref.repository.FacilityRepository;
import com.synectiks.pref.repository.HolidayRepository;
import com.synectiks.pref.service.util.CommonUtil;
import com.synectiks.pref.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class CmsFacilityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacilityRepository facilityRepository;

    @Autowired
    CmsAcademicYearService cmsAcademicYearService;

    @Autowired
    CmsBranchService cmsBranchService;

    public List<CmsFacility> getCmsFacilityListOnFilterCriteria(Map<String, String> criteriaMap){
        Facility obj = new Facility();
        boolean isFilter = false;
        if(criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if(criteriaMap.get("name") != null) {
            obj.setName(criteriaMap.get("name"));
            isFilter = true;
        }
        if(criteriaMap.get("status") != null) {
            obj.setStatus(criteriaMap.get("status"));
            isFilter = true;
        }
        if(criteriaMap.get("startDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("startDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setStartDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("endDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("endDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setEndDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("suspandStartDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("suspandStartDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setSuspandStartDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("suspandEndDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("suspandEndDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setSuspandEndDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("amount") != null) {
            obj.setAmount(Long.valueOf(criteriaMap.get("amount")));
            isFilter = true;
        }
        if(criteriaMap.get("academicYearId") != null) {
            AcademicYear academicYear = cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
            obj.setAcademicYear(academicYear);
            isFilter = true;
        }
        if(criteriaMap.get("branchId") != null) {
            Branch branch = cmsBranchService.getBranch(Long.parseLong(criteriaMap.get("branchId")));
            obj.setBranch(branch);
            isFilter = true;
        }
        List<Facility> list = null;
        if(isFilter) {
            list = this.facilityRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.facilityRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        List<CmsFacility> ls = changeFacilityToCmsFacilityList(list);
        Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return ls;
    }

    public List<Facility> getFacilityListOnFilterCriteria(Map<String, String> criteriaMap){
        Facility obj = new Facility();
        boolean isFilter = false;
        if(criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if(criteriaMap.get("name") != null) {
            obj.setName(criteriaMap.get("name"));
            isFilter = true;
        }
        if(criteriaMap.get("status") != null) {
            obj.setStatus(criteriaMap.get("status"));
            isFilter = true;
        }
        if(criteriaMap.get("startDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("startDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setStartDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("endDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("endDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setEndDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("suspandStartDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("suspandStartDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setSuspandStartDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("suspandEndDate") != null) {
            LocalDate dt = DateFormatUtil.convertStringToLocalDate(criteriaMap.get("suspandEndDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
            obj.setSuspandEndDate(dt);
            isFilter = true;
        }
        if(criteriaMap.get("amount") != null) {
            obj.setAmount(Long.valueOf(criteriaMap.get("amount")));
            isFilter = true;
        }
        if(criteriaMap.get("academicYearId") != null) {
            AcademicYear academicYear = cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
            obj.setAcademicYear(academicYear);
            isFilter = true;
        }
        if(criteriaMap.get("branchId") != null) {
            Branch branch = cmsBranchService.getBranch(Long.parseLong(criteriaMap.get("branchId")));
            obj.setBranch(branch);
            isFilter = true;
        }
        List<Facility> list = null;
        if(isFilter) {
            list = this.facilityRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.facilityRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return list;
    }

    public List<CmsFacility> getFacilityList(String status) {
        Facility facility = new Facility();
        facility.setStatus(status);
        List<Facility> list = this.facilityRepository.findAll(Example.of(facility));
        List<CmsFacility> ls = changeFacilityToCmsFacilityList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<CmsFacility> getFacilityList(){
        List<Facility> list = this.facilityRepository.findAll();
        List<CmsFacility> ls = changeFacilityToCmsFacilityList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsFacility getCmsFacility(Long id){
        Optional<Facility> th = this.facilityRepository.findById(id);
        if(th.isPresent()) {
            CmsFacility vo = CommonUtil.createCopyProperties(th.get(), CmsFacility.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsFacility for given id : "+id+". CmsFacility object : "+vo);
            return vo;
        }
        logger.debug("Facility object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Facility getFacility(Long id){
        Optional<Facility> th = this.facilityRepository.findById(id);
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("Facility object not found for the given id. "+id+". Returning null");
        return null;
    }

    private List<CmsFacility> changeFacilityToCmsFacilityList(List<Facility> list){
        List<CmsFacility> ls = new ArrayList<>();
        for(Facility fl: list) {
            CmsFacility vo = CommonUtil.createCopyProperties(fl, CmsFacility.class);
            convertDatesAndProvideDependencies(fl, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Facility fl, CmsFacility vo) {
        if(fl.getStartDate() != null) {
            vo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(fl.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        if(fl.getEndDate() != null) {
            vo.setStrEndDate(DateFormatUtil.changeLocalDateFormat(fl.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        if(fl.getSuspandStartDate() != null) {
            vo.setStrSuspandStartDate(DateFormatUtil.changeLocalDateFormat(fl.getSuspandStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        if(fl.getSuspandEndDate() != null) {
            vo.setStrSuspandEndDate(DateFormatUtil.changeLocalDateFormat(fl.getSuspandEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        if(fl.getAcademicYear() != null) {
            vo.setAcademicYearId(fl.getAcademicYear().getId());
            CmsAcademicYearVo cmsAvo =CommonUtil.createCopyProperties(fl.getAcademicYear(), CmsAcademicYearVo.class);
            vo.setCmsAcademicYearVo(cmsAvo);
        }
        if(fl.getBranch() != null) {
            vo.setBranchId(fl.getBranch().getId());
            CmsBranchVo cmsBr =CommonUtil.createCopyProperties(fl.getBranch(), CmsBranchVo.class);
            vo.setCmsBranchVo(cmsBr);
        }
    }

    public CmsFacility saveFacility(FacilityInput input) {
        logger.info("Saving facility");
        CmsFacility vo = null;
        try {
            Facility facility = null;
            if(input.getId() == null) {
                logger.debug("Adding new facility");
                facility = CommonUtil.createCopyProperties(input, Facility.class);
            }else {
                logger.debug("Updating existing facility");
                facility = this.facilityRepository.findById(input.getId()).get();
            }
            AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(input.getAcademicYearId()); // this.academicYearRepository.findById(input.getAcademicYearId()).get();
            facility.setAcademicYear(ay);
            Branch br = this.cmsBranchService.getBranch(input.getBranchId()); // this.academicYearRepository.findById(input.getAcademicYearId()).get();
            facility.setBranch(br);
            facility.setName(input.getName());
            facility.setStartDate(input.getStrStartDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            facility.setEndDate(input.getStrEndDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            facility.setSuspandStartDate(input.getStrSuspandStartDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrSuspandStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            facility.setSuspandEndDate(input.getStrSuspandEndDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrSuspandEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            facility.setStatus(input.getStatus());

            facility = facilityRepository.save(facility);

            vo = CommonUtil.createCopyProperties(facility, CmsFacility.class);
            vo.setStrStartDate(facility.getStartDate() != null ? DateFormatUtil.changeLocalDateFormat(facility.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrEndDate(facility.getEndDate() != null ? DateFormatUtil.changeLocalDateFormat(facility.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrSuspandStartDate(facility.getSuspandStartDate() != null ? DateFormatUtil.changeLocalDateFormat(facility.getSuspandStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrSuspandEndDate(facility.getSuspandEndDate() != null ? DateFormatUtil.changeLocalDateFormat(facility.getSuspandEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");

            vo.setExitCode(0L);
            if(input.getId() == null) {
                vo.setExitDescription("Facility is added successfully");
                logger.debug("Facility is added successfully");
            }else {
                vo.setExitDescription("Facility is updated successfully");
                logger.debug("Facility is updated successfully");
            }

        }catch(Exception e) {
            vo = new CmsFacility();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, Facility data not be saved");
            logger.error("Facility save failed. Exception : ",e);
        }
        logger.info("Facility saved successfully");
        List ls =  getFacilityList();
        vo.setDataList(ls);
        return vo;

    }
}

