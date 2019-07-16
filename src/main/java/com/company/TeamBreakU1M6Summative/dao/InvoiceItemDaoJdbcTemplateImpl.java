package com.company.TeamBreakU1M6Summative.dao;


import com.company.TeamBreakU1M6Summative.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {

        String sql = "insert into invoice_item ( invoice_id, item_id , quantity , unit_rate , discount) "
                +"values (?,?,?,?,?)";

        jdbcTemplate.update(
                sql,
                invoiceItem.getInvoiceId(),
                invoiceItem.getItemId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitRate(),
                invoiceItem.getDiscount()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        invoiceItem.setInvoiceItemId(id);
        return invoiceItem;
    }

    @Override
    public void deleteInvoiceItem(int invoiceItemId) {
        jdbcTemplate.update("delete from invoice_item where invoice_item_id=?",invoiceItemId);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        String sql = "update invoice_item set invoice_id = ?, item_id=? , quantity =?, unit_rate=? , discount=?"
                +" where invoice_item_id =?";
        jdbcTemplate.update(
                sql,
                invoiceItem.getInvoiceId(),
                invoiceItem.getItemId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitRate(),
                invoiceItem.getDiscount(),
                invoiceItem.getInvoiceItemId()
        );
    }

    @Override
    public InvoiceItem getInvoiceItemById(int id) {
        String sql = "select * from invoice_item where invoice_item_id=?";

        try{
            return jdbcTemplate.queryForObject(
                    sql,
                    this::MapTo,
                    id
            );

        } catch (EmptyResultDataAccessException e){

            return null;

        }

    }

    @Override
    public List<InvoiceItem> getAllInvoiceItem() {
        String sql = "select * from invoice_item";
        return jdbcTemplate.query(
                sql,
                this::MapTo
        );
    }

    @Override
    public List<InvoiceItem> getInvoiceItemByInvoiceId(int id) {
        String sql = "select * from invoice_item where invoice_id = ?";
        List<InvoiceItem> invoiceItemList = jdbcTemplate.query(sql, this::MapTo, id);
        return invoiceItemList;
    }

    public InvoiceItem MapTo(ResultSet rs, int rowNum)throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(rs.getInt("invoice_item_id"));
        invoiceItem.setDiscount(rs.getBigDecimal("discount"));
        invoiceItem.setUnitRate(rs.getBigDecimal("unit_rate"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setItemId(rs.getInt("item_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        return invoiceItem;
    }
}
