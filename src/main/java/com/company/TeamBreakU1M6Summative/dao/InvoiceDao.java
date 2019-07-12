package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    // create invoiceItem when invoice created
    Invoice createInvoice(Invoice invoiceItem);

    //    // delete invoiceItem when invoice is deleted
        void deleteInvoice(int invoiceItemId);

    //    // update invoiceItem when invoice is updated
    void updateInvoice(Invoice invoiceItem);

    // read invoiceItem by Id
    Invoice getInvoiceById(int id);

    // read all invoiceItem
    List<Invoice> getAllInvoice();

    // returns invoices by customer_id
    List<Invoice> getInvoiceByCustomer(int customerId);
}
