/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import DAO.ConnectionFactory;
import Domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserServiceImpl;
import services.UserServiceIntf;

/**
 *
 * @author bazziss
 */
public class loginDispatcher extends HttpServlet {

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
        String login;
        String password;
         RequestDispatcher rd=null;
         try{
         login=request.getParameter("login");
           login= login.trim();
         password=request.getParameter("password");
           password = password.trim();
         
        UserServiceIntf userService =  UserServiceImpl.getInstance(ConnectionFactory.getInstance());
              boolean succeslogin = userService.isValidUsernameAndPassword(login, password);
              
               
         
        if (succeslogin) {
            
             HttpSession session = request.getSession(true);
             
             User user = new User();
             user.setUsername(login);
             
             session.setAttribute("MAIN_USER", user);
            
             rd = request.getRequestDispatcher("/WEB-INF/CMS/cmsindex.jsp");
            
        }else{
              request.setAttribute("error", "Username or password is wrong, please try again");
            rd = request.getRequestDispatcher("login");
        
        }
          rd.forward(request, response);
        }catch (NullPointerException ex){
         LOG.log(Level.INFO, "Accesing loginDispatcher outside of login page", ex);
         
         }finally{
          request.setAttribute("error", "Please provide your credentials");
            rd = request.getRequestDispatcher("/WEB-INF/CMS/login.jsp");
             rd.forward(request, response);
         }
         
    }
    private static final Logger LOG = Logger.getLogger(loginDispatcher.class.getName());

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
