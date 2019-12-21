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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.pref.utils.IESEntity;

/**
 * A College.
 */
@Entity
@Table(name = "college")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class College implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "logo_file_path")
    private String logoFilePath;

    @Column(name = "logo_file_name")
    private String logoFileName;

    @Column(name = "logo_file_extension")
    private String logoFileExtension;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate updatedOn;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("colleges")
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public College collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getLogoFilePath() {
        return logoFilePath;
    }

    public College logoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
        return this;
    }

    public void setLogoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public College logoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
        return this;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getLogoFileExtension() {
        return logoFileExtension;
    }

    public College logoFileExtension(String logoFileExtension) {
        this.logoFileExtension = logoFileExtension;
        return this;
    }

    public void setLogoFileExtension(String logoFileExtension) {
        this.logoFileExtension = logoFileExtension;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public College createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public College createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public College updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public College updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStatus() {
        return status;
    }

    public College status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Country getCountry() {
        return country;
    }

    public College country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof College)) {
            return false;
        }
        return id != null && id.equals(((College) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "College{" +
            "id=" + getId() +
            ", collegeName='" + getCollegeName() + "'" +
            ", logoFilePath='" + getLogoFilePath() + "'" +
            ", logoFileName='" + getLogoFileName() + "'" +
            ", logoFileExtension='" + getLogoFileExtension() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
