package com.company.TeamBreakU1M6Summative.dao;

import com.company.TeamBreakU1M6Summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {


    private final String CREATE_INVOICE_SQL = "insert into invoice(customer_id, order_date, pickup_date, return_date, late_fee)" +
                                                "values(?,?,?,?,?)";

    private final String DELETE_INVOICE_SQL = "delete from invoice where invoice_id = ?";

    private final String UPDATE_INVOICE_SQL = "update invoice set order_date = ?, pickup_date = ?, return_date = ?, " +
            "late_fee = ? where invoice_id = ?";

    private final String SELECT_INVOICE_BY_ID_SQL = "select * from invoice where invoice_id = ?";

    private final String SELECT_ALL_INVOICES_SQL = "select * from invoice";

    private final String SELECT_INVOICE_BY_CUSTOMER_SQL = "select * from invoice where customer_id = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        jdbcTemplate.update(CREATE_INVOICE_SQL,
                invoice.getCustomerId(),
                invoice.getOrderDate(),
                invoice.getPickupDate(),
                invoice.getReturnDate(),
                invoice.getLateFee());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public void deleteInvoice(int invoiceId) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceId);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getOrderDate(),
                invoice.getPickupDate(),
                invoice.getReturnDate(),
                invoice.getLateFee(),
                invoice.getInvoiceId());
    }

    @Override
    public Invoice getInvoiceById(int id) {
        try {
            return jdbcTemplate
                    .queryForObject(SELECT_INVOICE_BY_ID_SQL, this::mapRowToInvoice, id);

        } catch (EmptyResultDataAccessException e) {

            return null;

        }


    }

    @Override
    public List<Invoice> getAllInvoice() {
        List<Invoice> invoiceList = jdbcTemplate
                .query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
        return invoiceList;
    }

    @Override
    public List<Invoice> getInvoiceByCustomer(int customerId) {
        try {
            return jdbcTemplate
                    .query(SELECT_INVOICE_BY_CUSTOMER_SQL, this::mapRowToInvoice);

        } catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Invoice mapRowToInvoice(ResultSet rs, int rowNumber) throws SQLException {

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setOrderDate(rs.getDate("order_date").toLocalDate());
        invoice.setPickupDate(rs.getDate("pickup_date").toLocalDate());
        invoice.setReturnDate(rs.getDate("return_date").toLocalDate());
        invoice.setLateFee(rs.getBigDecimal("late_fee"));

        return invoice;

    }
}
