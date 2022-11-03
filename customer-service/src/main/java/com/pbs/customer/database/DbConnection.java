/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbs.customer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Victor Arucha - varucha@hightech-corp.com - PBS
 */
public class DbConnection {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/tallerws";


    // Credentials
    static final String USER_NAME = "root";
    static final String USER_PASSWORD = "";

    /**
     * TODO 
     * Change this to protected!!!!
     */
    protected Connection conn = null;
    protected Statement st = null;
    protected PreparedStatement pst = null;
    protected ResultSet rs= null;

    public DbConnection() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);
    }

    public void close() throws SQLException {
        if(Objects.nonNull(conn)) conn.close();
        conn = null;
    }
}