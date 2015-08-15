/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;

import Domain.Carousel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.CarouselService;

/**
 *
 * @author bazziss
 */

@MultipartConfig
public class CMSHomeCarousel extends HttpServlet {

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

        LOG.log(Level.INFO, "Accesing CMSHomeCarousel ");
        CarouselService cs = new CarouselService();
        String deleteId = request.getParameter("deleteId");
        if (deleteId != null) {
            try {
                int idToDelete = Integer.parseInt(deleteId);
                cs.delete(idToDelete);
                request.setAttribute("info", "Deleted succesfully");
            } catch (NumberFormatException e) {

                List <Carousel> carouselList = cs.readAll();
                request.setAttribute("carouselList", carouselList);
                request.setAttribute("error", e);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeCarousel.jsp");
                rd.forward(request, response);
            }
        }

        String updateId = request.getParameter("updateId");

        if (updateId != null) {
            try {
                int idToUpdate = Integer.parseInt(updateId);
                if(idToUpdate!=0){
                Carousel carousel = cs.read(idToUpdate);
                request.setAttribute("carousel", carousel);}
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeCarouselUpdate.jsp");
                rd.forward(request, response);
            } catch (NumberFormatException e) {

                List <Carousel> carouselList = cs.readAll();
                request.setAttribute("carouselList", carouselList);
                request.setAttribute("error", e);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeCarousel.jsp");
                rd.forward(request, response);
            }
        }

        List <Carousel> carouselList = cs.readAll();
        request.setAttribute("carouselList", carouselList);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeCarousel.jsp");
        rd.forward(request, response);

    }
    private static final Logger LOG = Logger.getLogger(CMSHomeServlet.class.getName());

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
