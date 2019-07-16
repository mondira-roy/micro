package com.company.TeamBreakU1M6Summative.service;

import com.company.TeamBreakU1M6Summative.dao.*;
import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.InvoiceItem;
import com.company.TeamBreakU1M6Summative.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


public class ServiceLayerTest {
    ServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpItemDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();

        service = new ServiceLayer(customerDao, invoiceDao, itemDao, invoiceItemDao);

    }

    // need service layer test methods

        private void setUpCustomerDaoMock() {
        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Geo");
        customer.setLastName("Fredo");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("cognizant");
        customer.setPhone("1234567891");

        Customer customer1 = new Customer();

        customer1.setFirstName("Geo");
        customer1.setLastName("Fredo");
        customer1.setEmail("geo@gmail.com");
        customer1.setCompany("cognizant");
        customer1.setPhone("1234567891");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerDao).createCustomer(customer1);
        doReturn(customer).when(customerDao).getCustomer(1);
        doReturn(customerList).when(customerDao).getAllCustomers();
    }

    private void setUpItemDaoMock() {
        itemDao = mock(ItemJdbcTemplateImpl.class);
        Item item = new Item();
        item.setItemId(5);
        item.setName("Iindia");
        item.setDescription("Team Break Member ");
        item.setDailyRate(new BigDecimal("3.5").setScale(2));

        Item item2 = new Item();
        item2.setName("Iindia");
        item2.setDescription("Team Break Member ");
        item2.setDailyRate(new BigDecimal("3.5").setScale(2));

        List<Item> itemList = new ArrayList();
        itemList.add(item);

        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(5);
        doReturn(itemList).when(itemDao).getAllItems();
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(10);
        invoice.setCustomerId(1);
        invoice.setOrderDate(LocalDate.of(2010, 5, 5));
        invoice.setPickupDate(LocalDate.of(2010, 5, 10));
        invoice.setReturnDate(LocalDate.of(2010, 5, 15));
        invoice.setLateFee(new BigDecimal("3.5").setScale(2));

        Invoice invoice2 = new Invoice();
        invoice.setOrderDate(LocalDate.of(2010, 5, 5));
        invoice.setPickupDate(LocalDate.of(2010, 5, 10));
        invoice.setReturnDate(LocalDate.of(2010, 5, 15));
        invoice.setLateFee(new BigDecimal("3.5").setScale(2));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).createInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoiceById(10);
        doReturn(invoice).when(invoiceDao).getInvoiceByCustomer(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoice();
    }

    private void setUpInvoiceItemDaoMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);
        InvoiceItem invoiceItem = new InvoiceItem();

        invoiceItem.setInvoiceItemId(45);
        invoiceItem.setInvoiceId(10);
        invoiceItem.setItemId(5);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitRate(new BigDecimal("3.5").setScale(2));
        invoiceItem.setDiscount(new BigDecimal("3.5").setScale(2));

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(10);
        invoiceItem2.setItemId(5);
        invoiceItem2.setQuantity(100);
        invoiceItem2.setUnitRate(new BigDecimal("3.5").setScale(2));
        invoiceItem2.setDiscount(new BigDecimal("3.5").setScale(2));

        List<InvoiceItem> invItemList = new ArrayList<>();
        invItemList.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).createInvoiceItem(invoiceItem2);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItemById(1);
        doReturn(invItemList).when(invoiceItemDao).getAllInvoiceItem();

    }
}