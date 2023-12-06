/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taise.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
//1. xml 2. anotation
//cho thay doi nhiu thi @ con it thay doi thi xml. Neu  it thay doi thanh thay doi nhieu 
//thi viet vao xml, xml ghi de len tat ca 
public class DispatchServlet extends HttpServlet {
/*//    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_PAGE = "";
//    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGIN_CONTROLLER = "loginController";
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "deleteController";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "updateController";
//    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String START_UP_CONTROLLER = "startupController";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToListController";
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String VIEW_CART_PAGE = "viewcartPage";
//    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String LOGOUT_CONTROLLER = "logoutController";
//    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "removeItemFromCartController";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
    private final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
//    private final String BOOK_LIST_CONTROLLER = "ShowBookServlet";
    private final String BOOK_LIST_CONTROLLER = "bookListController";*/
    private final String CHECK_OUT_CONTROLLER = "checkOutServlet";
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
        //String url = LOGIN_PAGE;
//        String url = siteMaps.getProperty(LOGIN_PAGE);
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
        //1. Which button did user click?
        String button = request.getParameter("btAction");
        try {
            /* TODO output your page here. You may use following sample code. */
            if (button == null) {//welcome file is called
//                url = START_UP_CONTROLLER;
//                url = siteMaps.getProperty(START_UP_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.START_UP_CONTROLLER);
            } else if (button.equals("Login")) {
//                url = LOGIN_CONTROLLER;
//                url = siteMaps.getProperty(LOGIN_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {
//                url = SEARCH_LASTNAME_CONTROLLER;
//                url =siteMaps.getProperty(SEARCH_LASTNAME_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("delete")) {
//                url = DELETE_ACCOUNT_CONTROLLER;
//                url = siteMaps.getProperty(DELETE_ACCOUNT_CONTROLLER);
                 url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {
//                url = UPDATE_ACCOUNT_CONTROLLER;
//                url = siteMaps.getProperty(UPDATE_ACCOUNT_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);                
            } else if (button.equals("Add book to Your cart")) {
//                url = ADD_ITEM_TO_CART_CONTROLLER;
//                url = siteMaps.getProperty(ADD_ITEM_TO_CART_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER);
            } else if (button.equals("View your cart")) {
//                url = VIEW_CART_PAGE;
//                url = siteMaps.getProperty(VIEW_CART_PAGE);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.VIEW_CART_PAGE);
            } else if (button.equals("Logout")) {
//                url = LOGOUT_CONTROLLER;
//                url = siteMaps.getProperty(LOGOUT_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);
            } else if (button.equals("Remove Selected Item")) {
//                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
//                url = siteMaps.getProperty(REMOVE_ITEM_FROM_CART_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.REMOVE_ITEM_FROM_CART_CONTROLLER);
            } else if (button.equals("Creat New Account")) {
//                url = CREATE_ACCOUNT_CONTROLLER;
//                url = siteMaps.getProperty(CREATE_ACCOUNT_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.CREATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("ShowBookList")) {
//                url = BOOK_LIST_CONTROLLER;
//                url = siteMaps.getProperty(BOOK_LIST_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.BOOK_LIST_CONTROLLER);
            } else if (button.equals("CheckOut")) {
//                url = BOOK_LIST_CONTROLLER;
//                url = siteMaps.getProperty(BOOK_LIST_CONTROLLER);
                url = CHECK_OUT_CONTROLLER;
            }
        } finally {
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
