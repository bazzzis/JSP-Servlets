/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DAO.ConnectionFactory;

import Domain.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bazziss
 */
public class UserServiceImpl implements UserServiceIntf{
    
     private static UserServiceImpl instance;

    public static  UserServiceImpl getInstance(ConnectionFactory connectionFactory) {
        
        if(instance == null){
           instance = new UserServiceImpl(connectionFactory);
        }
        return instance;
    }
   
    private ConnectionFactory connectionFactory;

    private  Connection connection ;

    private UserServiceImpl(ConnectionFactory connectionFactory) {
            this.connectionFactory = connectionFactory;
    }

    
     private List <User> list = new ArrayList<>();
     
     public List <User> getAll() {
      

        try {

           connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM eeproject.Login;";
         Statement statement = connection.createStatement();
           

            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                 
                
                 LOG.log(Level.INFO,"User: "+ user.toString());
                list.add(user);
                 LOG.log(Level.INFO,"List size: "+ list.size());
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
        connectionFactory.closeConnection();
        LOG.log(Level.INFO,"Connection closed");
        }             
                
        return list;
     }
   

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public boolean isValidUsernameAndPassword(String username, String password) {
       list = getAll();
       if(username!=null || password!=null){
           LOG.log(Level.INFO, " Login: "+username+ " Password:" + password);
             LOG.log(Level.INFO, " List size: "+list.size());
        for (int i = 0; i < list.size(); i++) {
           
            User user=list.get(i);
              LOG.log(Level.INFO, " Users: "+user.toString());
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }     
            
        } }return false;
       
    }
     
}
