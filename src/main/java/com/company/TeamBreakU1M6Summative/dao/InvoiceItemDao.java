package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    // create invoiceItem when invoice created
    InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);
    // delete invoiceItem when invoice is deleted
    void deleteInvoiceItem(int invoiceItemId);
    // update invoiceItem when invoice is updated
    void updateInvoiceItem(InvoiceItem invoiceItem);
    // read invoiceItem by Id
    InvoiceItem getInvoiceItemById(int id);
    // read all invoiceItem
    List<InvoiceItem> getAllInvoiceItem();

    List<InvoiceItem> getInvoiceItemByInvoiceId(int id);

}
