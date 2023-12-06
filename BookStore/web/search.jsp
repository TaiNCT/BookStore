<%--
    Document   : search
    Created on : Sep 28, 2023, 2:30:45 PM
    Author     : ADMIN
--%>

<%-- <%@page import="taise.registration.RegistrationDTO"%> 
<%@page import="java.util.List"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%-- đặt tên theo nguyên tắc uri --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color ="red">
        Welcome, ${sessionScope.USER_INFO.fullName}
        </font>      
        <h1>Search page</h1>
        <form action="DispatchServlet" method="GET">
            Search Value <input type="text" name="txtSearchvalue" 
                                value="${param.txtSearchvalue}" /><br/>
            <input type="submit" value="Search" name="btAction"/>
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearchvalue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/> <%-- result = list RegistrationDTO --%>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text"  name="txtPassword" value="<%--${dto.password}--%>" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked = "checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="LastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form> 
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched
            </h2>
        </c:if>
    </c:if>
    <form action="DispatchServlet">
        <input type = "submit" value = "Logout" name = "btAction" />
    </form>
</body>
</html>
<%-- <%
     Cookie[] cookies = request.getCookies();
     if (cookies != null) {
         //Cookie lastCookie = cookies[cookies.length - 1];
         String username = "";
         for (Cookie cookie : cookies) {
             String tmp = cookie.getName();
             if (!tmp.equals("JSESSIONID")) {
                 username = tmp;
             }
         }
 %>
 <font color="red">
 Welcome, <%= username%>
 </font>
 <%
     }
 %>

        <h1>Search page</h1>
        <form action="DispatchServlet" method="GET">
            Search Value <input type="text" name="txtSearchvalue" 
                                value="<%= request.getParameter("txtSearchvalue")%>" /><br/>
            <input type="submit" value="Search" name="btAction"/>
        </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchvalue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {//end one or more record
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&LastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">   
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }//if user is admin
                               %>
                               />
                    </td> 
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                        <input type="submit" value="Update" name="btAction" />
                    </td>
                </tr>    

            </form>   

            <%
                }//end traverse DTO
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2>
        No record is mathed!!!
    </h2>
    <%
            }
        }//end search Value had inputed
%>
    <br/>
    <form action="DispatchServlet">
        <input type = "submit" value = "Logout" name = "btAction" />
    </form> --%>

