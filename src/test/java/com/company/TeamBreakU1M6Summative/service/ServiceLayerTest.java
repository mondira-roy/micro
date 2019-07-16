package com.company.TeamBreakU1M6Summative.service;

import com.company.TeamBreakU1M6Summative.dao.*;
import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.Item;
import org.junit.After;
import org.junit.Before;

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
    InvoiceItemDao invoiceItemDao ;
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpItemDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();

        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);

    }invoiceItemDao

    @After
    public void tearDown() throws Exception {
    }

    private void setUpCustomerDaoMock() {
        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Geo");
        customer.setLastName("Fredo");
        customer.setEmail("geo@gmail.com");
        customer.setCompany("cognizant");
        customer.setPhone("1234567891") ;

        Customer customer1 = new Customer();

        customer1.setFirstName("Geo");
        customer1.setLastName("Fredo");
        customer1.setEmail("geo@gmail.com");
        customer1.setCompany("cognizant");
        customer1.setPhone("1234567891") ;

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
        item.setDailyRate(200.00);

        Item item2 = new Item();
        item2.setName("Iindia");
        item2.setDescription("Team Break Member ");
        item2.setDailyRate(200.00);

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
        invoice.setOrderDate(LocalDate.of(2010,5,5));
        invoice.setPickupDate(LocalDate.of(2010,5,10));
        invoice.setReturnDate(LocalDate.of(2010,5,15));
        invoice.setLateFee(35.77);

        Invoice invoice2 = new Label();
        invoice.setOrderDate(LocalDate.of(2010,5,5));
        invoice.setPickupDate(LocalDate.of(2010,5,10));
        invoice.setReturnDate(LocalDate.of(2010,5,15));
        invoice.setLateFee(35.77);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).createInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getLabel(10);
        doReturn(invoiceList).when(invoiceDao).getAllLabels();
    }

    private void setUpInvoiceItemDaoMock() {
        trackDao = mock(TrackDaoJdbcTemplateImpl.class);
        Track track = new Track();
        track.setId(1);
        track.setAlbumId(1);
        track.setRunTime(180);
        track.setTitle("Number 1 Hit!");

        Track track2 = new Track();
        track.setAlbumId(1);
        track.setRunTime(180);
        track.setTitle("Number 1 Hit!");

        List<Track> tList = new ArrayList<>();
        tList.add(track);

        doReturn(track).when(trackDao).addTrack(track2);
        doReturn(track).when(trackDao).getTrack(1);
        doReturn(tList).when(trackDao).getAllTracks();
        doReturn(tList).when(trackDao).getTracksByAlbum(1);
    }
}