<%-- 
    Document   : view
    Created on : 14/08/2014, 8:39:42 PM
    Author     : Alex
--%>
<%@page import="au.edu.uts.aip.detentiontracker.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Detentions</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <h1>View Detentions</h1>
        
        <% DetentionList dList = (DetentionList)session.getAttribute("dList");
        if (dList == null){
            dList = new DetentionList();
            session.setAttribute("dList", dList);
        }
        
        try{
            Detention detention = new Detention();
            detention.setFName(request.getParameter("fname"));
            detention.setLName(request.getParameter("lname"));
            detention.setYear(Integer.parseInt(request.getParameter("year")));
            detention.setType(request.getParameter("type"));
            detention.setDept(request.getParameter("dept"));
            detention.setReason(request.getParameter("reason"));
            detention.setDetentionList(dList);
        }
        catch(Exception e)
        {
            
        }

        %>
        
        
        <p>
        <%if (null == dList.getDetentions()){
                %>No current detentions </p>
        <% } 
         else {
                %>
            
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
            <% for(Detention current : dList.getDetentions()){

            %>
            <tr>
                <td><%=current.getFName() %></td>
                <td><%=current.getLName() %></td>
                <td><%=current.getYear()%></td>
                <td><%=current.getType()%></td>
                <td><%=current.getDept()%></td>
                <td><%=current.getReason()%></td>
            </tr>
            <% }
        } %>
            </table>
        </p>
        
        <jsp:include page="footer.jsp"/>
    </body>
</html>
