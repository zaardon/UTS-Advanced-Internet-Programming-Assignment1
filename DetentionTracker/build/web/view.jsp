<%-- 
    Document   : view
    Created on : 14/08/2014, 8:39:42 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <h1>View Detentions</h1>
        
        <p>
            <table align="center" border="2" style="width:75%" >
            <tr>
              <th>First Name</th>
              <th>Last Name</th> 
              <th>Year</th>
              <th>Type</th>
              <th>Department</th>
              <th>Reason</th>
              <th style="width: 5px">Edit?</th>
            </tr>
            </table>
        </p>
        
        <jsp:include page="footer.jsp"/>
    </body>
</html>
