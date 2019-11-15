package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A AuthorizedSignatory.
 */
@Entity
@Table(name = "authorized_signatory")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "authorizedsignatory")
public class AuthorizedSignatory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "pan_no")
    private String panNo;

    @ManyToOne
    @JsonIgnoreProperties("authorizedSignatories")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AuthorizedSignatory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public AuthorizedSignatory fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDesignation() {
        return designation;
    }

    public AuthorizedSignatory designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public AuthorizedSignatory address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public AuthorizedSignatory email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPanNo() {
        return panNo;
    }

    public AuthorizedSignatory panNo(String panNo) {
        this.panNo = panNo;
        return this;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Branch getBranch() {
        return branch;
    }

    public AuthorizedSignatory branch(Branch branch) {
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
        if (!(o instanceof AuthorizedSignatory)) {
            return false;
        }
        return id != null && id.equals(((AuthorizedSignatory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AuthorizedSignatory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", panNo='" + getPanNo() + "'" +
            "}";
    }
}
