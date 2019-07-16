package com.company.TeamBreakU1M6Summative.viewmodel;

import com.company.TeamBreakU1M6Summative.model.Customer;

import java.time.LocalDate;
import java.util.Objects;

public class InvoiceViewModel {

    private int invoiceId;
    private Customer customer;
    private LocalDate orderDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private double lateFee;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getInvoiceId() == that.getInvoiceId() &&
                Double.compare(that.getLateFee(), getLateFee()) == 0 &&
                getCustomer().equals(that.getCustomer()) &&
                getOrderDate().equals(that.getOrderDate()) &&
                getPickupDate().equals(that.getPickupDate()) &&
                getReturnDate().equals(that.getReturnDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getCustomer(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee());
    }
}
