package com.synectiks.pref.business.service;

import com.synectiks.pref.constant.CmsConstants;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Notifications;
import com.synectiks.pref.domain.vo.CmsAcademicYearVo;
import com.synectiks.pref.domain.vo.CmsNotificationsVo;
import com.synectiks.pref.graphql.types.notifications.NotificationsInput;
import com.synectiks.pref.repository.NotificationsRepository;
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
public class CmsNotificationsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NotificationsRepository notificationsRepository;
    @Autowired
    CmsAcademicYearService cmsAcademicYearService;


    public List<CmsNotificationsVo> getCmsNotificationsListOnFilterCriteria(Map<String, String> criteriaMap) {
        Notifications obj = new Notifications();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("messageCode") != null) {
            obj.setMessageCode(criteriaMap.get("messageCode"));
            isFilter = true;
        }
        if (criteriaMap.get("message") != null) {
            obj.setMessage(criteriaMap.get("message"));
            isFilter = true;
        }
        if (criteriaMap.get("status") != null) {
            obj.setStatus(criteriaMap.get("status"));
            isFilter = true;
        }
        if (criteriaMap.get("academicYearId") != null) {
            AcademicYear academicYear = cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
            obj.setAcademicYear(academicYear);
            isFilter = true;
        }
        List<Notifications> list = null;
        if (isFilter) {
            list = this.notificationsRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        } else {
            list = this.notificationsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        List<CmsNotificationsVo> ls = changeNotificationsToCmsNotificationsList(list);
        Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return ls;
    }


    public List<Notifications> getNotificationsListOnFilterCriteria(Map<String, String> criteriaMap) {
        Notifications obj = new Notifications();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("messageCode") != null) {
            obj.setMessageCode(criteriaMap.get("messageCode"));
            isFilter = true;
        }
        if (criteriaMap.get("message") != null) {
            obj.setMessage(criteriaMap.get("message"));
            isFilter = true;
        }
        if (criteriaMap.get("status") != null) {
            obj.setStatus(criteriaMap.get("status"));
            isFilter = true;
        }
        if (criteriaMap.get("academicYearId") != null) {
            AcademicYear academicYear = cmsAcademicYearService.getAcademicYear(Long.parseLong(criteriaMap.get("academicYearId")));
            obj.setAcademicYear(academicYear);
            isFilter = true;
        }
        List<Notifications> list = null;
        if (isFilter) {
            list = this.notificationsRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        } else {
            list = this.notificationsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return list;
    }

    public List<CmsNotificationsVo> getNotificationsList(String status) {
        Notifications notifications = new Notifications();
        notifications.setStatus(status);
        List<Notifications> list = this.notificationsRepository.findAll(Example.of(notifications));
        List<CmsNotificationsVo> ls = changeNotificationsToCmsNotificationsList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<CmsNotificationsVo> getNotificationsList() {
        List<Notifications> list = this.notificationsRepository.findAll();
        List<CmsNotificationsVo> ls = changeNotificationsToCmsNotificationsList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsNotificationsVo getCmsNotifications(Long id) {
        Optional<Notifications> th = this.notificationsRepository.findById(id);
        if (th.isPresent()) {
            CmsNotificationsVo vo = CommonUtil.createCopyProperties(th.get(), CmsNotificationsVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsNotifications for given id : " + id + ". CmsNotifications object : " + vo);
            return vo;
        }
        logger.debug("Notifications object not found for the given id. " + id + ". Returning null object");
        return null;
    }

    public Notifications getNotifications(Long id) {
        Optional<Notifications> th = this.notificationsRepository.findById(id);
        if (th.isPresent()) {
            return th.get();
        }
        logger.debug("Notifications object not found for the given id. " + id + ". Returning null");
        return null;
    }

    private List<CmsNotificationsVo> changeNotificationsToCmsNotificationsList(List<Notifications> list) {
        List<CmsNotificationsVo> ls = new ArrayList<>();
        for (Notifications hl : list) {
            CmsNotificationsVo vo = CommonUtil.createCopyProperties(hl, CmsNotificationsVo.class);
            convertDatesAndProvideDependencies(hl, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Notifications hl, CmsNotificationsVo vo) {
        if (hl.getCreatedOn() != null) {
            vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(hl.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        if (hl.getUpdatedOn() != null) {
            vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(hl.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }
        if (hl.getAcademicYear() != null) {
            vo.setAcademicYearId(hl.getAcademicYear().getId());
            CmsAcademicYearVo cmsAvo = CommonUtil.createCopyProperties(hl.getAcademicYear(), CmsAcademicYearVo.class);
            vo.setCmsAcademicYearVo(cmsAvo);
        }
    }
    public CmsNotificationsVo saveNotifications(NotificationsInput input) {
        logger.info("Saving Notifications");
        CmsNotificationsVo vo = null;
        try {
            Notifications notifications = null;
            if(input.getId() == null) {
                logger.debug("Adding new notifications");
                notifications = CommonUtil.createCopyProperties(input, Notifications.class);
                notifications.setCreatedOn(LocalDate.now());

            }else {
                logger.debug("Updating existing notifications");
                notifications = this.notificationsRepository.findById(input.getId()).get();
                notifications.setUpdatedOn(LocalDate.now());

            }
            AcademicYear ay = this.cmsAcademicYearService.getAcademicYear(input.getAcademicYearId()); // this.academicYearRepository.findById(input.getAcademicYearId()).get();
            notifications.setAcademicYear(ay);
            notifications.setMessageCode(input.getMessageCode());
            notifications.setMessage(input.getMessage());
            notifications.setStartTime(input.getStartTime());
            notifications.setEndTime(input.getEndTime());

            notifications = notificationsRepository.save(notifications);
            notifications.setStartDate(input.getStrStartDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            notifications.setEndDate(input.getStrEndDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);


            notifications = notificationsRepository.save(notifications);

            vo = CommonUtil.createCopyProperties(notifications, CmsNotificationsVo.class);
            vo.setStrCreatedOn(notifications.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(notifications.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrUpdatedOn(notifications.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(notifications.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrStartDate(notifications.getStartDate() != null ? DateFormatUtil.changeLocalDateFormat(notifications.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrUpdatedOn(notifications.getEndDate() != null ? DateFormatUtil.changeLocalDateFormat(notifications.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");


            vo.setCreatedOn(null);
            vo.setUpdatedOn(null);
            vo.setExitCode(0L);
            if(input.getId() == null) {
                vo.setExitDescription("Holiday is added successfully");
                logger.debug("Holiday is added successfully");
            }else {
                vo.setExitDescription("Holiday is updated successfully");
                logger.debug("Holiday is updated successfully");
            }

        }catch(Exception e) {
            vo = new CmsNotificationsVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, holiday data not be saved");
            logger.error("Holiday save failed. Exception : ",e);
        }
        logger.info("Holiday saved successfully");
        List ls =  getNotificationsList();
        vo.setDataList(ls);
        return vo;


}
}

