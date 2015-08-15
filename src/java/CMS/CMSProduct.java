/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;

import DAO.ConnectionFactory;
import Domain.ProductImages;
import Domain.Produs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ImageService;
import services.ProdusService;

/**
 *
 * @author bazziss
 */
public class CMSProduct extends HttpServlet {

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

        RequestDispatcher rd = null;

        String productIdStr = request.getParameter("productId");
        int productId = 0;

        try {
            productId = Integer.parseInt(productIdStr);
        } catch (NumberFormatException e) {
            rd = request.getRequestDispatcher("/CMS/CMSProducts");
            request.setAttribute("error", "Please provide a valid product ID!");
            rd.forward(request, response);
        }

        ProdusService ps = ProdusService.getInstance(ConnectionFactory.getInstance());
        Produs produs = ps.findById(productId);

        if (produs.getId() == 0 & productId!=0) {
            
           rd = request.getRequestDispatcher("/CMS/CMSProducts");
            request.setAttribute("error", "Sorry, product ID "+productIdStr+" is not available!");
            rd.forward(request, response); 

        } else {
            ImageService imageService = ImageService.getInstance(ConnectionFactory.getInstance());
            List <ProductImages> productImageList= imageService.findByProductId(productId);
            
            request.setAttribute("imageList", productImageList);
            request.setAttribute("product", produs);
            

            rd = request.getRequestDispatcher("/WEB-INF/CMS/product.jsp");
            rd.forward(request, response);
        }
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
