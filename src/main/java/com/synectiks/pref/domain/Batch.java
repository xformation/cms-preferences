package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import com.synectiks.pref.domain.enumeration.BatchEnum;

/**
 * A Batch.
 */
@Entity
@Table(name = "batch")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "batch")
public class Batch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "batch")
    private BatchEnum batch;

    @ManyToOne
    @JsonIgnoreProperties("batches")
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BatchEnum getBatch() {
        return batch;
    }

    public Batch batch(BatchEnum batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(BatchEnum batch) {
        this.batch = batch;
    }

    public Department getDepartment() {
        return department;
    }

    public Batch department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Batch)) {
            return false;
        }
        return id != null && id.equals(((Batch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Batch{" +
            "id=" + getId() +
            ", batch='" + getBatch() + "'" +
            "}";
    }
}
