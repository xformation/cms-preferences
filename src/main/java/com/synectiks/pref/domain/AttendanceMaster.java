package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A AttendanceMaster.
 */
@Entity
@Table(name = "attendance_master")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "attendancemaster")
public class AttendanceMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "jhi_desc")
    private String desc;

    @ManyToOne
    @JsonIgnoreProperties("attendanceMasters")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("attendanceMasters")
    private Section section;

    @ManyToOne
    @JsonIgnoreProperties("attendanceMasters")
    private Teach teach;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public AttendanceMaster desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Batch getBatch() {
        return batch;
    }

    public AttendanceMaster batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Section getSection() {
        return section;
    }

    public AttendanceMaster section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Teach getTeach() {
        return teach;
    }

    public AttendanceMaster teach(Teach teach) {
        this.teach = teach;
        return this;
    }

    public void setTeach(Teach teach) {
        this.teach = teach;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttendanceMaster)) {
            return false;
        }
        return id != null && id.equals(((AttendanceMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AttendanceMaster{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
