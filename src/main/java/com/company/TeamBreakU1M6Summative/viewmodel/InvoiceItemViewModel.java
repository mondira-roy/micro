package com.company.TeamBreakU1M6Summative.viewmodel;

import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.Item;

import java.util.Objects;

public class InvoiceItemViewModel {
    private int invoiceItemId;
    private Invoice invoice;
    private Item item;
    private int quantity;
    private double unitRate;
    private double discount;

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItemViewModel that = (InvoiceItemViewModel) o;
        return getInvoiceItemId() == that.getInvoiceItemId() &&
                getQuantity() == that.getQuantity() &&
                Double.compare(that.getUnitRate(), getUnitRate()) == 0 &&
                Double.compare(that.getDiscount(), getDiscount()) == 0 &&
                getInvoice().equals(that.getInvoice()) &&
                getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceItemId(), getInvoice(), getItem(), getQuantity(), getUnitRate(), getDiscount());
    }
}
