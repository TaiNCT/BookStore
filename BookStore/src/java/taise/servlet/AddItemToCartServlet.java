/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taise.cart.CartObject;
import taise.product.ProductCreateError;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    private final String QUANTITY_ERROR = "Over limit(limit: ";
    private final String QUNATITY_DIF_ZERO = "Please enter greater than 0";
    private final String QUNATITY_MUST_NUMBER = "Please enter number";
    // private final String BOOKSTORE_PAGE = "bookStore.jsp";

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
        ProductCreateError errors = new ProductCreateError();
        boolean foundErr = false;
        try {
            /* TODO output your page here. You may use following sample code. */
            String itemName = request.getParameter("txtProductName");//ben client request parameter
            String quantity = request.getParameter("txtProductQuantity");
            String pQuantity = request.getParameter("txtQuantity");
            String price = request.getParameter("txtPrice");
            if (Integer.parseInt(quantity) > Integer.parseInt(pQuantity)) {
                foundErr = true;
                errors.setQuantityError(QUANTITY_ERROR + pQuantity + ")");
            } 
            if(Integer.parseInt(quantity) == 0 || Integer.parseInt(quantity) < 0){
                foundErr = true;
                errors.setQuantityError(QUNATITY_DIF_ZERO);
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERROR", errors);
                request.setAttribute("Quantity", quantity);
                request.setAttribute("itemName", itemName);
            } else {
                //1. Cust goes to the cart place
                HttpSession session = request.getSession();//Bat buoc phai co
                //2.Cust take cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObject();
                }//end cart has existed
                //3. Cust drop item in cart 

                cart.addItemToCart(itemName, Integer.parseInt(quantity));
                session.setAttribute("CART", cart);
                session.setAttribute("PRICE", price);
                session.setAttribute("itemName", itemName);
            }
        } finally {
            //4. Cust goes to shopping 
//            String urlRewriting = "DispatchServlet"
//                    + "?btAction=ShowBookList";
            String urlRewriting = "ShowBookServlet";
//            response.sendRedirect(urlRewriting);//coi chung
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
