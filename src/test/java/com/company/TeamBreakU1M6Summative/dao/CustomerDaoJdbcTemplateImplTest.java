package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.InvoiceItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerDaoJdbcTemplateImplTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception {
        List<Item> items = itemDao.getAllItems();
        items.stream()
                .forEach(i -> itemDao.deleteItem(i.getItemId()));

        List<Invoice> invoices = invoiceDao.getAllInvoice();
        invoices.stream()
                .forEach(inv -> invoiceDao.deleteInvoice(inv.getInvoiceId()));

        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItem();
        invoiceItems.stream()
                .forEach();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllCustomers() {
    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void deleteAllCustomers() {
    }
}