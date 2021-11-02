/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.DaoFuncionario;
import DAO.DaoPeca;
import DAO.DaoProjeto;
import DAO.DaoSupervisor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Etapa;
import models.Funcionario;
import models.Projeto;
import models.Supervisor;

@ManagedBean
@ViewScoped
public class BeanProjeto {
    private Integer idprojeto;
    private String nome;
    private Date tempoprevisto;
    private String tempoprevistoString;
    private Integer repeticoes;
    private Integer repeticaoatual;
    private Integer situacao;
    private int fkfuncionario;
    private int fksupervisor;
    private List<Projeto> lista;
    private List<Supervisor> supervisores;
    private List<Funcionario> funcionarios;
    private List<Etapa> etapas;

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Supervisor> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<Supervisor> supervisores) {
        this.supervisores = supervisores;
    }

       public String editarRedirect(int id){
       return "editaProjeto.jsf?faces-redirect=true&idprojeto="+id;      
    }

     public void buscar(int id){
        Projeto f = DaoProjeto.buscar(id);
        this.idprojeto = f.getIdprojeto();
        this.nome = f.getNome();
        this.situacao =f.getSituacao();
        this.repeticoes = f.getRepeticoes();
        this.fksupervisor = (f.getFksupervisor() != null)? f.getFksupervisor().getIdsupervisor() : 0;
        this.fkfuncionario = (f.getFkfuncionario()!= null)? f.getFkfuncionario().getIdfuncionario(): 0;
        //this.etapas = f.buscarEtapas();
     }
     
    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe um nome pro projeto");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Projeto f = new Projeto(this.nome, this.repeticoes, 1, null, DaoSupervisor.buscar(this.fksupervisor));            
            if (DaoProjeto.persist(f)) {
                msg = new FacesMessage("Projeto salvo com sucesso");
                view.addMessage(null, msg);
            }
        }
    }
     public void buscarSupervisores(){
        List<Supervisor> SuupervisorEscolhas = new ArrayList<Supervisor>();
        SuupervisorEscolhas.add(new Supervisor(0, "sem supervisor", "","","Sem Setor"));
        SuupervisorEscolhas.addAll(DaoSupervisor.todos());    
        supervisores = SuupervisorEscolhas;       
    }
     public void buscarFuncionarios(){
        List<Funcionario> FuncionarioEscolhas = new ArrayList<Funcionario>();
        FuncionarioEscolhas.add(new Funcionario(0, "sem Funcionario", "","","",0));
        FuncionarioEscolhas.addAll(DaoFuncionario.todos());    
        funcionarios = FuncionarioEscolhas;       
    }
    
    public void consultar(){  
          this.lista = new Projeto().consultar();              
    }

    
    public String editar(){
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome do Projeto");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Projeto f = new Projeto(this.idprojeto, this.nome, this.repeticoes, this.situacao, DaoFuncionario.buscar(this.fkfuncionario), DaoSupervisor.buscar(this.fksupervisor));            
            if (DaoProjeto.editar(f)) {
                msg = new FacesMessage("Projeto editado com sucesso");
                view.addMessage(null, msg);
            }
        }
        return "editaProjeto.jsf?faces-redirect=true&idprojeto="+idprojeto;  
      }      

    public Integer getIdprojeto() {
        return idprojeto;
    }

    public void setIdprojeto(Integer idprojeto) {
        this.idprojeto = idprojeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getTempoprevisto() {
        return tempoprevisto;
    }

    public void setTempoprevisto(Date tempoprevisto) {
        this.tempoprevisto = tempoprevisto;
    }

    public String getTempoprevistoString() {
        return tempoprevistoString;
    }

    public void setTempoprevistoString(String tempoprevistoString) {
        this.tempoprevistoString = tempoprevistoString;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Integer getRepeticaoatual() {
        return repeticaoatual;
    }

    public void setRepeticaoatual(Integer repeticaoatual) {
        this.repeticaoatual = repeticaoatual;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public int getFkfuncionario() {
        return fkfuncionario;
    }

    public void setFkfuncionario(int fkfuncionario) {
        this.fkfuncionario = fkfuncionario;
    }

    public int getFksupervisor() {
        return fksupervisor;
    }

    public void setFksupervisor(int fksupervisor) {
        this.fksupervisor = fksupervisor;
    }

    public List<Projeto> getLista() {
        return lista;
    }

    public void setLista(List<Projeto> lista) {
        this.lista = lista;
    }
    
      public String deletar(int id){
        FacesContext view = FacesContext.getCurrentInstance();     
        Projeto f = DaoProjeto.buscar(id);
        DaoProjeto.deletar(f);     
        view.addMessage(null, new FacesMessage("Projeto deletado com sucesso"));
        
        return "projetos?faces-redirect=true";
     }
      
      public String calcularTempoprevistoString(){
          return DaoProjeto.buscar(idprojeto).tempoprevisto();
      }
    
       public String nomeSupervisor(){
          if(fksupervisor != 0)
            return DaoSupervisor.buscar(fksupervisor).getNome();
          else 
            return "Sem supervisor";
    }
}
