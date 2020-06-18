package com.synectiks.pref.graphql.resolvers;

import com.synectiks.pref.business.service.*;
//import com.synectiks.pref.domain.Notifications;
import com.synectiks.pref.domain.vo.*;
import com.synectiks.pref.graphql.types.facility.FacilityInput;
import com.synectiks.pref.graphql.types.facility.FacilityPayload;
import com.synectiks.pref.graphql.types.notifications.NotificationsInput;
import com.synectiks.pref.graphql.types.notifications.NotificationsPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.pref.graphql.types.academicyear.AcademicYearInput;
import com.synectiks.pref.graphql.types.academicyear.AcademicYearPayload;
import com.synectiks.pref.graphql.types.authorizedsignatory.AuthorizedSignatoryInput;
import com.synectiks.pref.graphql.types.authorizedsignatory.AuthorizedSignatoryPayload;
import com.synectiks.pref.graphql.types.bankaccount.BankAccountsInput;
import com.synectiks.pref.graphql.types.bankaccount.BankAccountsPayload;
import com.synectiks.pref.graphql.types.branch.BranchInput;
import com.synectiks.pref.graphql.types.branch.BranchPayload;
import com.synectiks.pref.graphql.types.college.CollegeInput;
import com.synectiks.pref.graphql.types.college.CollegePayload;
import com.synectiks.pref.graphql.types.course.CourseInput;
import com.synectiks.pref.graphql.types.course.CoursePayload;
import com.synectiks.pref.graphql.types.department.DepartmentInput;
import com.synectiks.pref.graphql.types.department.DepartmentPayload;
import com.synectiks.pref.graphql.types.employee.EmployeeInput;
import com.synectiks.pref.graphql.types.employee.EmployeePayload;
import com.synectiks.pref.graphql.types.holiday.HolidayInput;
import com.synectiks.pref.graphql.types.holiday.HolidayPayload;
import com.synectiks.pref.graphql.types.legalentity.LegalEntityInput;
import com.synectiks.pref.graphql.types.legalentity.LegalEntityPayload;
import com.synectiks.pref.graphql.types.subject.SubjectInput;
import com.synectiks.pref.graphql.types.subject.SubjectPayload;
import com.synectiks.pref.graphql.types.teacher.TeacherInput;
import com.synectiks.pref.graphql.types.teacher.TeacherPayload;
import com.synectiks.pref.graphql.types.term.TermInput;
import com.synectiks.pref.graphql.types.term.TermPayload;
import com.synectiks.pref.repository.UserPreferenceRepository;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    CmsCollegeService cmsCollegeService;

    @Autowired
    CmsBranchService cmsBranchService;

    @Autowired
    CmsAuthorizedSignatoryService cmsAuthorizedSignatoryService;

    @Autowired
    CmsBankAccountsService cmsBankAccountsService;

    @Autowired
    CmsLegalEntityService cmsLegalEntityService;

    @Autowired
    CmsAcademicYearService cmsAcademicYearService;

    @Autowired
    CmsHolidayService cmsHolidayService;

    @Autowired
    CmsTermService cmsTermService;

    @Autowired
    CmsDepartmentService cmsDepartmentService;

    @Autowired
    CmsCourseService cmsCourseService;

    @Autowired
    CmsEmployeeService cmsEmployeeService;

    @Autowired
    CmsTeacherService cmsTeacherService;
    @Autowired
    CmsNotificationsService cmsNotificationsService;
    @Autowired
    CmsSubjectService cmsSubjectService;

    @Autowired
    CmsFacilityService cmsFacilityService;

    public Mutation(UserPreferenceRepository userPreferenceRepository) {
    	this.userPreferenceRepository = userPreferenceRepository;
    }

    public CollegePayload addCollege(CollegeInput cmsCollegeVo) {
    	CmsCollegeVo vo = this.cmsCollegeService.addCollege(cmsCollegeVo);
    	return new CollegePayload(vo);
    }

    public BranchPayload saveBranch(BranchInput cmsBranchVo) {
    	CmsBranchVo vo = this.cmsBranchService.saveBranch(cmsBranchVo);
    	return new BranchPayload(vo);
    }


    public AuthorizedSignatoryPayload saveAuthorizedSignatory(AuthorizedSignatoryInput cmsAuthorizedSignatoryVo) {
    	CmsAuthorizedSignatoryVo vo = this.cmsAuthorizedSignatoryService.saveAuthorizedSignatory(cmsAuthorizedSignatoryVo);
    	return new AuthorizedSignatoryPayload(vo);
    }

    public BankAccountsPayload saveBankAccounts(BankAccountsInput cmsBankAccountsVo) {
    	CmsBankAccountsVo vo = this.cmsBankAccountsService.saveBankAccounts(cmsBankAccountsVo);
    	return new BankAccountsPayload(vo);
    }
    public NotificationsPayload saveNotifications(NotificationsInput cmsNotificationsVo) {
        CmsNotificationsVo vo = this.cmsNotificationsService.saveNotifications(cmsNotificationsVo);
        return new NotificationsPayload(vo);
    }
    public LegalEntityPayload saveLegalEntity(LegalEntityInput cmsLegalEntityVo) {
    	CmsLegalEntityVo vo = this.cmsLegalEntityService.saveLegalEntity(cmsLegalEntityVo);
    	return new LegalEntityPayload(vo);
    }

    public AcademicYearPayload saveAcademicYear(AcademicYearInput cmsAcademicYearVo) {
    	CmsAcademicYearVo vo = this.cmsAcademicYearService.saveAcademicYear(cmsAcademicYearVo);
    	return new AcademicYearPayload(vo);
    }

    public HolidayPayload saveHoliday(HolidayInput cmsHolidayVo) {
    	CmsHolidayVo vo = this.cmsHolidayService.saveHoliday(cmsHolidayVo);
    	return new HolidayPayload(vo);
    }

    public FacilityPayload saveFacility(FacilityInput cmsFacilityVo) {
        CmsFacility vo = this.cmsFacilityService.saveFacility(cmsFacilityVo);
        return new FacilityPayload(vo);
    }

    public TermPayload saveTerm(TermInput cmsTermVo) {
    	CmsTermVo vo = this.cmsTermService.saveTerm(cmsTermVo);
    	return new TermPayload(vo);
    }

    public DepartmentPayload saveDepartment(DepartmentInput cmsDepartmentVo) {
    	CmsDepartmentVo vo = this.cmsDepartmentService.saveDepartment(cmsDepartmentVo);
    	return new DepartmentPayload(vo);
    }

    public CoursePayload saveCourse(CourseInput cmsCourseVo) {
    	CmsCourseVo vo = this.cmsCourseService.saveCourse(cmsCourseVo);
    	return new CoursePayload(vo);
    }

    public EmployeePayload saveEmployee(EmployeeInput cmsEmployeeVo) {
    	CmsEmployeeVo vo = this.cmsEmployeeService.saveEmployee(cmsEmployeeVo);
    	return new EmployeePayload(vo);
    }

    public TeacherPayload saveTeacher(TeacherInput cmsTeacherVo) {
    	CmsTeacherVo vo = this.cmsTeacherService.saveTeacher(cmsTeacherVo);
    	return new TeacherPayload(vo);
    }
    public SubjectPayload saveSubject(SubjectInput cmsSubjectVo) {
    	CmsSubjectVo vo = this.cmsSubjectService.saveSubject(cmsSubjectVo);
    	return new SubjectPayload(vo);
    }

}
