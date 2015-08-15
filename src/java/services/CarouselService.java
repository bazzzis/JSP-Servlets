/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DAO.ConnectionFactory;
import Domain.Carousel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bazziss
 */
public class CarouselService {

    private final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private Connection connection;

    public void create(Carousel carousel) {

        try {
            connection = connectionFactory.getConnection();
            String sql = "INSERT INTO `eeproject`.`carousel` (`fileName`, `folderName`, `image`, `header`, `text`, `buttonLink`, `buttonName`) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, carousel.getFileName());
            ps.setString(2, carousel.getFolderName());
            ps.setBytes(3, carousel.getImageData());
            ps.setString(4, carousel.getHeader());
            ps.setString(5, carousel.getText());
            ps.setString(6, carousel.getButtonLink());
            ps.setString(7, carousel.getButtonName());
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();

        }

    }

    public List<Carousel> readAll() {
        List<Carousel> list = new ArrayList<>();
        try {
            connection = connectionFactory.getConnection();
            String sql = "SELECT * FROM eeproject.carousel;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carousel carousel = new Carousel();
                carousel.setId(rs.getInt(1));
                carousel.setFileName(rs.getString(2));
                carousel.setFolderName(rs.getString(3));
                carousel.setHeader(rs.getString(5));
                carousel.setText(rs.getString(6));
                carousel.setButtonLink(rs.getString(7));
                carousel.setButtonName(rs.getString(8));
               
                list.add(carousel);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionFactory.closeConnection();

        }
        return list;
    }
 public Carousel read(int id) {
       Carousel carousel = new Carousel();
        try {
            connection = connectionFactory.getConnection();
            String sql = "SELECT * FROM eeproject.carousel where idcarousel= "+id+";";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            rs.next();
              
                carousel.setId(rs.getInt(1));
                carousel.setFileName(rs.getString(2));
                carousel.setFolderName(rs.getString(3));
                carousel.setHeader(rs.getString(5));
                carousel.setText(rs.getString(6));
                carousel.setButtonLink(rs.getString(7));
                carousel.setButtonName(rs.getString(8));

            

        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionFactory.closeConnection();

        }
        return carousel;
    }
 
     public void update(Carousel carousel) {

        try {
            connection = connectionFactory.getConnection();
            if(carousel.getFileName()!=null){
            
            String sql = "UPDATE `eeproject`.`carousel` SET `fileName`= ?,`folderName`=?,`image`=?, `header`=?,`text`=?, `buttonLink`=?, `buttonName`=? WHERE `idcarousel`=?;";
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, carousel.getFileName());
            ps.setString(2, carousel.getFolderName());
            ps.setBytes(3, carousel.getImageData());
            ps.setString(4, carousel.getHeader());
            ps.setString(5, carousel.getText());
            ps.setString(6, carousel.getButtonLink());
            ps.setString(7, carousel.getButtonName());
            ps.setInt(8, carousel.getId());
             ps.executeUpdate();
            }else{
                String sql = "UPDATE `eeproject`.`carousel` SET `header`=?,`text`=?, `buttonLink`=?, `buttonName`=? WHERE `idcarousel`=?;";
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, carousel.getHeader());
            ps.setString(2, carousel.getText());
            ps.setString(3, carousel.getButtonLink());
            ps.setString(4, carousel.getButtonName());
            ps.setInt(5, carousel.getId());
             ps.executeUpdate();
            }
           
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();

        }

    }
     public void delete(int id) {
      
        try {
            connection = connectionFactory.getConnection();
            String sql = "DELETE FROM `eeproject`.`carousel` WHERE `idcarousel`='"+id+"';";

            PreparedStatement ps = connection.prepareStatement(sql);
           ps.executeUpdate();

           
        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionFactory.closeConnection();

        }
     }
}
