/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taise.registration.RegistrationDAO;
import taise.registration.RegistrationDTO;
import taise.util.DBHelper;
import taise.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    //private final String SEARCH_PAGE = "search.jsp";
//    private final String SEARCH_PAGE = "searchPage";
//   // private final String ERROR_PAGE = "error.html";
//    private final String ERROR_PAGE = "invalidPage";
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
        //0. get current context
        ServletContext context = this.getServletContext();
        //0.1 get siteMaps
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        //1.get all parameter
        // String button = request.getParameter("btAction");
//        String url = ERROR_PAGE;
//        String url =siteMaps.getProperty(ERROR_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.ERROR_PAGE);

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            /* TODO output your page here. You may use following sample code. */
//            out.println("Username " + username + " - " + password + " - " + button);
//            System.out.println("Username " + username + " - " + password + " - " + button);
//            Connection con = DBHelper.createConnection();
//            if (con != null) {
//                out.println("Database is open");
//            } else {
//                out.println("Database is closed");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();

            //tai servlet          
            //2.Call DAO
            //2.1 New DAO
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO 
//            boolean result = dao.checkLogin(username, password);
            RegistrationDTO result = dao.checkLogin(username, password);
            //3.process
            if (result != null) {
//                url = SEARCH_PAGE;
//                url = siteMaps.getProperty(SEARCH_PAGE);
                url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.SEARCH_PAGE);
                HttpSession session = request.getSession();
                session.setAttribute("USER_INFO", result);
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 4);
//                response.addCookie(cookie);
            }//username password is correct

        } catch (SQLException ex) {
            log("LoginServlet _ SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming:" + ex.getMessage());
        } finally {
            //4. return to browser
            // response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
