/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DAO.ConnectionFactory;
import Domain.ProductImages;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bazis
 */
public class ImageService {

    private static ImageService instance;

    public static ImageService getInstance(ConnectionFactory connectionFactory) {

        if (instance == null) {
            instance = new ImageService(connectionFactory);
        }
        return instance;
    }

    private final ConnectionFactory connectionFactory;

    private Connection connection;

    private ImageService(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Map<Integer, ProductImages> getAllMain() {

        Map<Integer, ProductImages> map = new HashMap();

        try {

            connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM eeproject.SyncronizedImages where mainImage=1;";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ProductImages productImage = new ProductImages();
                productImage.setId(rs.getInt(1));
                productImage.setFileName(rs.getString(2));
                productImage.setFolderName(rs.getString(3));
                //productImage.setImageData(rs.getBytes(4));
                productImage.setProductId(rs.getInt(5));
                if (rs.getInt(6) != 0) {
                    productImage.setMainImage(true);
                } else if (rs.getInt(6) == 0) {
                    productImage.setMainImage(false);
                }

                map.put(productImage.getProductId(), productImage);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }

        return map;

    }

    public List<ProductImages> findByProductId(int productId) {

        List<Domain.ProductImages> list = new ArrayList<>();

        try {

            connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM eeproject.SyncronizedImages where productId=" + productId + ";";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ProductImages productImage = new ProductImages();
                productImage.setId(rs.getInt(1));
                productImage.setFileName(rs.getString(2));
                productImage.setFolderName(rs.getString(3));
                //productImage.setImageData(rs.getBytes(4));
                productImage.setProductId(rs.getInt(5));
                if (rs.getInt(6) != 0) {
                    productImage.setMainImage(true);
                } else if (rs.getInt(6) == 0) {
                    productImage.setMainImage(false);
                }

                list.add(productImage);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }
        return list;
    }

    public ProductImages findImageId(int imageId, boolean syncronize) {

        ProductImages productImage = new ProductImages();

        try {

            connection = connectionFactory.getConnection();

            String sql = "select * from eeproject.SyncronizedImages where idSyncronizedImages=" + imageId + ";";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                productImage.setId(rs.getInt(1));
                productImage.setFileName(rs.getString(2));
                productImage.setFolderName(rs.getString(3));
                if (syncronize) {

                    Blob blob = rs.getBlob(4);
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
//release the blob and free up memory. (since JDBC 4.0)
                    blob.free();
                    productImage.setImageData(blobAsBytes);

                }
                productImage.setProductId(rs.getInt(5));
                if (rs.getInt(6) != 0) {
                    productImage.setMainImage(true);
                } else if (rs.getInt(6) == 0) {
                    productImage.setMainImage(false);
                }

            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }
        return productImage;
    }

    public void deleteByImageId(int imageId) {

        try {

            connection = connectionFactory.getConnection();

            String sql = "delete  from eeproject.SyncronizedImages where idSyncronizedImages=" + imageId + ";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }

    }

    public void deleteAllUsingProduct(int productId) {

        try {

            connection = connectionFactory.getConnection();

            String sql = "DELETE FROM `eeproject`.`SyncronizedImages` WHERE productId=" + productId + ";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            ImageService imageService = ImageService.getInstance(connectionFactory);

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }

    }

    public void create(ProductImages productImages) {

        int booleanToInt;
        if (productImages.isMainImage()) {
            booleanToInt = 1;
        } else {
            booleanToInt = 0;
        }

        try {

            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO `eeproject`.`SyncronizedImages` (`fileName`,`folderName`, `Images`,`productId`,`mainImage`) VALUES ( ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, productImages.getFileName());
            statement.setString(2, productImages.getFolderName());
            statement.setBytes(3, productImages.getImageData());
            statement.setInt(4, productImages.getProductId());
            statement.setInt(5, booleanToInt);
            
            statement.executeUpdate();
            
            connection.commit();

            ResultSet rs = statement.executeQuery("select last_insert_id() as last_id from eeproject.SyncronizedImages");
            int lastid = 0;
            while (rs.next()) {
                lastid = Integer.parseInt(rs.getString("last_id"));

            }

           // ByteArrayInputStream bis = new ByteArrayInputStream(productImages.getImageData());
//            InputStream inputStream = new FileInputStream(new File(productImages.getFolderName() + productImages.getFileName()));
//            System.out.println(productImages.getFolderName() + productImages.getFileName());
            // statement.setBytes(3, productImages.getImageData());
//            statement.setInt(1, productImages.getProductId());
//            statement.setInt(2, booleanToInt);
//            System.out.println("SQL create" + sql);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }

    }

    public void changeMain(int imageId, boolean mainImage) {

        int booleanToInt = 0;

        if (mainImage) {
            booleanToInt = 1;
        }

        try {

            connection = connectionFactory.getConnection();

            String sql = "UPDATE `eeproject`.`SyncronizedImages` SET `mainImage`=" + booleanToInt + " WHERE `idSyncronizedImages`=" + imageId + ";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();
            LOG.log(Level.INFO, "Connection closed");
        }

    }
    private static final Logger LOG = Logger.getLogger(ImageService.class.getName());

}
