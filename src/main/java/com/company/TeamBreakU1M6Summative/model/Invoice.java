package com.company.TeamBreakU1M6Summative.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
    private int invoiceId;
    @NotEmpty(message = "You must supply a value for customerId.")
    private int customerId;
    @NotEmpty(message = "You must supply a value for orderDate.")
    private LocalDate orderDate;
    @NotEmpty(message = "You must supply a value for pickupDate.")
    private LocalDate pickupDate;
    @NotEmpty(message = "You must supply a value for returnDate.")
    private LocalDate returnDate;
    @NotEmpty(message = "You must supply a value for lateFee.")
    private double lateFee;


    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getCustomerId() == invoice.getCustomerId() &&
                Double.compare(invoice.getLateFee(), getLateFee()) == 0 &&
                getOrderDate().equals(invoice.getOrderDate()) &&
                Objects.equals(getPickupDate(), invoice.getPickupDate()) &&
                Objects.equals(getReturnDate(), invoice.getReturnDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getCustomerId(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee());
    }
}
