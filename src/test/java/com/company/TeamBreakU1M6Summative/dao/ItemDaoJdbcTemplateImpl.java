package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.InvoiceItem;
import com.company.TeamBreakU1M6Summative.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoJdbcTemplateImpl {

    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    CustomerDao customerDao;


    @Before
    public  void setUp() throws Exception {
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

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void addItem(){

        Item item = new Item();
        item.setName("Shirt");
        item.setDescription("Red shirt.");
        item.setDailyRate(10.99);

        item = itemDao.addItem(item);

        Item item2 = itemDao.getItem(item.getItemId());

        assertEquals(item, item2);

    }
    @Test
    public void getItem(){

        Item item = new Item();
        item.setName("Shirt");
        item.setDescription("Red shirt.");
        item.setDailyRate(10.99);

        item = itemDao.addItem(item);

        Item item2 = itemDao.getItem(item.getItemId());

        assertEquals(item ,item2);
    }
    @Test
    public void deleteItem(){

        Item item = new Item();
        item.setName("Shirt");
        item.setDescription("Red shirt.");
        item.setDailyRate(10.99);

        item = itemDao.addItem(item);

        itemDao.deleteItem(item.getItemId());

        Item item2 = itemDao.getItem(item.getItemId());

        assertNull(item2);

    }

    @Test
    public void addGetDeleteItem(){
        Item item = new Item();
        item.setName("Shirt");
        item.setDescription("Red shirt.");
        item.setDailyRate(10.99);

        item = itemDao.addItem(item);

        Item item2 = itemDao.getItem(item.getItemId());

        assertEquals(item, item2);

        itemDao.deleteItem(item.getItemId());

        item2 = itemDao.getItem(item.getItemId());

        assertNull(item2);


    }

    @Test
    public void getAllItems(){

        Item item = new Item();
        item.setName("Shirt");
        item.setDescription("Red shirt.");
        item.setDailyRate(10.99);

        itemDao.addItem(item);

        item= new Item();
        item.setName("Pants");
        item.setDescription("Black pants.");
        item.setDailyRate(15.99);


        itemDao.addItem(item);


        List<Item> itemList = itemDao.getAllItems();

        assertEquals(itemList.size(), 2);
    }

    @Test
    public void updateItem(){
        Item item = new Item();
        item.setName("Shirt");
        item.setDescription("Red shirt.");
        item.setDailyRate(10.99);

        itemDao.addItem(item);

        item.setDescription("DAMAGED");
        item.setDailyRate(4.99);

        itemDao.updateItem(item);

        Item item2 = itemDao.getItem(item.getItemId());

        assertEquals(item2, item);

    }
}


