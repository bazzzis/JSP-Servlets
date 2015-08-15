/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DAO.ConnectionFactory;
import Domain.Produs;
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
 * @author Bazis
 */
public class ProdusService {

    private static ProdusService instance;

    public static ProdusService getInstance(ConnectionFactory connectionFactory) {

        if (instance == null) {
            instance = new ProdusService(connectionFactory);
        }
        return instance;
    }

    private ConnectionFactory connectionFactory;

    private Connection connection;

    private ProdusService(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Produs> getAll() {
        List<Domain.Produs> list = new ArrayList<>();

        try {

            connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM eeproject.products;";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Produs produs = new Produs();
                produs.setId(rs.getInt(1));
                produs.setName(rs.getString(2));
                produs.setDescription(rs.getString(3));
                produs.setPrice(rs.getDouble(4));

                list.add(produs);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }

        return list;

    }

    public Produs findById(int id) {

        Produs produs = new Produs();
        try {

            connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM eeproject.products where productID = " + id + ";";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                produs.setId(rs.getInt(1));
                produs.setName(rs.getString(2));
                produs.setDescription(rs.getString(3));
                produs.setPrice(rs.getDouble(4));
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }
        return produs;
    }

    public Produs update(Produs produs) {

        try {

            connection = connectionFactory.getConnection();

            System.out.println(produs.toString());

            String sql = "UPDATE `eeproject`.`products` SET `productName`='" + produs.getName() + "', `productDescription`='" + produs.getDescription() + "', price=" + produs.getPrice() + " WHERE `productID`='" + produs.getId() + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }
        return findById(produs.getId());
    }

    public void delete(int id) {

        Produs produs = new Produs();
        try {

            connection = connectionFactory.getConnection();

            String sql = "DELETE FROM `eeproject`.`products` WHERE `productID`='" + id + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            ImageService imageService =  ImageService.getInstance(connectionFactory);
            imageService.deleteAllUsingProduct(id);
            
            

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }

    }

    public Produs create(Produs produs) {

        int lastid = 0;

        if (produs.getId() == 0) {
            try {

                connection = connectionFactory.getConnection();

                String sql = "INSERT INTO `eeproject`.`products` (`productName`, `productDescription`, price) VALUES ('" + produs.getName() + "', '" + produs.getDescription() + "', " + produs.getPrice() + ");";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);

                ResultSet rs = statement.executeQuery("select last_insert_id() as last_id from eeproject.products");

                while (rs.next()) {
                    lastid = Integer.parseInt(rs.getString("last_id"));

                }

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            } finally {
                connectionFactory.closeConnection();
                LOG.log(Level.INFO, "Connection closed");
            }

        } else {
            update(produs);
            return findById(produs.getId());
        }

        return findById(lastid);

    }
    private static final Logger LOG = Logger.getLogger(ProdusService.class.getName());

}
