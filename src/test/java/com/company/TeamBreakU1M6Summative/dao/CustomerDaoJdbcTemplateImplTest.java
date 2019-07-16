package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.InvoiceItem;
import com.company.TeamBreakU1M6Summative.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
                .forEach(ii -> invoiceItemDao.deleteInvoiceItem(ii.getInvoiceItemId()));

        List<Customer> customers = customerDao.getAllCustomers();
        customers.stream()
                .forEach(c -> customerDao.deleteCustomer(c.getCustomerId()));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteCustomer() {
        Customer customer1 = new Customer();
        customer1.setFirstName("George");
        customer1.setLastName("Johnson");
        customer1.setEmail("gj@gmail.com");
        customer1.setCompany("Sony");
        customer1.setPhone("937-628-3947");
        customer1 = customerDao.createCustomer(customer1);

        Customer customerFromDb = customerDao.getCustomer(customer1.getCustomerId());

        assertEquals(customer1, customerFromDb);

        customerDao.deleteCustomer(customer1.getCustomerId());

        customerFromDb = customerDao.getCustomer(customer1.getCustomerId());

        assertNull(customerFromDb);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setFirstName("George");
        customer1.setLastName("Johnson");
        customer1.setEmail("gj@gmail.com");
        customer1.setCompany("Sony");
        customer1.setPhone("937-628-3947");
        customer1 = customerDao.createCustomer(customer1);
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jake");
        customer2.setLastName("Paul");
        customer2.setEmail("jpaul@gmail.com");
        customer2.setCompany("Honda");
        customer2.setPhone("222-555-5555");
        customer2 = customerDao.createCustomer(customer2);
        customers.add(customer2);

        List<Customer> customersFromDb = customerDao.getAllCustomers();

        assertEquals(customers, customersFromDb);

    }

    @Test
    public void updateCustomer() {
        Customer customer1 = new Customer();
        customer1.setFirstName("George");
        customer1.setLastName("Johnson");
        customer1.setEmail("gj@gmail.com");
        customer1.setCompany("Sony");
        customer1.setPhone("937-628-3947");
        customer1 = customerDao.createCustomer(customer1);

        customer1.setEmail("georgej@aol.com");
        customer1.setPhone("686-404-5867");

        customerDao.updateCustomer(customer1);
        Customer customerFromDb = customerDao.getCustomer(customer1.getCustomerId());

        assertEquals(customer1, customerFromDb);


    }
}

//    @Test
//    public void createCustomer() {
//        Customer customer1 = new Customer();
//        customer1.setFirstName("George");
//        customer1.setLastName("Johnson");
//        customer1.setEmail("gj@gmail.com");
//        customer1.setCompany("Sony");
//        customer1.setPhone("937-628-3947");
//        customer1 = customerDao.createCustomer(customer1);
//    }
//
//    @Test
//    public void getCustomer() {
//        Customer customer1 = new Customer();
//        customer1.setFirstName("George");
//        customer1.setLastName("Johnson");
//        customer1.setEmail("gj@gmail.com");
//        customer1.setCompany("Sony");
//        customer1.setPhone("937-628-3947");
//        customer1 = customerDao.createCustomer(customer1);
//        Customer customerFromDb = customerDao.getCustomer(customer1.getCustomerId());
//
//        assertEquals(customer1, customerFromDb);
//
//    }
//
//
//
//
//
//    @Test
//    public void deleteCustomer() {
//        Customer customer1 = new Customer();
//        customer1.setFirstName("George");
//        customer1.setLastName("Johnson");
//        customer1.setEmail("gj@gmail.com");
//        customer1.setCompany("Sony");
//        customer1.setPhone("937-628-3947");
//        customer1 = customerDao.createCustomer(customer1);
//    }
//}