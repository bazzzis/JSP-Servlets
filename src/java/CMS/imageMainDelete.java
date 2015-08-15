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
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ImageService;
import services.ProdusService;

/**
 *
 * @author bazziss
 */
@WebServlet(name = "imageMainDelete", urlPatterns = {"/CMS/imageMainDelete"})
public class imageMainDelete extends HttpServlet {

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

        int productId = Integer.parseInt(request.getParameter("productId"));

        ImageService is = ImageService.getInstance(ConnectionFactory.getInstance());
        List<ProductImages> productImageList = is.findByProductId(productId);
       
        try {
            int mainImage = Integer.parseInt(request.getParameter("mainImage"));
            for (ProductImages productImageList1 : productImageList) {
                if (productImageList1.isMainImage()) {
                    is.changeMain(productImageList1.getId(), false);
                }
            }
            is.changeMain(mainImage, true);

            String[] deleteImage = request.getParameterValues("deleteImage");

            if (deleteImage != null) {
                String id = "";
                for (String s : deleteImage) {
                    int toDelete = Integer.parseInt(s);
                    id =id+s+";";
                    if (toDelete != mainImage) {
                        is.deleteByImageId(toDelete);
                    } else {

                        request.setAttribute("error", "Image id " + toDelete + " can not be deleted because it's main image!");

                    }
                }
                 request.setAttribute("info", "Following image ID's are deleted: "+id);
            }

            ProdusService ps = ProdusService.getInstance(ConnectionFactory.getInstance());
            Produs produs = ps.findById(productId);
            request.setAttribute("product", produs);
            
            productImageList = is.findByProductId(productId);
             request.setAttribute("imageList", productImageList);
            rd = request.getRequestDispatcher("/WEB-INF/CMS/product.jsp");
            rd.forward(request, response);

        } catch (NumberFormatException e) {

            rd = request.getRequestDispatcher("/CMS/CMSProduct");
            request.setAttribute("error", "Please provide a valid image ID!");
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
