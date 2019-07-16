package com.company.TeamBreakU1M6Summative.service;

import com.company.TeamBreakU1M6Summative.dao.CustomerDao;
import com.company.TeamBreakU1M6Summative.dao.InvoiceDao;
import com.company.TeamBreakU1M6Summative.dao.InvoiceItemDao;
import com.company.TeamBreakU1M6Summative.dao.ItemDao;
import com.company.TeamBreakU1M6Summative.model.Customer;
import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.model.InvoiceItem;
import com.company.TeamBreakU1M6Summative.model.Item;
import com.company.TeamBreakU1M6Summative.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private ItemDao itemDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, ItemDao itemDao,
                        InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    //
    // Invoice API
    //
    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel) {

        // Persist Invoice
        Invoice invoice = new Invoice();
//        invoice.setInvoiceId(viewModel.getInvoiceId());
        invoice.setCustomerId(viewModel.getCustomer().getCustomerId());
        invoice.setOrderDate(viewModel.getOrderDate());
        invoice.setPickupDate(viewModel.getPickupDate());
        invoice.setReturnDate(viewModel.getReturnDate());
        invoice.setLateFee(viewModel.getLateFee());
        invoice = invoiceDao.createInvoice(invoice);
        viewModel.setInvoiceId(invoice.getInvoiceId());

//         Add Album Id to Tracks and Persist Tracks
        List<InvoiceItem> invItemList = viewModel.getInvoiceItemsList();

        List<InvoiceItem> newList = new ArrayList<>();

        invItemList.stream()
                .forEach(t ->
                {
                    t.setInvoiceId(viewModel.getInvoiceId());
                    t = invoiceItemDao.createInvoiceItem(t);
                });

//        invItemList = invoiceItemDao.getIn(viewModel.getId());
////        viewModel.setTracks(tracks);

        viewModel.setInvoiceItemsList(invItemList);

        return viewModel;
    }

    public InvoiceViewModel findInvoice(int id) {

        // Get the album object first
        Invoice invoice = invoiceDao.getInvoiceById(id);

        return buildInvoiceViewModel(invoice);

    }

    public List<InvoiceViewModel> findAllInvoices() {

        List<Invoice> invoiceList = invoiceDao.getAllInvoice();

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for (Invoice ivm : invoiceList) {
            InvoiceViewModel invoiceViewModel = buildInvoiceViewModel(ivm);
            ivmList.add(invoiceViewModel);
        }

        return ivmList;
    }

    @Transactional
    public void updateInvoice(InvoiceViewModel viewModel) {

        // Update the album information
        Invoice invoice = new Invoice();

//        invoice.setCustomerId(viewModel.getCustomer().getCustomerId());
        invoice.setOrderDate(viewModel.getOrderDate());
        invoice.setPickupDate(viewModel.getPickupDate());
        invoice.setReturnDate(viewModel.getReturnDate());
        invoice.setLateFee(viewModel.getLateFee());
        invoiceDao.updateInvoice(invoice);
//
////
////        // We don't know if any track have been removed so delete all associated tracks
////        // and then associate the tracks in the viewModel with the album
//        List<InvoiceItem> invoiceItemsList = viewModel.getInvoiceItemsList();
//        invoiceItemsList.stream()
//                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));
//
//        List<InvoiceItem> tracks = viewModel.getTracks();
//        tracks.stream()
//                .forEach(t ->
//                {
//                    t.setAlbumId(viewModel.getId());
//                    t = trackDao.addTrack(t);
//                });
    }

    @Transactional
    public void removeInvoice(int id) {

        // Remove all associated tracks first
//        List<Track> trackList = trackDao.getTracksByAlbum(id);
//
//        trackList.stream()
//                .forEach(track -> trackDao.deleteTrack(track.getId()));

        // Remove invoice
        invoiceDao.deleteInvoice(id);

    }

    private List<InvoiceViewModel> findInvoiceByCustomer(int id){
        List<Invoice> invoicesByCustomerId = invoiceDao.getInvoiceByCustomer(id);
        List<InvoiceViewModel> ivmList = new ArrayList<>();
        invoicesByCustomerId.stream()
                .forEach(i ->ivmList.add(buildInvoiceViewModel(i)));

        return ivmList;
    }

    // Helper Methods
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        // Get the associated artist
//        Customer customer = customerDao.getCustomer(invoice.getCustomerId());

        // Get the associated label
//        Label label = labelDao.getLabel(album.getLabelId());

        // Get the tracks associated with the album
//        List<Track> trackList = trackDao.getTracksByAlbum(album.getId());

        // Assemble the InvoiceViewModel
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setCustomer(customerDao.getCustomer(invoice.getCustomerId()));
        invoiceViewModel.setOrderDate(invoice.getOrderDate());
        invoiceViewModel.setPickupDate(invoice.getPickupDate());
        invoiceViewModel.setReturnDate(invoice.getReturnDate());
        invoiceViewModel.setLateFee(invoice.getLateFee());

        // Return the InvoiceViewModel
        return invoiceViewModel;

    }

    //
    // Customer API
    //

    public Customer saveCustomer(Customer customer) {

        return customerDao.createCustomer(customer);
    }

    public Customer findCustomer(int id) {

        return customerDao.getCustomer(id);
    }

    public List<Customer> findAllCustomers() {

        return customerDao.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {

        customerDao.updateCustomer(customer);
    }

    public void removeCustomer(int id) {

        customerDao.deleteCustomer(id);
    }

    //
    // Item API
    //

    public Item saveItem(Item item) {

        return itemDao.addItem(item);
    }

    public Item findItem(int id) {

        return itemDao.getItem(id);
    }

    public List<Item> findAllItems() {

        return itemDao.getAllItems();
    }

    public void updateItem(Item item) {

        itemDao.updateItem(item);
    }

    public void removeItem(int id) {

        itemDao.deleteItem(id);
    }


}
