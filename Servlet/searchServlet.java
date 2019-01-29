/*
 *Author : Aparajita Sahay
    *Servlet uses wineFacadeLocal session bean to handle bussiness logic.
    *Sesion bean used is stateless
    *Search wine by category red / white and gives the result back to user.
 */
package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Funapp
 */
//{"/test","/category", "/addToCart", "/viewCart"}
//@WebServlet(name = "ShoppingServlet", urlPatterns = {"/searchServlet"})
public class searchServlet extends HttpServlet {

    @EJB
    private WineFacadeLocal wineFacade;

    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                    
            out.println("</head>");
            String wineCategory = request.getParameter("category");
            if (wineCategory != null && wineCategory.length() > 0) 
            {
		
                 List<Wine> wineResult = wineFacade.findByCategory(wineCategory);
                 out.print("<form method=\"post\" action=\"shoppingServlet\">");
                 out.print("<Title>List of Wines </Title>");
                 for(int i = 0;i<wineResult.size();i++)
                 {
                     Wine wine = wineResult.get(i);
                     out.println("</br>");
                     
                     out.println(String.format(
                                    "<input type=\"checkbox\" name=\"%s\" value\"%s\" >%s</input>", 
                                    wine.getId(), 
                                    wine.getId(),
                                    wine.getName()));
                 }
                   out.println("</br>");
                   out.println("<input type=\"submit\" value=\"Add to cart\" action=\"shoppingServlet\">");
                   //out.println("<input type=\"submit\" value=\"View Cart\" action=\"shoppingServlet\">");
                   out.print("</form>");
                
            }
            out.println("<body>");
            
            out.println("</body>");
            out.println("</html>");
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
