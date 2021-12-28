
package beans;

import DAO.DaoFerramenta;
import DAO.DaoSupervisor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Ferramenta;
import models.Funcionario;
import models.Supervisor;

@ManagedBean
@ViewScoped
public class BeanSupervisor {
    private int idsupervisor;
    private String nome;
    private String login;
    private String senha;
    private String setor;
    private List<Supervisor> lista;
    private boolean ehuser;

    public boolean getEhuser() {
        return ehuser;
    }

    public void setEhuser(boolean ehuser) {
        this.ehuser = ehuser;
    }
    public List<Supervisor> getLista() {
        return lista;
    }

    public void setLista(List<Supervisor> lista) {
        this.lista = lista;
    }
    private List<Funcionario> funcionarios;
    //private List<Projeto> projetos;
   
     public void buscar(int id){
        Supervisor s = DaoSupervisor.buscar(id);
        
        this.idsupervisor = s.getIdsupervisor();
        this.nome = s.getNome();
        this.login = s.getLogin();
        this.senha = s.getSenha();
        this.setor = s.getSetor();
     }

    public int getIdsupervisor() {
        return idsupervisor;
    }

    public void setIdsupervisor(int idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
     
    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome do usuário");
            view.addMessage(null, msg);
        }
        if (login.equals("")) {
            msg = new FacesMessage("Informe o login do usuário");
            view.addMessage(null, msg);
        }
        if (senha.equals("")) {
            msg = new FacesMessage("Informe a senha do usuário");
            view.addMessage(null, msg);
        }        
        
        
        if (msg == null) {
            Supervisor f = new Supervisor(this.nome, this.login, this.senha, this.setor);            
            if (DaoSupervisor.persist(f)) {
                msg = new FacesMessage("Usuário salva com sucesso");
                view.addMessage(null, msg);
            }
        }
    }
            
    public String editar(){
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome do supervisor");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Supervisor s = new Supervisor(this.idsupervisor, this.nome, this.login, this.senha, this.setor);            
            if (DaoSupervisor.editar(s)) {
                msg = new FacesMessage("Supervisor editada com sucesso");
                view.addMessage(null, msg);
            }
        }
         if(this.ehuser)
            return "supervisor.jsf";  
        return "editaSupervisor.jsf?faces-redirect=true&idsupervisor="+idsupervisor;  
      }      
    
      public String deletar(int id){
        FacesContext view = FacesContext.getCurrentInstance();     
        Supervisor f = DaoSupervisor.buscar(id);
        DaoSupervisor.deletar(f);     
        view.addMessage(null, new FacesMessage("Supervisor deletado com sucesso"));
        
        return "usuarios?faces-redirect=true";
     }      
      
    public String editarRedirect(int id){
       return "editaSupervisor.jsf?faces-redirect=true&idsupervisor="+id;      
    }
    
      public void consultar(){  
          this.lista = new Supervisor().todos();              
    }
}

