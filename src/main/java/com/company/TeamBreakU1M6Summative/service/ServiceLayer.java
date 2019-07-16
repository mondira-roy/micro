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

        // Persist Album
        Invoice invoice = new Invoice();
//        invoice.setInvoiceId(viewModel.getInvoiceId());
        invoice.setCustomerId(viewModel.getCustomer().getCustomerId());
        invoice.setOrderDate(viewModel.getOrderDate());
        invoice.setPickupDate(viewModel.getPickupDate());
        invoice.setReturnDate(viewModel.getReturnDate());
        invoice.setLateFee(viewModel.getLateFee());
        invoice = invoiceDao.createInvoice(invoice);
        viewModel.setInvoiceId(invoice.getInvoiceId());

        // Add Album Id to Tracks and Persist Tracks
        List<Track> tracks = viewModel.getTracks();

        tracks.stream()
                .forEach(t ->
                {
                    t.setAlbumId(viewModel.getId());
                    trackDao.addTrack(t);
                });

        tracks = trackDao.getTracksByAlbum(viewModel.getId());
        viewModel.setTracks(tracks);

        return viewModel;
    }

    public AlbumViewModel findAlbum(int id) {

        // Get the album object first
        Album album = albumDao.getAlbum(id);

        return buildAlbumViewModel(album);

    }

    public List<AlbumViewModel> findAllAlbums() {

        List<Album> albumList = albumDao.getAllAlbums();

        List<AlbumViewModel> avmList = new ArrayList<>();

        for (Album album : albumList) {
            AlbumViewModel avm = buildAlbumViewModel(album);
            avmList.add(avm);
        }

        return avmList;
    }

    @Transactional
    public void updateAlbum(AlbumViewModel viewModel) {

        // Update the album information
        Album album = new Album();
        album.setId(viewModel.getId());
        album.setArtistId(viewModel.getArtist().getId());
        album.setLabelId(viewModel.getLabel().getId());
        album.setListPrice(viewModel.getListPrice());
        album.setReleaseDate(viewModel.getReleaseDate());

        albumDao.updateAlbum(album);

        // We don't know if any track have been removed so delete all associated tracks
        // and then associate the tracks in the viewModel with the album
        List<Track> trackList = trackDao.getTracksByAlbum(album.getId());
        trackList.stream()
                .forEach(track -> trackDao.deleteTrack(track.getId()));

        List<Track> tracks = viewModel.getTracks();
        tracks.stream()
                .forEach(t ->
                {
                    t.setAlbumId(viewModel.getId());
                    t = trackDao.addTrack(t);
                });
    }

    @Transactional
    public void removeAlbum(int id) {

        // Remove all associated tracks first
        List<Track> trackList = trackDao.getTracksByAlbum(id);

        trackList.stream()
                .forEach(track -> trackDao.deleteTrack(track.getId()));

        // Remove album
        albumDao.deleteAlbum(id);

    }

    // Helper Methods
    private AlbumViewModel buildAlbumViewModel(Album album) {

        // Get the associated artist
        Artist artist = artistDao.getArtist(album.getArtistId());

        // Get the associated label
        Label label = labelDao.getLabel(album.getLabelId());

        // Get the tracks associated with the album
        List<Track> trackList = trackDao.getTracksByAlbum(album.getId());

        // Assemble the AlbumViewModel
        AlbumViewModel avm = new AlbumViewModel();
        avm.setId(album.getId());
        avm.setTitle(album.getTitle());
        avm.setReleaseDate(album.getReleaseDate());
        avm.setListPrice(album.getListPrice());
        avm.setArtist(artist);
        avm.setLabel(label);
        avm.setTracks(trackList);

        // Return the AlbumViewModel
        return avm;

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
