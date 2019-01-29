/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author :Aparajita Sahay
 * Shopping servlet handles user request to access shopping cart details.
 * @shopping servlet uses a statefull session bean.
 * Uses JNDI lookup to get EJB reference and to make session bean thread safe.
 */
public class shoppingServlet extends HttpServlet {

    //Access EJB interface 
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
            out.println("<title>Servlet shoppingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form action=\"shoppingServlet?checkout=true\" method=\"POST\">");
            ShoppingCartLocal cart = lookupShoppingCartLocal(request);
            if(request.getParameterMap().containsKey("checkout"))
            {
                int userId = Integer.parseInt(request.getSession().getAttribute("userid").toString());
                cart.checkout(userId);
            } else {
                Enumeration ids = request.getParameterNames();
                while(ids.hasMoreElements()){
                    String id = (String)ids.nextElement();
                    List<Wine> wine = wineFacade.findById(id);
                    if(wine != null && wine.size() != 0){
                        cart.addWine(wine.get(0));
                    }
                }
            }
            
            cart.print(out);
            out.println("<input type=\"submit\" value=\"Checkout\" >");
            out.println("</form>");

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

    private ShoppingCartLocal lookupShoppingCartLocal(HttpServletRequest request) {
        try {
            ShoppingCartLocal result = null;
            result = (ShoppingCartLocal)request.getSession().getAttribute("wineShoppingCart");
            if ( result != null ){
                return result;
            }
            Context c = new InitialContext();
            result = (ShoppingCartLocal) c.lookup("java:comp/env/ejb/ShoppingCart");
            request.getSession().setAttribute("wineShoppingCart", result);
            return result;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
