<%-- 
    Document   : bookStore
    Created on : Oct 11, 2023, 9:57:41 PM
    Author     : ADMIN
--%>

<%@page import="taise.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="producList" value="${requestScope.PRODUCT_LIST}" />
        <c:if test="${not empty producList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
                    <c:forEach items="${producList}" var="dto"  varStatus="counter">
                    <form action="DispatchServlet">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                <input type="hidden" name="txtProductName" value="${dto.productName}" />
                                ${dto.productName}
                            </td>
                            <td>
                                <input type="hidden" name="txtPrice" value="${dto.productUnitPrice}" />
                                ${dto.productUnitPrice}
                            </td>
                            <td>
                                <input type="hidden" name="txtQuantity" value="${dto.productQuantity}" />
                                <c:if test="${requestScope.itemName == dto.productName}">
                                    <c:set var="quantity" value="${requestScope.Quantity}"/>
                                    <input type="number" min="1" max="${dto.productQuantity}" name="txtProductQuantity" value="${quantity}" />
                                    <c:if test="${not empty errors.quantityError}">
                                        <font color="red">
                                        ${errors.quantityError}
                                        </font><br/>
                                    </c:if>
                                </c:if>
                                <c:if test="${requestScope.itemName != dto.productName}">
                                    <input type="number" min="1" max="${dto.productQuantity}" name="txtProductQuantity" value="" />
                                </c:if>
                            </td>
                            <td>
                                <input type="submit" value="Add book to Your cart" name="btAction" />
                            </td>
                        </tr>   
                    </form>
                </c:forEach>      

            </tbody>

        </table>
        <form action="DispatchServlet">
            <input type="submit" value="View your cart" name="btAction" />
        </form>
    </c:if>
    <c:if test="${empty producList}">
        Empty product List
    </c:if>
    <%--         <%
                 List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
                 if (productList != null) {
             %>
             <form action="DispatchServlet">
                 <table border="1">
                     <thead>
                         <tr>
                             <th>No</th>
                             <th>Name</th>
                             <th>Price</th>
                             <th>Quantity</th>
                             <th>Action</th>
                         </tr>
                     </thead>
                     <tbody>
                         <%
                             int count = 0;
                             for (ProductDTO dto : productList) {
                         %>
                     <form action="DispatchServlet">
                         <tr>
                             <td><%= ++count%></td>
                         <input type="hidden" name="txtProductName" value="<%= dto.getProductName()%>" />
                         <td> <%= dto.getProductName()%> </td>
                         <td><%= dto.getProductUnitPrice()%></td>
                         <td><input type="text" name="txtProductQuantity" value="" /></td>
                         <td><input type="submit" value="Add book to Your cart" name="btAction" /></td>
                         </tr>          
                     </form>
                     <%
                         }
                     %>
                     </tbody>
                 </table>
                 <input type="submit" value="View your cart" name="btAction" />
             </form>
             <%
             } else {
             %>
             Empty product List
             <%
                 }
             %> --%>


</body>
</html>
