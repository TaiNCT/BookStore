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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taise.registration.RegistrationDAO;
import taise.registration.RegistrationDTO;
import taise.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

////    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "loginPage";
////    private final String SEARCH_PAGE = "search.jsp";
//    private final String SEARCH_PAGE = "searchPage";
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
//        String url = LOGIN_PAGE;
//        String url = siteMaps.getProperty(LOGIN_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.StartUpFeture.LOGIN_PAGE);
        try {
            /* TODO output your page here. You may use following sample code. */
            //1. Get all cookie 
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                HttpSession session = request.getSession();
                //2. Get username and password of newest cookies
                for (Cookie cookie : cookies) {
                    Cookie lastCookie = cookies[cookies.length - 1];
                    String username = lastCookie.getName();
                    String password = lastCookie.getValue();
                    //3.call DAO
                    //3.1 new DAO 
                    RegistrationDAO dao = new RegistrationDAO();
                    //3.2 call method
//                    boolean result = dao.checkLogin(username, password);
                    RegistrationDTO result = dao.checkLogin(username, password);
                    //4. process result
                    if (result != null) {
//                        url = SEARCH_PAGE;
//                        url = siteMaps.getProperty(SEARCH_PAGE);
                        url = siteMaps.getProperty(MyApplicationConstants.StartUpFeture.SEARCH_PAGE);
                    }//end user is authenticated
                }
            }//cookie exist
        } catch (SQLException ex) {
            log("StartUpServlet _ SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log("StartUpServlet _ Naming:" + ex.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
