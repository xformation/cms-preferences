package com.synectiks.pref.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.pref.domain.UserPreference} entity.
 */
public class UserPreferenceDTO implements Serializable {

    private Long id;

    private String userId;

    private Long academicYearId;

    private Long collegeId;

    private Long branchId;

    private Long departmentId;

    private Long courseId;

    private Long semesterId;

    private Long batchId;

    private Long sectionId;

    private Long classId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserPreferenceDTO userPreferenceDTO = (UserPreferenceDTO) o;
        if (userPreferenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userPreferenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserPreferenceDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", academicYearId=" + getAcademicYearId() +
            ", collegeId=" + getCollegeId() +
            ", branchId=" + getBranchId() +
            ", departmentId=" + getDepartmentId() +
            ", courseId=" + getCourseId() +
            ", semesterId=" + getSemesterId() +
            ", batchId=" + getBatchId() +
            ", sectionId=" + getSectionId() +
            ", classId=" + getClassId() +
            "}";
    }
}
