package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import com.synectiks.pref.domain.enumeration.SectionEnum;

/**
 * A Section.
 */
@Entity
@Table(name = "section")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "section")
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "section")
    private SectionEnum section;

    @ManyToOne
    @JsonIgnoreProperties("sections")
    private Batch batch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SectionEnum getSection() {
        return section;
    }

    public Section section(SectionEnum section) {
        this.section = section;
        return this;
    }

    public void setSection(SectionEnum section) {
        this.section = section;
    }

    public Batch getBatch() {
        return batch;
    }

    public Section batch(Batch batch) {
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
        if (!(o instanceof Section)) {
            return false;
        }
        return id != null && id.equals(((Section) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Section{" +
            "id=" + getId() +
            ", section='" + getSection() + "'" +
            "}";
    }
}
