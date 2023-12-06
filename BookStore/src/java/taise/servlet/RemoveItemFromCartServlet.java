/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taise.cart.CartObject;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

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
        try{
            /* TODO output your page here. You may use following sample code. */
            //1. Cust goes to cart place 
            HttpSession session = request.getSession(false);//dung false tai vi viewCart la phia client, gio hang con` nhung giao dien bien mat
            if(session != null){
            //2. Cust take cart 
                CartObject cart  =(CartObject) session.getAttribute("CART");
                if(cart != null){
                    //3.Cust get all items;
                    Map<String, Integer> items = cart.getItem();
                    if(items != null){
                        //4. Cust choose all selected items
                        String[] selectedItem = request.getParameterValues("chkItem");
                        if(selectedItem != null){
                            //5. Remove all selected items
                            for(String item : selectedItem){
                                cart.removeItemFromCart(item);
                            }
                            session.setAttribute("CART", cart);
                        }//end cust has checked
                    }//item has existed
                }//cart has existed
            }//end cart place has existed          
        }finally{
            //6. Refresh --> call previous function again (ViewCart)
            //--> using url rewriting technique
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View your cart";
            response.sendRedirect(urlRewriting);//phai dung sendRedirect do trung btAction
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
