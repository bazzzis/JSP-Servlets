/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;

import DAO.ConnectionFactory;
import Domain.ProductImages;
import Domain.Produs;
import Util.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import services.ImageService;
import services.ProdusService;

/**
 *
 * @author bazziss
 */
@MultipartConfig
public class saveupdate extends HttpServlet {

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
        int productId = 0;
        double productPrice = 0;

        String productName = request.getParameter("ProductName");
        String productDecription = request.getParameter("ProductDecription");
        String strProductPrice = request.getParameter("ProductPrice");
        String productIdStr = request.getParameter("productId");
        RequestDispatcher rd = null;
        ProdusService produsService = ProdusService.getInstance(ConnectionFactory.getInstance());
        try {

            productId = Integer.parseInt(productIdStr);

        } catch (NumberFormatException e) {
            rd = request.getRequestDispatcher("/WEB-INF/CMS/products.jsp");
            request.setAttribute("error", "Please provide a valid product ID!");
            rd.forward(request, response);
        }
        try {

            productPrice = Double.parseDouble(strProductPrice);

        } catch (NumberFormatException e) {

            Produs produs = new Produs();
            if (productId == 0) {
                request.setAttribute("product", produs);
                request.setAttribute("error", "Please provide a valid price!");
                rd = request.getRequestDispatcher("/WEB-INF/CMS/product.jsp");
                rd.forward(request, response);
            } else {
                produs = produsService.findById(productId);
                request.setAttribute("product", produs);
                request.setAttribute("error", "Please provide a valid price!");
                rd = request.getRequestDispatcher("/WEB-INF/CMS/product.jsp");
                rd.forward(request, response);
            }

        }

        String imagesFolder = "/productsImages/";
        final String path = getServletContext().getRealPath("/") + imagesFolder;
        final Part filePart = request.getPart("file");
         String fileName = null;
        if(filePart!=null){
          fileName = getFileName(filePart);}
        InputStream filecontent = null;

        ImageService imageService = ImageService.getInstance(ConnectionFactory.getInstance());
        ProductImages productImage = new ProductImages();

        List<ProductImages> images = imageService.findByProductId(productId);
        request.setAttribute("imageList", images);

        if (fileName != null && fileName.length() >= "*.jpg".length()) {

            String fullPath = path + fileName;
            filecontent = filePart.getInputStream();
            ImageUtil.saveImageToFile(fullPath, filecontent);

            productImage.setFileName(fileName);
            productImage.setFolderName(path);
            byte[] imageData = ImageUtil.readImageFileToBytes(fullPath);
          
            productImage.setImageData(imageData);
            productImage.setProductId(productId);

           
            
            try {
                imageService.create(productImage);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Not possible to save the Image", e);
            }
        }

        Produs produs = new Produs(productId, productName, productDecription, productPrice);

        ImageService is = ImageService.getInstance(ConnectionFactory.getInstance());
        if (produs.getId() == 0) {

            Produs createdProdus = produsService.create(produs);

            request.setAttribute("product", createdProdus);

            rd = request.getRequestDispatcher("/WEB-INF/CMS/product.jsp");
            request.setAttribute("info", "Product created succesfully!");

        } else {
            Produs updatedProdus = produsService.update(produs);
            request.setAttribute("product", updatedProdus);
            request.setAttribute("info", "Product modified succesfully!");

            List<ProductImages> listProductImage = is.findByProductId(productId);
            request.setAttribute("imageList", listProductImage);
            rd = request.getRequestDispatcher("/WEB-INF/CMS/product.jsp");

        }
        rd.forward(request, response);

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
    private static final Logger LOG = Logger.getLogger(saveupdate.class.getName());

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
