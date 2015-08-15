/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DAO.ConnectionFactory;
import Domain.Article;

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
public class ArticleService {

    private final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private Connection connection;

    public void Create(Article article) {
        try {
            connection = connectionFactory.getConnection();
            String sql = "INSERT INTO `eeproject`.`Articles` (`Article`, `menuItem`) VALUES ( ?, ?);";
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, article.getArticle());
            ps.setString(2, article.getMenuItem());
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionFactory.closeConnection();

        }
    }

    public Article read(int id) {
        Article article = new Article();
        try {
            connection = connectionFactory.getConnection();
            String sql = "SELECT * FROM eeproject.Articles where id= " + id + ";";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            rs.next();

            article.setArticleId((rs.getInt(1)));
            article.setArticle(rs.getString(2));
            article.setMenuItem(rs.getString(3));

        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionFactory.closeConnection();

        }
        return article;
    }

    public void update(Article article) {

        try {
            connection = connectionFactory.getConnection();

            String sql = "UPDATE `eeproject`.`Articles` SET `Article`= ? WHERE `id`=?;";
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, article.getArticle());
            ps.setInt(2, article.getArticleId());

            ps.executeUpdate();

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
            String sql = "DELETE FROM `eeproject`.`Articles` WHERE `id`='"+id+"';";

            PreparedStatement ps = connection.prepareStatement(sql);
           ps.executeUpdate();

           
        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionFactory.closeConnection();

        }
     }
     
     public List<Article> readAll(String menuItem) {
        List<Article> list = new ArrayList<>();
        try {
            connection = connectionFactory.getConnection();
            String sql = "SELECT * FROM eeproject.Articles where menuItem='"+menuItem+"' ORDER BY id DESC;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Article article = new Article();
                article.setArticleId(rs.getInt(1));
                article.setArticle(rs.getString(2));
                article.setMenuItem(rs.getString(3));
                
               
                list.add(article);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarouselService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionFactory.closeConnection();

        }
        return list;
    }

    private static final Logger LOG = Logger.getLogger(ArticleService.class.getName());
}
