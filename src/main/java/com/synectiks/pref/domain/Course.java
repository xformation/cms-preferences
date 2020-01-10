package com.synectiks.pref.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "course_duration")
    private String courseDuration;

    @Column(name = "course_type")
    private String courseType;

    @Column(name = "year_or_semester_type")
    private String yearOrSemesterType;

    @Column(name = "total_fee")
    private Long totalFee;

    @Column(name = "yearly_fee")
    private Long yearlyFee;

    @Column(name = "per_semester_fee")
    private Long perSemesterFee;

    @Column(name = "comments")
    private String comments;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("courses")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("courses")
    private Branch branch;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Course name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Course description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public Course courseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
        return this;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseType() {
        return courseType;
    }

    public Course courseType(String courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getYearOrSemesterType() {
        return yearOrSemesterType;
    }

    public Course yearOrSemesterType(String yearOrSemesterType) {
        this.yearOrSemesterType = yearOrSemesterType;
        return this;
    }

    public void setYearOrSemesterType(String yearOrSemesterType) {
        this.yearOrSemesterType = yearOrSemesterType;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public Course totalFee(Long totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getYearlyFee() {
        return yearlyFee;
    }

    public Course yearlyFee(Long yearlyFee) {
        this.yearlyFee = yearlyFee;
        return this;
    }

    public void setYearlyFee(Long yearlyFee) {
        this.yearlyFee = yearlyFee;
    }

    public Long getPerSemesterFee() {
        return perSemesterFee;
    }

    public Course perSemesterFee(Long perSemesterFee) {
        this.perSemesterFee = perSemesterFee;
        return this;
    }

    public void setPerSemesterFee(Long perSemesterFee) {
        this.perSemesterFee = perSemesterFee;
    }

    public String getComments() {
        return comments;
    }

    public Course comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Course createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public Course createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Course updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public Course updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStatus() {
        return status;
    }

    public Course status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Department getDepartment() {
        return department;
    }

    public Course department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Branch getBranch() {
        return branch;
    }

    public Course branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", courseDuration='" + getCourseDuration() + "'" +
            ", courseType='" + getCourseType() + "'" +
            ", yearOrSemesterType='" + getYearOrSemesterType() + "'" +
            ", totalFee=" + getTotalFee() +
            ", yearlyFee=" + getYearlyFee() +
            ", perSemesterFee=" + getPerSemesterFee() +
            ", comments='" + getComments() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
