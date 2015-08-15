/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;

import Domain.Article;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ArticleService;

/**
 *
 * @author bazziss
 */
public class HomeArticleEditServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArticleService as = new ArticleService();
        RequestDispatcher rd = null;
        
        String strId = request.getParameter("articleId");
        if (strId != null) {
            if (strId.equals("new")) {
                
                Article article = new Article();
                String strArticle = request.getParameter("articlenew");
                
                article.setArticle(strArticle);
                article.setMenuItem("home");
                
                as.Create(article);
                 request.setAttribute("info", "New article succesfully created");
                
            } else {
                try {
                    int articleId = Integer.parseInt(strId);
                    String strArticle = request.getParameter("article" + articleId);
                    Article article = new Article();
                    article.setArticleId(articleId);
                    article.setArticle(strArticle);
                    
                    as.update(article);
                    
                    request.setAttribute("info", "Article ID " + articleId + " succesfully updated");
                } catch (NumberFormatException e) {
                    
                    List<Article> articleList = as.readAll("home");
                    
                    request.setAttribute("error", "Number format exception, please provide a valid ID");
                    request.setAttribute("articleList", articleList);
                    rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeContent.jsp");
                    rd.forward(request, response);
                }
            }
        }
        
        String delId = request.getParameter("delete");
        if (delId != null) {
            try {
                int articleId = Integer.parseInt(delId);
                
                as.delete(articleId);
                
                request.setAttribute("info", "Article ID " + articleId + " succesfully deleted");
            } catch (NumberFormatException e) {
                
                List<Article> articleList = as.readAll("home");
                
                request.setAttribute("error", "Number format exception, please provide a valid ID");
                request.setAttribute("articleList", articleList);
                rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeContent.jsp");
                rd.forward(request, response);
            }
        }
        
        List<Article> articleList = as.readAll("home");
        
        request.setAttribute("articleList", articleList);
        rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeContent.jsp");
        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
