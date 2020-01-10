package com.synectiks.pref.service.dto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.pref.domain.enumeration.Status;

/**
 * A DTO for the {@link com.synectiks.pref.domain.AcademicYear} entity.
 */
public class AcademicYearDTO implements Serializable {

    private Long id;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String comments;

    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AcademicYearDTO academicYearDTO = (AcademicYearDTO) o;
        if (academicYearDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicYearDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicYearDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", comments='" + getComments() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
