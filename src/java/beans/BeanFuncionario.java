
package beans;

import DAO.DaoFuncionario;
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
public class BeanFuncionario {
    private int idfuncionario;
    private String nome;
    private String login;
    private String senha;
    private String cargo;
    private List<Funcionario> lista;
     private List<Supervisor> supervisores;
    private int fksupervisor;

    public List<Funcionario> getLista() {
        return lista;
    }

    public List<Supervisor> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<Supervisor> supervisores) {
        this.supervisores = supervisores;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }
   
     public void buscar(int id){
        Funcionario s = DaoFuncionario.buscar(id);
        
        this.idfuncionario = s.getIdfuncionario();
        this.nome = s.getNome();
        this.login = s.getLogin();
        this.senha = s.getSenha();
        this.cargo = s.getCargo();
        if(s.getFksupervisor() != null)
            this.fksupervisor = s.getFksupervisor().getIdsupervisor();
        else
            this.fksupervisor = 0;
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

    public void buscarSupervisores(){
        List<Supervisor> SuupervisorEscolhas = new ArrayList<Supervisor>();
        SuupervisorEscolhas.add(new Supervisor(0, "sem supervisor", "","","Sem Setor"));
        SuupervisorEscolhas.addAll(DaoSupervisor.todos());    
        supervisores = SuupervisorEscolhas;       
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
            Funcionario f = new Funcionario(this.nome, this.login, this.senha, this.cargo, this.fksupervisor);            
            if (DaoFuncionario.persist(f)) {
                msg = new FacesMessage("Usuário salvo com sucesso");
                view.addMessage(null, msg);
            }
        }
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getFksupervisor() {
        return fksupervisor;
    }

    public void setFksupervisor(int fksupervisor) {
        this.fksupervisor = fksupervisor;
    }
            
    public String editar(){
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome do supervisor");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Funcionario s = new Funcionario(this.idfuncionario, this.nome, this.login, this.senha, this.cargo, this.fksupervisor);            
            if (DaoFuncionario.editar(s)) {
                msg = new FacesMessage("Funcionario editada com sucesso");
                view.addMessage(null, msg);
            }
        }
        return "editaFuncionario.jsf?faces-redirect=true&idfuncionario="+idfuncionario;  
      }      
    
      public String deletar(int id){
        FacesContext view = FacesContext.getCurrentInstance();     
        Funcionario f = DaoFuncionario.buscar(id);
        DaoFuncionario.deletar(f);     
        view.addMessage(null, new FacesMessage("Funcionario deletado com sucesso"));
        
        return "usuarios?faces-redirect=true";
     }      
      
    public String editarRedirect(int id){
       return "editaFuncionario.jsf?faces-redirect=true&idfuncionario="+id;      
    }
    
      public void consultar(){  
          this.lista = new Funcionario().todos();              
    }
}

