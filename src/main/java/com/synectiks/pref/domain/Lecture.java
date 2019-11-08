package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Lecture.
 */
@Entity
@Table(name = "lecture")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "lecture")
public class Lecture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @NotNull
    @Column(name = "lec_date", nullable = false)
    private LocalDate lecDate;

    @NotNull
    @Column(name = "last_updated_on", nullable = false)
    private LocalDate lastUpdatedOn;

    @NotNull
    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private String startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private String endTime;

    @ManyToOne
    @JsonIgnoreProperties("lectures")
    private AttendanceMaster attendancemaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLecDate() {
        return lecDate;
    }

    public Lecture lecDate(LocalDate lecDate) {
        this.lecDate = lecDate;
        return this;
    }

    public void setLecDate(LocalDate lecDate) {
        this.lecDate = lecDate;
    }

    public LocalDate getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public Lecture lastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Lecture lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getStartTime() {
        return startTime;
    }

    public Lecture startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Lecture endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public AttendanceMaster getAttendancemaster() {
        return attendancemaster;
    }

    public Lecture attendancemaster(AttendanceMaster attendanceMaster) {
        this.attendancemaster = attendanceMaster;
        return this;
    }

    public void setAttendancemaster(AttendanceMaster attendanceMaster) {
        this.attendancemaster = attendanceMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lecture)) {
            return false;
        }
        return id != null && id.equals(((Lecture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Lecture{" +
            "id=" + getId() +
            ", lecDate='" + getLecDate() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }
}
