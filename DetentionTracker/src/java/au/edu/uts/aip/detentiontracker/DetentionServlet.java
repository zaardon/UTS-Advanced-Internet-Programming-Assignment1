/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.edu.uts.aip.detentiontracker;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author asmith
 */
@WebServlet("/detention")
public class DetentionServlet extends HttpServlet {

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
       
        String cmd = request.getParameter("command");
        
        if(cmd == null){
            response.sendRedirect("detention?command=view");
        }
        else
        {
         switch (cmd){
             case "view":
                 viewCmd(request,response);
             case "create":
                 createCmd(request,response);
             case "remove":
                 removeCmd(request, response);
             case "edit":
                 editCmd(request, response);
            }                  
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

    protected void viewCmd(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    
        request.setAttribute("dList", getDetetionList(request));
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }
    
    protected void createCmd(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Detention detention = new Detention();
        
        detention.setFName(request.getParameter("fname"));
        detention.setLName(request.getParameter("sname"));
        detention.setYear(Integer.parseInt(request.getParameter("year")));
        detention.setType(request.getParameter("type"));
        detention.setDept(request.getParameter("dept"));
        detention.setReason(request.getParameter("reason"));
        detention.setDetentionList(getDetetionList(request)); //fsdfsfsd
        
        request.setAttribute("detention", detention);
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }
    
    protected void removeCmd(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("remove.jsp").forward(request, response);
    }
    
    protected void editCmd(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }
    
    
    protected DetentionList getDetetionList(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        DetentionList dList = (DetentionList)session.getAttribute("dList");
    
        if(null == dList){
            dList = new DetentionList();
            session.setAttribute("dList", dList);
        }
    
        return dList;
    }
}
