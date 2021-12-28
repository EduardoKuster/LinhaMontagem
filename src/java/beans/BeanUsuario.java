/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Util.UsuariosServlet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import models.Funcionario;
import models.Supervisor;

/**
 *
 * @author Eduardo
 */
@ViewScoped
@ManagedBean
public class BeanUsuario {
   
       public List<Object> todosusuarios(){  
          List<Object> users = new ArrayList<Object>();
          users.add(new Funcionario().todos());    
          users.add(new Supervisor().todos());    
          return users;
          
    }
}

