package com.company.TeamBreakU1M6Summative.controller;


import com.company.TeamBreakU1M6Summative.dao.InvoiceDao;
import com.company.TeamBreakU1M6Summative.dao.InvoiceItemDao;
import com.company.TeamBreakU1M6Summative.model.Invoice;
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
    InvoiceDao invoiceDao;

    @Autowired
    InvoiceItemDao invoiceItemDao;

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice createInvoice(@Valid @RequestBody Invoice invoice) {
        return invoiceDao.createInvoice(invoice);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoice();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        return invoiceDao.getInvoiceById(id);

    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerId(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Customer Id must be greater than 0.");
        }

        return invoiceDao.getInvoiceByCustomer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateInvoice(@PathVariable int id, @Valid @RequestBody Invoice invoice) {
        if (id < 1) {
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        invoiceDao.updateInvoice(invoice);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        if (id < 1) {
            throw new IllegalArgumentException("Invoice Id must be greater than 0.");
        }

        invoiceDao.deleteInvoice(id);
    }
}
