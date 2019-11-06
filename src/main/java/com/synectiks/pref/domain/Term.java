package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

import com.synectiks.pref.domain.enumeration.Status;

/**
 * A Term.
 */
@Entity
@Table(name = "term")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "term")
public class Term implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "terms_desc")
    private String termsDesc;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_status")
    private Status termStatus;

    @ManyToOne
    @JsonIgnoreProperties("terms")
    private AcademicYear academicyear;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermsDesc() {
        return termsDesc;
    }

    public Term termsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
        return this;
    }

    public void setTermsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Term startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Term endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getTermStatus() {
        return termStatus;
    }

    public Term termStatus(Status termStatus) {
        this.termStatus = termStatus;
        return this;
    }

    public void setTermStatus(Status termStatus) {
        this.termStatus = termStatus;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public Term academicyear(AcademicYear academicYear) {
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
        if (!(o instanceof Term)) {
            return false;
        }
        return id != null && id.equals(((Term) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Term{" +
            "id=" + getId() +
            ", termsDesc='" + getTermsDesc() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", termStatus='" + getTermStatus() + "'" +
            "}";
    }
}
