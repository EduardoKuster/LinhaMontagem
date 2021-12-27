/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Supervisor;

/**
 *
 * @author Eduardo Kuster
 */
@WebServlet(name = "UsuariosServlet", urlPatterns = {"/"})
public class UsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UsuariosServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null)
            session.setAttribute("user", "Login");
        
        //info da sessao
        //PrintWriter writer = response.getWriter();
       // writer.println("Session ID: " + session.getId());
       // writer.println("Creation Time: " + new Date(session.getCreationTime()));
       // writer. println("Last Accessed Time: " + new Date(session.getLastAccessedTime()));        
       // writer.println("username: " + session.getAttribute("user"));
       
       RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsf");
       dispatcher.forward(request, response);
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


