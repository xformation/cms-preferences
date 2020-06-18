package com.synectiks.pref.ems.rest;



import com.synectiks.pref.business.service.CmsNotificationsService;

import com.synectiks.pref.domain.Notifications;

import com.synectiks.pref.domain.vo.CmsNotificationsVo;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NotificationsRestController {

    private final Logger logger = LoggerFactory.getLogger(NotificationsRestController.class);

    @Autowired
    private CmsNotificationsService cmsNotificationsService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsnotifications-by-filters")
    public List<CmsNotificationsVo> getCmsNotificationsListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Notifications based on filter criteria");
        List<CmsNotificationsVo> list = this.cmsNotificationsService.getCmsNotificationsListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/notifications-by-filters")
    public List<Notifications> getNotificationsListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Notifications based on filter criteria");
        List<Notifications> list = this.cmsNotificationsService.getNotificationsListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsnotifications")
    public List<CmsNotificationsVo> getAllCmsNotifications() throws Exception {
        logger.debug("REST request to get all Cms Notifications");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsNotificationsService.getCmsNotificationsListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-notifications")
    public List<Notifications> getAllNotifications() throws Exception {
        logger.debug("REST request to get all the Notifications");
        Map<String, String> m = new HashMap<String, String>();
        return this.cmsNotificationsService.getNotificationsListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/notifications-by-id/{id}")
    public ResponseEntity<Notifications> getNotifications(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Notifications : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsNotificationsService.getNotifications(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsnotifications/{id}")
    public ResponseEntity<CmsNotificationsVo> getCmsNotifications(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a notifications : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.cmsNotificationsService.getCmsNotifications(id)));
    }

}
