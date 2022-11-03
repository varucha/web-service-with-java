/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbs.customer.database;

import com.pbs.customer.model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Arucha - varucha@hightech-corp.com - PBS
 */
public class CustomerDAO extends DbConnection {

    public Customer findById(int id) {
        Customer customer = null;
        try {
            connect();
            pst = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?");
            pst.setInt(1, id);

            rs = pst.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setLastName(rs.getDate("lastName").toString());
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return customer;
    }

    public List<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Customer customer;
        try {
            connect();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM CUSTOMER");
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setLastName(rs.getString("lastName"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));

                customers.add(customer);
            }
            close();
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return customers;
    }

    public void save(Customer t) {
        try {
            connect();
            pst = this.conn.prepareStatement("INSERT INTO CUSTOMER(NAME, LASTNAME, PHONE, EMAIL, ADDRESS) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getName());
            pst.setString(2, t.getLastName());
            pst.setString(3, t.getPhone());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getAddress());

            pst.executeUpdate();
            ResultSet keys = pst.getGeneratedKeys();
            keys.next();

            int id = keys.getInt(1);
            t.setId(id);
            close();
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    public int update(Customer t) {
        int update = 0;
        try {
            connect();
            pst = this.conn.prepareStatement("UPDATE CUSTOMER SET NAME = ?, LASTNAME = ?, PHONE = ?, EMAIL = ?, ADDRESS = ? WHERE ID = ?");
            pst.setString(1, t.getName());
            pst.setString(2, t.getLastName());
            pst.setString(3, t.getPhone());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getAddress());
            pst.setInt(6, t.getId());
            update = pst.executeUpdate();
            close();
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return update;
    }

    public int delete(int id) {
        int delete = 0;  
        try {
            connect();
            pst = conn.prepareStatement("DELETE FROM CUSTOMER WHERE ID = ?");
            pst.setInt(1, id);
            delete = pst.executeUpdate();
            close();
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return delete;
    }
}
