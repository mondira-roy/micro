package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Invoice;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

    protected final String CREATE_INVOICE_SQL = "insert into invoice(customer_id, order_date, pickup_date, return_date, late_fee)" +
                                                "values(?,?,?,?,?);";
    protected final String DELETE_INVOICE_SQL = "delete from invoice where invoice_id = ?";

    protected final String 

    JdbcTemplate jdbcTemplate;

    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        jdbcTemplate.update()
        return null;
    }

    @Override
    public void deleteInvoice(int invoiceId) {

    }

    @Override
    public void updateInvoice(Invoice invoice) {

    }

    @Override
    public Invoice getInvoiceById(int id) {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return null;
    }

    @Override
    public List<Invoice> getInvoiceByCustomer(int customerId) {
        return null;
    }
}
