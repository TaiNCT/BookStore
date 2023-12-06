/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taise.registration.RegistrationDAO;
import taise.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

//    private String ERROR_PAGE = "deleteErr.html";
//    private String ERROR_PAGE = "deleteErr";

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //0. get servlet context
        ServletContext context = this.getServletContext();
        //0.1 get siteMaps
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //1. Get all parameter
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("LastSearchValue");
//        String url = ERROR_PAGE;
//        String url = siteMaps.getProperty(ERROR_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.DeleteAccountFeture.ERROR_PAGE);
        try {
            /* TODO output your page here. You may use following sample code. */
            //2. Call DAO 
            //2.1 new DAO
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of dao 
            boolean result = dao.deleteAccount(username);
            //3.process result
            if (result) {
                //refresh page --> call previous function url rewriting
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchvalue=" + searchValue;
            }//delete action is successful
        } catch (SQLException ex) {
            log("DeleteAccountServlet _ SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteAccountServlet _ Naming:" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
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
