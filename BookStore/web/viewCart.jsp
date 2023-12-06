<%-- 
    Document   : viewCart
    Created on : Oct 9, 2023, 5:00:00 PM
    Author     : ADMIN
--%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="taise.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>

        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <form action="DispatchServlet">
                    <c:url var="urlRewriting" value="DispatchServlet">
                        <c:param name="btAction" value="ShowBookList"/>   
                    </c:url>
                    <a href="${urlRewriting}">Home</a>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>   
                                <th>Total</th>   
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="sum" value="0"/>
                            <c:set var="item" value="${cart.getItem()}"/> 
                            <c:if test="${not empty item}">                          
                                <c:forEach items="${item.keySet()}" var="c" varStatus="counter">
                                    <c:if test="${c == sessionScope.itemName}">
                                        <c:set var="price" value="${sessionScope.PRICE}"/>       
                                    </c:if>
                                    <c:if test="${c == sessionScope.itemName}">
                                        <c:set var="total" value="${(item.get(c))*price}"/>
                                    </c:if>
                                    <c:set var="sum" value="${sum+total}"/>

                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${c}
                                        </td>
                                        <td>
                                            ${item.get(c)}
                                        </td>  
                                        <td>
                                            ${total}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" value="${c}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4">
                                        <c:url var="urlRewriting" value="DispatchServlet">
                                            <c:param name="btAction" value="ShowBookList"/>   
                                        </c:url>
                                        <a href="${urlRewriting}">Add more book to Your cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Item" name="btAction"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <jsp:useBean id="d" class="java.util.Date"/>
                                        Order date:${d.toString()}
                                    </td>
                                    <td>
                                        Total money:${sum}
                                    </td>
                                    <td>
                                        <input type="submit" value="CheckOut" name="btAction"/>
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </form>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope}">   
            <c:url var="urlRewriting" value="DispatchServlet">
                <c:param name="btAction" value="ShowBookList"/>   
            </c:url>
            <a href="${urlRewriting}">Add more book to Your cart</a>
            <h2>
                No cart is existed!
            </h2>
        </c:if>
        <%--   <%
               //1. Cust goes to his/her cart place
               if (session != null) {
                   CartObject cart = (CartObject) session.getAttribute("CART");
                   if (cart != null) {
                       Map<String, Integer> items = cart.getItem();
                       if (items != null) {
           %>
           <form action="DispatchServlet">
               <table border="1">
                   <thead>
                       <tr>
                           <th>No.</th>
                           <th>Name</th>
                           <th>Quantity</th>  
                           <th>Action</th>
                       </tr>
                   </thead>
                   <tbody>
                       <%
                           int count = 0;
                           for (String key : items.keySet()) {
                       %>
                       <tr>
                           <td>
                               <%= ++count%>
                           </td>
                           <td>
                               <%= key%> 
                           </td>
                           <td>
                               <%= items.get(key)%>
                           </td>                   
                           <td>
                               <input type="checkbox" name="chkItem" value="<%= key%>" />
                           </td>
                       </tr>
                       <%
                           }//end traverse items
                       %>
                       <tr>
                           <td colspan="3">
                               <a href="DispatchServlet?btAction=ShowBookList">Add more book to Your cart</a>
                           </td>
                           <td>
                               <input type="submit" value="Remove Selected Item" name="btAction"/>
                           </td>
                       </tr>
                   </tbody>
               </table>
           </form>
           <%
                           return;
                       }//end item have exist
                   }//cart has exist
               }//cart place must exist
           %>
           <h2>
               No cart is existed!
           </h2> --%>
    </body>
</html>
