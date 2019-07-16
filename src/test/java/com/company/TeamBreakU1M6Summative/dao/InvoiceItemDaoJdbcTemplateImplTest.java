package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.InvoiceItem;
import com.company.TeamBreakU1M6Summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceItemDaoJdbcTemplateImplTest {

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
        //clean up the test
        List<Item> itemList = itemDao.getAllItems();
        for (Item i : itemList){
            itemDao.deleteItem(i.getItemId());
        }

        List<Invoice> invoiceList = invoiceDao.getAllInvoice();

        for (Invoice i : invoiceList){
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItem();

        for (InvoiceItem i : invoiceItemList) {
            invoiceItemDao.deleteInvoiceItem(i.getInvoiceItemId());
        }

        List<Customer> customerList = customerDao.getAllCustomers();

        for (Customer c : customerList){
            customerDao.deleteCustomer(c.getCustomerId());
        }
    }

    @Test
    public void testAddGetByIdGetAllUpdateDelete(){
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
        invoice.setLateFee(new BigDecimal("3.5").setScale(2));
        invoice = invoiceDao.createInvoice(invoice);

        Item item = new Item();
        item.setName("Shirt");
        item.setDescription("Red shirt.");
        item.setDailyRate(new BigDecimal("3.5").setScale(2));
        item = itemDao.addItem(item);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitRate(new BigDecimal("1.12").setScale(2));
        invoiceItem.setDiscount(new BigDecimal("0.82").setScale(2));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem2 = invoiceItemDao.getInvoiceItemById(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem, invoiceItem2);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        invoiceItem2 = invoiceItemDao.getInvoiceItemById(invoiceItem.getInvoiceItemId());

        assertNull(invoiceItem2);
    }
}