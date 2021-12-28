/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        if(session.getAttribute("user") == null){
            session.setAttribute("user", "Login");
            request.getSession().setAttribute("tipo", "");
        }                    
       
       RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsf");
       dispatcher.forward(request, response);
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}


