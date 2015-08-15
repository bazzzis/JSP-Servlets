/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Bazis
 */
public class ConnectionFactory { 
    private static final Logger LOG = Logger.getLogger(ConnectionFactory.class.getName());

    
    
    private static ConnectionFactory instance;

    public static ConnectionFactory getInstance() {
         if(instance == null){
           instance = new ConnectionFactory();
         }
        return instance;
    }

    
    
    private static Connection connection;
    
    private static DataSource dataSource;
    
    private ConnectionFactory() {
        
        
         try {
            //  jdbc/centricdb

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/eeproject");
            
            Connection conn = dataSource.getConnection();
            
            if(conn==null){
                LOG.log(Level.SEVERE, "Atentie conexiunea este nula!");
            }
            
            
        } catch (NamingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        
        //createConnection();
    }
    
    
    public  Connection getConnection() throws SQLException{
         if(connection == null || connection.isClosed() ){
            createConnection() ;
         }
        return connection;
    }
    
    
    public  void closeConnection() {
        try {
            if(connection != null && !connection.isClosed() ){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
                connection=null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    

    private static void createConnection() {
     try {
            //connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/webmvc", "root", "free");
         
            connection =dataSource.getConnection();
            
            LOG.info("connection created! ");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}