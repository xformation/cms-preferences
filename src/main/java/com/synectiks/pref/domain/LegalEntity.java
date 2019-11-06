package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

import com.synectiks.pref.domain.enumeration.TypeOfCollege;

/**
 * A LegalEntity.
 */
@Entity
@Table(name = "legal_entity")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "legalentity")
public class LegalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "logo_path")
    private String logoPath;

    @Column(name = "logo_file_name")
    private String logoFileName;

    @Column(name = "logo_file")
    private String logoFile;

    @Column(name = "legal_name_of_the_college")
    private String legalNameOfTheCollege;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_college")
    private TypeOfCollege typeOfCollege;

    @Column(name = "date_of_incorporation")
    private LocalDate dateOfIncorporation;

    @Column(name = "registered_office_address_1")
    private String registeredOfficeAddress1;

    @Column(name = "registered_office_address_2")
    private String registeredOfficeAddress2;

    @Column(name = "registered_office_address_3")
    private String registeredOfficeAddress3;

    @Column(name = "registered_office_address_4")
    private String registeredOfficeAddress4;

    @Column(name = "registered_office_address_5")
    private String registeredOfficeAddress5;

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
    private LocalDate pfRegistrationDate;

    @Column(name = "pf_signatory")
    private Long pfSignatory;

    @Column(name = "esi_number")
    private String esiNumber;

    @Column(name = "esi_registration_date")
    private LocalDate esiRegistrationDate;

    @Column(name = "esi_signatory")
    private Long esiSignatory;

    @Column(name = "pt_number")
    private String ptNumber;

    @Column(name = "pt_registration_date")
    private LocalDate ptRegistrationDate;

    @Column(name = "pt_signatory")
    private Long ptSignatory;

    @ManyToOne
    @JsonIgnoreProperties("legalEntities")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("legalEntities")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("legalEntities")
    private State state;

    @ManyToOne
    @JsonIgnoreProperties("legalEntities")
    private City city;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public LegalEntity logoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
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

    public String getLogoFile() {
        return logoFile;
    }

    public LegalEntity logoFile(String logoFile) {
        this.logoFile = logoFile;
        return this;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }

    public String getLegalNameOfTheCollege() {
        return legalNameOfTheCollege;
    }

    public LegalEntity legalNameOfTheCollege(String legalNameOfTheCollege) {
        this.legalNameOfTheCollege = legalNameOfTheCollege;
        return this;
    }

    public void setLegalNameOfTheCollege(String legalNameOfTheCollege) {
        this.legalNameOfTheCollege = legalNameOfTheCollege;
    }

    public TypeOfCollege getTypeOfCollege() {
        return typeOfCollege;
    }

    public LegalEntity typeOfCollege(TypeOfCollege typeOfCollege) {
        this.typeOfCollege = typeOfCollege;
        return this;
    }

    public void setTypeOfCollege(TypeOfCollege typeOfCollege) {
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

    public String getRegisteredOfficeAddress1() {
        return registeredOfficeAddress1;
    }

    public LegalEntity registeredOfficeAddress1(String registeredOfficeAddress1) {
        this.registeredOfficeAddress1 = registeredOfficeAddress1;
        return this;
    }

    public void setRegisteredOfficeAddress1(String registeredOfficeAddress1) {
        this.registeredOfficeAddress1 = registeredOfficeAddress1;
    }

    public String getRegisteredOfficeAddress2() {
        return registeredOfficeAddress2;
    }

    public LegalEntity registeredOfficeAddress2(String registeredOfficeAddress2) {
        this.registeredOfficeAddress2 = registeredOfficeAddress2;
        return this;
    }

    public void setRegisteredOfficeAddress2(String registeredOfficeAddress2) {
        this.registeredOfficeAddress2 = registeredOfficeAddress2;
    }

    public String getRegisteredOfficeAddress3() {
        return registeredOfficeAddress3;
    }

    public LegalEntity registeredOfficeAddress3(String registeredOfficeAddress3) {
        this.registeredOfficeAddress3 = registeredOfficeAddress3;
        return this;
    }

    public void setRegisteredOfficeAddress3(String registeredOfficeAddress3) {
        this.registeredOfficeAddress3 = registeredOfficeAddress3;
    }

    public String getRegisteredOfficeAddress4() {
        return registeredOfficeAddress4;
    }

    public LegalEntity registeredOfficeAddress4(String registeredOfficeAddress4) {
        this.registeredOfficeAddress4 = registeredOfficeAddress4;
        return this;
    }

    public void setRegisteredOfficeAddress4(String registeredOfficeAddress4) {
        this.registeredOfficeAddress4 = registeredOfficeAddress4;
    }

    public String getRegisteredOfficeAddress5() {
        return registeredOfficeAddress5;
    }

    public LegalEntity registeredOfficeAddress5(String registeredOfficeAddress5) {
        this.registeredOfficeAddress5 = registeredOfficeAddress5;
        return this;
    }

    public void setRegisteredOfficeAddress5(String registeredOfficeAddress5) {
        this.registeredOfficeAddress5 = registeredOfficeAddress5;
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

    public College getCollege() {
        return college;
    }

    public LegalEntity college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public State getState() {
        return state;
    }

    public LegalEntity state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public LegalEntity city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
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
            ", logoPath='" + getLogoPath() + "'" +
            ", logoFileName='" + getLogoFileName() + "'" +
            ", logoFile='" + getLogoFile() + "'" +
            ", legalNameOfTheCollege='" + getLegalNameOfTheCollege() + "'" +
            ", typeOfCollege='" + getTypeOfCollege() + "'" +
            ", dateOfIncorporation='" + getDateOfIncorporation() + "'" +
            ", registeredOfficeAddress1='" + getRegisteredOfficeAddress1() + "'" +
            ", registeredOfficeAddress2='" + getRegisteredOfficeAddress2() + "'" +
            ", registeredOfficeAddress3='" + getRegisteredOfficeAddress3() + "'" +
            ", registeredOfficeAddress4='" + getRegisteredOfficeAddress4() + "'" +
            ", registeredOfficeAddress5='" + getRegisteredOfficeAddress5() + "'" +
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
}
