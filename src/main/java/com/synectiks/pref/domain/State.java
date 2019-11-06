package com.synectiks.pref.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A State.
 */
@Entity
@Table(name = "state")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "division_type")
    private String divisionType;

    @Column(name = "state_code")
    private String stateCode;

    @ManyToOne
    @JsonIgnoreProperties("states")
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public State stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDivisionType() {
        return divisionType;
    }

    public State divisionType(String divisionType) {
        this.divisionType = divisionType;
        return this;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }

    public String getStateCode() {
        return stateCode;
    }

    public State stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Country getCountry() {
        return country;
    }

    public State country(Country country) {
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
        if (!(o instanceof State)) {
            return false;
        }
        return id != null && id.equals(((State) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "State{" +
            "id=" + getId() +
            ", stateName='" + getStateName() + "'" +
            ", divisionType='" + getDivisionType() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            "}";
    }
}
