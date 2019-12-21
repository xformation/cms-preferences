package com.synectiks.pref.domain;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.pref.domain.enumeration.Status;
import com.synectiks.pref.utils.IESEntity;

/**
 * A Holiday.
 */
@Entity
@Table(name = "holiday")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Holiday implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "holiday_desc")
    private String holidayDesc;

    @Column(name = "holiday_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate holidayDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "holiday_status")
    private Status holidayStatus;

    @ManyToOne
    @JsonIgnoreProperties("holidays")
    private AcademicYear academicyear;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public Holiday holidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
        return this;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public Holiday holidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
        return this;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Status getHolidayStatus() {
        return holidayStatus;
    }

    public Holiday holidayStatus(Status holidayStatus) {
        this.holidayStatus = holidayStatus;
        return this;
    }

    public void setHolidayStatus(Status holidayStatus) {
        this.holidayStatus = holidayStatus;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public Holiday academicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
        return this;
    }

    public void setAcademicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Holiday)) {
            return false;
        }
        return id != null && id.equals(((Holiday) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Holiday{" +
            "id=" + getId() +
            ", holidayDesc='" + getHolidayDesc() + "'" +
            ", holidayDate='" + getHolidayDate() + "'" +
            ", holidayStatus='" + getHolidayStatus() + "'" +
            "}";
    }
}
