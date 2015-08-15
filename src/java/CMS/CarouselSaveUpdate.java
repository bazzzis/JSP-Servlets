/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;

import Domain.Carousel;
import Util.ImageUtil;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;
import services.CarouselService;

/**
 *
 * @author bazziss
 */
@MultipartConfig
public class CarouselSaveUpdate extends HttpServlet {

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

        String idString = request.getParameter("carouselId");
        System.out.println("id "+ idString);
        String header = request.getParameter("header");
        
        System.out.println("header "+header);
        
        String text = request.getParameter("Text");
        String buttonLink = request.getParameter("buttonLink");
        String buttonName = request.getParameter("buttonName");
        int id = 0;
         CarouselService cs = new CarouselService();
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
           
            List carouselList = cs.readAll();
            request.setAttribute("carouselList", carouselList);
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeCarousel.jsp");
            rd.forward(request, response);

        }
        Carousel carousel = new Carousel();
        carousel.setHeader(header);
        carousel.setText(text);
        carousel.setButtonLink(buttonLink);
        carousel.setButtonName(buttonName);

        String imagesFolder = "/carousel/";
        final String path = getServletContext().getRealPath("/") + imagesFolder;
        System.out.println("file part "+request.getPart("file"));
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);
        InputStream filecontent = null;

        if (fileName != null && fileName.length() >= "*.jpg".length()) {

            String fullPath = path + fileName;
            filecontent = filePart.getInputStream();
            ImageUtil.saveImageToFile(fullPath, filecontent);
            byte[] imageData = ImageUtil.readImageFileToBytes(fullPath);
            carousel.setFileName(fileName);
            carousel.setFolderName(path);
            carousel.setImageData(imageData);
            
            
        } 
        if(id==0){
            cs.create(carousel);
            
             List carouselList = cs.readAll();
            request.setAttribute("carouselList", carouselList);
            request.setAttribute("info", "Succesfully created");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeCarousel.jsp");
            rd.forward(request, response);
        
        }else{
        carousel.setId(id);
        cs.update(carousel);
        
         List carouselList = cs.readAll();
            request.setAttribute("carouselList", carouselList);
            request.setAttribute("info", "Succesfully updated");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CMS/cmshomeCarousel.jsp");
            rd.forward(request, response);
        }
        
        
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOG.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    private static final Logger LOG = Logger.getLogger(CarouselSaveUpdate.class.getName());

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
