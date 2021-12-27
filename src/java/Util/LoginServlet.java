/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DAO.DaoSupervisor;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/loginServlet", "/logout.jsf"})
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder erros = new StringBuilder();
        if (request.getParameter("bOK") != null) {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            if (login == null || login.isEmpty()) {
                erros.append("Login não informado!\n");
            }
            if (senha == null || senha.isEmpty()) {
                erros.append("Senha não informada!\n");
            }
            if (erros.toString().isEmpty()) {
                DaoSupervisor dao = new DaoSupervisor();
                Supervisor user = dao.encontraLogin(login);
                if (user != null) {
                    if (user.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("user", user.getNome());
                        response.sendRedirect("index.jsf");
                        return;
                    } else {
                        erros.append("Senha incorreta!");
                    }
                } else {
                    erros.append("Usuário não encontrado!");
                }
            }

        }
        request.getSession().invalidate();
        
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null)
            session.setAttribute("user", "Login");
        
        request.setAttribute("mensagens", erros);
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsf");
        if(erros.toString() == ""){                
        rd = request.getRequestDispatcher("index.jsf");
        }
        else{
           rd = request.getRequestDispatcher("login.jsf");
        }
        rd.forward(request, response);
    }  
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}