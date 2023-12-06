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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taise.registration.RegistrationCreateError;
import taise.registration.RegistrationDAO;
import taise.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateAccountServlet extends HttpServlet {

//    private final String PASSWORD_LENGTH_ERROR = "Password is require from 6 to 30 characters";
//    private final String UPDATE_ERROR = "updateErr.html";
//    private final String UPDATE_ERROR = "updateErr";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.2
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
        //0. get current context
        ServletContext context = this.getServletContext();
        //0.1 get siteMaps
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
//        String url = UPDATE_ERROR;
//        String url = siteMaps.getProperty(UPDATE_ERROR);
        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeture.UPDATE_ERROR);

        //1.Get all parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        String searchValue = request.getParameter("lastSearchValue");
//        RegistrationCreateError errors = new RegistrationCreateError();
//        boolean foundErr = false;

        try {

            /* TODO output your page here. You may use following sample code. */
            boolean role = false;
            if (admin != null) {
                role = true;
            }
            //2. Call DAO 
            //2.1 new DAO
            RegistrationDAO dao = new RegistrationDAO();
            //3.process result
            boolean result = dao.updateAccount(username, role, password);
            if (result) {
                //refresh page --> call previous function(url rewriting)
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchvalue=" + searchValue;
            }//update action is successful

        } catch (SQLException ex) {
            log("UpdateServlet _ SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateServlet _ Naming:" + ex.getMessage());
        } finally {
            url = "DispatchServlet"
                    + "?btAction=Search"
                    + "&txtSearchvalue=" + searchValue;
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
