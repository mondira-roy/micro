package com.company.TeamBreakU1M6Summative.controller;


import com.company.TeamBreakU1M6Summative.dao.CustomerDao;
import com.company.TeamBreakU1M6Summative.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    /**
     * This controller deals with customer routes
     * Only Customer obj
     */

    @Autowired
    CustomerDao customerDao;

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerDao.createCustomer(customer);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomerById(@PathVariable int id) {

            if(id<1){
                throw new IllegalArgumentException("Customer Id must be greater than 0.");
            }

            return customerDao.getCustomer(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customer) {
        if (id < 1) {
            throw new IllegalArgumentException("Customer Id must be greater than 0.");
        }

            customerDao.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        if (id < 1) {
            throw new IllegalArgumentException("Customer Id must be greater than 0.");
        }

        customerDao.deleteCustomer(id);
    }

}
