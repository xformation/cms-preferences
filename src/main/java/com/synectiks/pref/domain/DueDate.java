package com.synectiks.pref.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A DueDate.
 */
@Entity
@Table(name = "due_date")
public class DueDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "installments")
    private Long installments;

    @Column(name = "day_desc")
    private String dayDesc;

    @Column(name = "payment_day")
    private Long paymentDay;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "branch_id")
    private Long branchId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public DueDate paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getInstallments() {
        return installments;
    }

    public DueDate installments(Long installments) {
        this.installments = installments;
        return this;
    }

    public void setInstallments(Long installments) {
        this.installments = installments;
    }

    public String getDayDesc() {
        return dayDesc;
    }

    public DueDate dayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
        return this;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }

    public Long getPaymentDay() {
        return paymentDay;
    }

    public DueDate paymentDay(Long paymentDay) {
        this.paymentDay = paymentDay;
        return this;
    }

    public void setPaymentDay(Long paymentDay) {
        this.paymentDay = paymentDay;
    }

    public String getFrequency() {
        return frequency;
    }

    public DueDate frequency(String frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Long getBranchId() {
        return branchId;
    }

    public DueDate branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DueDate)) {
            return false;
        }
        return id != null && id.equals(((DueDate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DueDate{" +
            "id=" + getId() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", installments=" + getInstallments() +
            ", dayDesc='" + getDayDesc() + "'" +
            ", paymentDay=" + getPaymentDay() +
            ", frequency='" + getFrequency() + "'" +
            ", branchId=" + getBranchId() +
            "}";
    }
}
