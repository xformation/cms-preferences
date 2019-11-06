package com.synectiks.pref.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.pref.domain.enumeration.Status;

/**
 * A DTO for the {@link com.synectiks.pref.domain.Term} entity.
 */
public class TermDTO implements Serializable {

    private Long id;

    private String termsDesc;

    private LocalDate startDate;

    private LocalDate endDate;

    private Status termStatus;


    private Long academicyearId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermsDesc() {
        return termsDesc;
    }

    public void setTermsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(Status termStatus) {
        this.termStatus = termStatus;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicYearId) {
        this.academicyearId = academicYearId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TermDTO termDTO = (TermDTO) o;
        if (termDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), termDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TermDTO{" +
            "id=" + getId() +
            ", termsDesc='" + getTermsDesc() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", termStatus='" + getTermStatus() + "'" +
            ", academicyear=" + getAcademicyearId() +
            "}";
    }
}
