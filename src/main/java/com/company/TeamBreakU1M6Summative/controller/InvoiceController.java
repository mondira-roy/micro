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
@RequestMapping("/invoice")
public class InvoiceController {

    /**
     * This controller deals with invoice routes
     * ItemViewModel
     */

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@Valid @RequestBody Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return serviceLayer.findAllInvoice();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        return serviceLayer.findInvoiceById(id);

    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerId(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Customer Id must be greater than 0.");
        }

        return serviceLayer.findInvoiceByCustomer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoice(@PathVariable int id, @Valid @RequestBody Invoice invoice) {
        if (id < 1) {
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        serviceLayer.updateInvoice(invoice);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        if (id < 1) {
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        serviceLayer.removeInvoice(id);
    }
}
