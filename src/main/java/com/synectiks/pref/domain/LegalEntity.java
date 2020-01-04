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
 * A LegalEntity.
 */
@Entity
@Table(name = "legal_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LegalEntity implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "logo_file_path")
    private String logoFilePath;

    @Column(name = "logo_file_name")
    private String logoFileName;

    @Column(name = "logo_file_extension")
    private String logoFileExtension;

    @Column(name = "legal_name_of_college")
    private String legalNameOfCollege;

    @Column(name = "type_of_college")
    private String typeOfCollege;

    @Column(name = "date_of_incorporation")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfIncorporation;

    @Column(name = "registered_office_address")
    private String registeredOfficeAddress;

    @Column(name = "college_identification_number")
    private String collegeIdentificationNumber;

    @Column(name = "pan")
    private String pan;

    @Column(name = "tan")
    private String tan;

    @Column(name = "tan_circle_number")
    private String tanCircleNumber;

    @Column(name = "cit_tds_location")
    private String citTdsLocation;

    @Column(name = "form_signatory")
    private Long formSignatory;

    @Column(name = "pf_number")
    private String pfNumber;

    @Column(name = "pf_registration_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate pfRegistrationDate;

    @Column(name = "pf_signatory")
    private Long pfSignatory;

    @Column(name = "esi_number")
    private String esiNumber;

    @Column(name = "esi_registration_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate esiRegistrationDate;

    @Column(name = "esi_signatory")
    private Long esiSignatory;

    @Column(name = "pt_number")
    private String ptNumber;

    @Column(name = "pt_registration_date")
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate ptRegistrationDate;

    @Column(name = "pt_signatory")
    private Long ptSignatory;

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
    @JsonIgnoreProperties("legalEntities")
    private Branch branch;

    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoFilePath() {
        return logoFilePath;
    }

    public LegalEntity logoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
        return this;
    }

    public void setLogoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public LegalEntity logoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
        return this;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getLogoFileExtension() {
        return logoFileExtension;
    }

    public LegalEntity logoFileExtension(String logoFileExtension) {
        this.logoFileExtension = logoFileExtension;
        return this;
    }

    public void setLogoFileExtension(String logoFileExtension) {
        this.logoFileExtension = logoFileExtension;
    }

    public String getLegalNameOfCollege() {
        return legalNameOfCollege;
    }

    public LegalEntity legalNameOfCollege(String legalNameOfCollege) {
        this.legalNameOfCollege = legalNameOfCollege;
        return this;
    }

    public void setLegalNameOfCollege(String legalNameOfCollege) {
        this.legalNameOfCollege = legalNameOfCollege;
    }

    public String getTypeOfCollege() {
        return typeOfCollege;
    }

    public LegalEntity typeOfCollege(String typeOfCollege) {
        this.typeOfCollege = typeOfCollege;
        return this;
    }

    public void setTypeOfCollege(String typeOfCollege) {
        this.typeOfCollege = typeOfCollege;
    }

    public LocalDate getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public LegalEntity dateOfIncorporation(LocalDate dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
        return this;
    }

    public void setDateOfIncorporation(LocalDate dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    public LegalEntity registeredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
        return this;
    }

    public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
    }

    public String getCollegeIdentificationNumber() {
        return collegeIdentificationNumber;
    }

    public LegalEntity collegeIdentificationNumber(String collegeIdentificationNumber) {
        this.collegeIdentificationNumber = collegeIdentificationNumber;
        return this;
    }

    public void setCollegeIdentificationNumber(String collegeIdentificationNumber) {
        this.collegeIdentificationNumber = collegeIdentificationNumber;
    }

    public String getPan() {
        return pan;
    }

    public LegalEntity pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getTan() {
        return tan;
    }

    public LegalEntity tan(String tan) {
        this.tan = tan;
        return this;
    }

    public void setTan(String tan) {
        this.tan = tan;
    }

    public String getTanCircleNumber() {
        return tanCircleNumber;
    }

    public LegalEntity tanCircleNumber(String tanCircleNumber) {
        this.tanCircleNumber = tanCircleNumber;
        return this;
    }

    public void setTanCircleNumber(String tanCircleNumber) {
        this.tanCircleNumber = tanCircleNumber;
    }

    public String getCitTdsLocation() {
        return citTdsLocation;
    }

    public LegalEntity citTdsLocation(String citTdsLocation) {
        this.citTdsLocation = citTdsLocation;
        return this;
    }

    public void setCitTdsLocation(String citTdsLocation) {
        this.citTdsLocation = citTdsLocation;
    }

    public Long getFormSignatory() {
        return formSignatory;
    }

    public LegalEntity formSignatory(Long formSignatory) {
        this.formSignatory = formSignatory;
        return this;
    }

    public void setFormSignatory(Long formSignatory) {
        this.formSignatory = formSignatory;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public LegalEntity pfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
        return this;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public LocalDate getPfRegistrationDate() {
        return pfRegistrationDate;
    }

    public LegalEntity pfRegistrationDate(LocalDate pfRegistrationDate) {
        this.pfRegistrationDate = pfRegistrationDate;
        return this;
    }

    public void setPfRegistrationDate(LocalDate pfRegistrationDate) {
        this.pfRegistrationDate = pfRegistrationDate;
    }

    public Long getPfSignatory() {
        return pfSignatory;
    }

    public LegalEntity pfSignatory(Long pfSignatory) {
        this.pfSignatory = pfSignatory;
        return this;
    }

    public void setPfSignatory(Long pfSignatory) {
        this.pfSignatory = pfSignatory;
    }

    public String getEsiNumber() {
        return esiNumber;
    }

    public LegalEntity esiNumber(String esiNumber) {
        this.esiNumber = esiNumber;
        return this;
    }

    public void setEsiNumber(String esiNumber) {
        this.esiNumber = esiNumber;
    }

    public LocalDate getEsiRegistrationDate() {
        return esiRegistrationDate;
    }

    public LegalEntity esiRegistrationDate(LocalDate esiRegistrationDate) {
        this.esiRegistrationDate = esiRegistrationDate;
        return this;
    }

    public void setEsiRegistrationDate(LocalDate esiRegistrationDate) {
        this.esiRegistrationDate = esiRegistrationDate;
    }

    public Long getEsiSignatory() {
        return esiSignatory;
    }

    public LegalEntity esiSignatory(Long esiSignatory) {
        this.esiSignatory = esiSignatory;
        return this;
    }

    public void setEsiSignatory(Long esiSignatory) {
        this.esiSignatory = esiSignatory;
    }

    public String getPtNumber() {
        return ptNumber;
    }

    public LegalEntity ptNumber(String ptNumber) {
        this.ptNumber = ptNumber;
        return this;
    }

    public void setPtNumber(String ptNumber) {
        this.ptNumber = ptNumber;
    }

    public LocalDate getPtRegistrationDate() {
        return ptRegistrationDate;
    }

    public LegalEntity ptRegistrationDate(LocalDate ptRegistrationDate) {
        this.ptRegistrationDate = ptRegistrationDate;
        return this;
    }

    public void setPtRegistrationDate(LocalDate ptRegistrationDate) {
        this.ptRegistrationDate = ptRegistrationDate;
    }

    public Long getPtSignatory() {
        return ptSignatory;
    }

    public LegalEntity ptSignatory(Long ptSignatory) {
        this.ptSignatory = ptSignatory;
        return this;
    }

    public void setPtSignatory(Long ptSignatory) {
        this.ptSignatory = ptSignatory;
    }

    public Branch getBranch() {
        return branch;
    }

    public LegalEntity branch(Branch branch) {
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
        if (!(o instanceof LegalEntity)) {
            return false;
        }
        return id != null && id.equals(((LegalEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LegalEntity{" +
            "id=" + getId() +
            ", logoFilePath='" + getLogoFilePath() + "'" +
            ", logoFileName='" + getLogoFileName() + "'" +
            ", logoFileExtension='" + getLogoFileExtension() + "'" +
            ", legalNameOfCollege='" + getLegalNameOfCollege() + "'" +
            ", typeOfCollege='" + getTypeOfCollege() + "'" +
            ", dateOfIncorporation='" + getDateOfIncorporation() + "'" +
            ", registeredOfficeAddress='" + getRegisteredOfficeAddress() + "'" +
            ", collegeIdentificationNumber='" + getCollegeIdentificationNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", tan='" + getTan() + "'" +
            ", tanCircleNumber='" + getTanCircleNumber() + "'" +
            ", citTdsLocation='" + getCitTdsLocation() + "'" +
            ", formSignatory=" + getFormSignatory() +
            ", pfNumber='" + getPfNumber() + "'" +
            ", pfRegistrationDate='" + getPfRegistrationDate() + "'" +
            ", pfSignatory=" + getPfSignatory() +
            ", esiNumber='" + getEsiNumber() + "'" +
            ", esiRegistrationDate='" + getEsiRegistrationDate() + "'" +
            ", esiSignatory=" + getEsiSignatory() +
            ", ptNumber='" + getPtNumber() + "'" +
            ", ptRegistrationDate='" + getPtRegistrationDate() + "'" +
            ", ptSignatory=" + getPtSignatory() +
            "}";
    }

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
