package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AbstractContextLoader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invoiceList = invoiceDao.getAllInvoice();

        invoiceList
                .stream().forEach(i -> invoiceDao.deleteInvoice(i.getInvoiceId()));

        List<Customer> customerList = customerDao.getAllCustomers();

        customerList
                .stream().forEach(c -> customerDao.deleteCustomer(c.getCustomerId()));   //Customer class not created yet 10:06am 7/15

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteInvoice(){
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Johnson");
        customer.setEmail("gj@gmail.com");
        customer.setCompany("Sony");
        customer.setPhone("937-628-3947");
        customer = customerDao.createCustomer(customer);

        Invoice invoice = new Invoice();

        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 7, 15));
        invoice.setPickupDate(LocalDate.of(2019, 7, 30));
        invoice.setReturnDate(LocalDate.of(2019, 8, 5));
        invoice.setLateFee(2.00);
        invoice = invoiceDao.createInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoiceById(invoice.getCustomerId());

        assertEquals(invoice, invoice2);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice2 = invoiceDao.getInvoiceById(invoice.getInvoiceId());

        assertNull(invoice2);


    }

    @Test
    public void updateInvoice() {
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Johnson");
        customer.setEmail("gj@gmail.com");
        customer.setCompany("Sony");
        customer.setPhone("937-628-3947");
        customer = customerDao.createCustomer(customer);

        Invoice invoice = new Invoice();

        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 7, 15));
        invoice.setPickupDate(LocalDate.of(2019, 7, 30));
        invoice.setReturnDate(LocalDate.of(2019, 8, 5));
        invoice.setLateFee(2.00);
        invoice = invoiceDao.createInvoice(invoice);

        invoice.setLateFee(5.00);

        invoiceDao.updateInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoiceById(invoice.getCustomerId());

        assertEquals(invoice, invoice2);

    }

    @Test
    public void getAllInvoice() {
        List<Invoice> invoiceList = new ArrayList<>();

        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Johnson");
        customer.setEmail("gj@gmail.com");
        customer.setCompany("Sony");
        customer.setPhone("937-628-3947");
        customer = customerDao.createCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 7, 15));
        invoice.setPickupDate(LocalDate.of(2019, 7, 30));
        invoice.setReturnDate(LocalDate.of(2019, 8, 5));
        invoice.setLateFee(2.00);
        invoice = invoiceDao.createInvoice(invoice);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jake");
        customer2.setLastName("Sawyer");
        customer2.setEmail("jsawyer@gmail.com");
        customer2.setCompany("Cognizant");
        customer2.setPhone("953-465-3049");
        customer2 = customerDao.createCustomer(customer);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(customer.getCustomerId());
        invoice2.setOrderDate(LocalDate.of(2019, 6, 25));
        invoice2.setPickupDate(LocalDate.of(2019, 7, 4));
        invoice.setReturnDate(LocalDate.of(2019, 7, 9));
        invoice2.setLateFee(2.00);
        invoice2 = invoiceDao.createInvoice(invoice);

        invoiceList = invoiceDao.getAllInvoice();

        assertEquals(2, invoiceList.size());

    }

    @Test
    public void getInvoiceByCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Johnson");
        customer.setEmail("gj@gmail.com");
        customer.setCompany("Sony");
        customer.setPhone("937-628-3947");
        customer = customerDao.createCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 7, 15));
        invoice.setPickupDate(LocalDate.of(2019, 7, 30));
        invoice.setReturnDate(LocalDate.of(2019, 8, 5));
        invoice.setLateFee(2.00);
        invoice = invoiceDao.createInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoiceById(customer.getCustomerId());

        assertEquals(invoice, invoice2);
    }
}