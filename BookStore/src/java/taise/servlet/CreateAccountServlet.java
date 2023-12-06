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
import taise.registration.RegistrationDTO;
import taise.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String USERNAME_LENGTH_ERROR = "Username is require from 6 to 20 characters";
    private final String USERNAME_EXISTED_ERROR = " is exist";
    private final String PASSWORD_LENGTH_ERROR = "Password is require from 6 to 30 characters";
    private final String FULLNAME_LENGTH_ERROR = "Full name is require from 6 to 30 characters";
    private final String CONFIRM_ERROR = "Confirm must matched password";

//    private final String CREATE_ERROR_PAGE = "createAccount.jsp";
//    private final String CREATE_ERROR_PAGE = "createAccountPage";
////    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "loginPage";
   
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
        //0. get current context
        ServletContext context = this.getServletContext();
        //0.1 get siteMaps
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //1. get all parameter 
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullName");
//        String url = CREATE_ERROR_PAGE;
//        String url = siteMaps.getProperty(CREATE_ERROR_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeture.CREATE_ERROR_PAGE);
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        try {
            /* TODO output your page here. You may use following sample code. */
            //2. Verify user's constraints
            if (username.trim().length() < 6
                    || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLengthError(USERNAME_LENGTH_ERROR);
            }
            if (password.trim().length() < 6
                    || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError(PASSWORD_LENGTH_ERROR);
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched(CONFIRM_ERROR);
            }
            if (fullname.trim().length() < 2
                    || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthError(FULLNAME_LENGTH_ERROR);
            }
            if (foundErr) {//errors occur
                //3.catch error to attribute then fw eror display page
                request.setAttribute("CREATE_ERROR", errors);
            } else {//no error
                //3. call DAO 
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call method of DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                //4. process result
                if(result){
//                    url = LOGIN_PAGE;
//                    url = siteMaps.getProperty(LOGIN_PAGE);
                    url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeture.LOGIN_PAGE);
                }//end insert action is successfully
            }//no error 
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming:" + ex.getMessage());
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL:" + ex.getMessage());
            if(errMsg.contains("duplicate")){
                errors.setUsernameIsExisted(username + USERNAME_EXISTED_ERROR);
                request.setAttribute("CREATE_ERROR", errors);
            }//end PK violate
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
