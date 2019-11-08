package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A MetaLecture.
 */
@Entity
@Table(name = "meta_lecture")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "metalecture")
public class MetaLecture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "week_day")
    private String weekDay;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private Subject subject;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private Term term;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private AcademicYear academicyear;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private Section section;

    @ManyToOne
    @JsonIgnoreProperties("metaLectures")
    private Batch batch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public MetaLecture weekDay(String weekDay) {
        this.weekDay = weekDay;
        return this;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public MetaLecture startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public MetaLecture endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public MetaLecture branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Department getDepartment() {
        return department;
    }

    public MetaLecture department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Subject getSubject() {
        return subject;
    }

    public MetaLecture subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public MetaLecture teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Term getTerm() {
        return term;
    }

    public MetaLecture term(Term term) {
        this.term = term;
        return this;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public MetaLecture academicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
        return this;
    }

    public void setAcademicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
    }

    public Section getSection() {
        return section;
    }

    public MetaLecture section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Batch getBatch() {
        return batch;
    }

    public MetaLecture batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MetaLecture)) {
            return false;
        }
        return id != null && id.equals(((MetaLecture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MetaLecture{" +
            "id=" + getId() +
            ", weekDay='" + getWeekDay() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }
}
