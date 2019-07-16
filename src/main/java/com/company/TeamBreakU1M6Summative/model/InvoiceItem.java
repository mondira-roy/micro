package com.company.TeamBreakU1M6Summative.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

public class InvoiceItem {

//    create table if not exists invoice_item (
//    invoice_item_id int(11) not null auto_increment primary key,
//    invoice_id int(11) not null,
//    item_id int(11) not null,
//    quantity int(11) not null,
//    unit_rate decimal(8,2) not null,
//    discount decimal(8.2) not null
//    );

    private int invoiceItemId;
    @NotEmpty(message = "You must supply a value for invoiceId.")
    private int invoiceId;
    @NotEmpty(message = "You must supply a value for itemId.")
    private int itemId;
    @NotEmpty(message = "You must supply a value for quantity.")
    private int quantity;
    @NotEmpty(message = "You must supply a value for unitRate.")
    private double unitRate;
    @NotEmpty(message = "You must supply a value for discount.")
    private double discount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return getInvoiceItemId() == that.getInvoiceItemId() &&
                getInvoiceId() == that.getInvoiceId() &&
                getItemId() == that.getItemId() &&
                getQuantity() == that.getQuantity() &&
                Double.compare(that.getUnitRate(), getUnitRate()) == 0 &&
                Double.compare(that.getDiscount(), getDiscount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceItemId(), getInvoiceId(), getItemId(), getQuantity(), getUnitRate(), getDiscount());
    }

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(double unitRate) {
        this.unitRate = unitRate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
