package com.company.TeamBreakU1M6Summative.controller;

import com.company.TeamBreakU1M6Summative.model.Invoice;
import com.company.TeamBreakU1M6Summative.service.ServiceLayer;
import com.company.TeamBreakU1M6Summative.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {

    /**
     * This controller deals with invoice routes
     * ItemViewModel
     */

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value=  "/invoice",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@Valid @RequestBody InvoiceViewModel invoiceViewModel) {
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

    @RequestMapping(value=  "/invoice",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }

    @RequestMapping(value=  "/invoice/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        return serviceLayer.findInvoice(id);

    }

    @RequestMapping(value=  "/invoice/customer/{id}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getInvoiceByCustomerId(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Customer Id must be greater than 0.");
        }

        return serviceLayer.findInvoiceByCustomer(id);
    }

    @RequestMapping(value=  "/invoice/{id}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoice(@PathVariable int id, @Valid @RequestBody InvoiceViewModel invoiceViewModel) {
        if (id < 1) {
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        serviceLayer.updateInvoice(invoiceViewModel);
    }

    @RequestMapping(value=  "/invoice/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEmployee(@PathVariable int id){
        if (id < 1) {
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        serviceLayer.removeInvoice(id);
    }
}
