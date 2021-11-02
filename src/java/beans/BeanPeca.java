/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.DaoFerramenta;
import DAO.DaoPeca;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Ferramenta;
import models.Peca;

/**
 *
 * @author Eduardo
 */
@ManagedBean
@ViewScoped
public class BeanPeca {
    private int idpeca;
    private String nome;
    private int fkferramenta;
    private String ferramenta;
    private List<Ferramenta> ferramentas;

    public int getIdpeca() {
        return idpeca;
    }

    public List<Ferramenta> getFerramentas() {
        return ferramentas;
    }

    public void setFerramentas(List<Ferramenta> ferramentas) {
        this.ferramentas = ferramentas;
    }

    public void setIdpeca(int idpeca) {
        this.idpeca = idpeca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFkferramenta() {
        return fkferramenta;
    }

    public void setFkferramenta(int fkferramenta) {
        this.fkferramenta = fkferramenta;
    }

    public String getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(String ferramenta) {
        this.ferramenta = ferramenta;
    }

    public List<Peca> getLista() {
        return lista;
    }

    public void setLista(List<Peca> lista) {
        this.lista = lista;
    }
    private List<Peca> lista = new ArrayList<>();


    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome da Peça");
            view.addMessage(null, msg);
        }
        
        if (msg == null) {
            Peca p = new Peca(this.nome, this.fkferramenta);            
            if (DaoPeca.persist(p)) {
                msg = new FacesMessage("Peça salva com sucesso");
                view.addMessage(null, msg);
            }
        }
    }
    
    public String editarRedirect(int id){
       return "editaPeca.jsf?faces-redirect=true&idpeca="+id;      
    }
    
     public void buscar(int id){
        Peca p = DaoPeca.buscar(id);
        
        this.idpeca = p.getIdpeca();
        this.nome = p.getNome();
        this.fkferramenta = p.getFkferramenta();
     }
    
    public void consultar(){      
          //if(!filtrar)
          //     lista = new Produto().consultar(descricao, 0);
          //else
          this.lista = new Peca().consultar();              
    }
    
    public String nomeferramenta(){
       return DaoFerramenta.buscar(this.fkferramenta).getNome();
    }
    
    public void buscarFerramentas(){
        List<Ferramenta> ferramentasEscolhas = new ArrayList<Ferramenta>();
        ferramentasEscolhas.add(new Ferramenta(0, "sem ferramenta"));
        ferramentasEscolhas.addAll(DaoFerramenta.todos());    
        ferramentas = ferramentasEscolhas;
    }
    
    public String editar(){
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome da ferramenta");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Peca p = new Peca(this.idpeca, this.nome, this.fkferramenta);            
            if (DaoPeca.editar(p)) {
                msg = new FacesMessage("Ferramenta editada com sucesso");
                view.addMessage(null, msg);
            }
        }
        return "editaPeca.jsf?faces-redirect=true&idpeca="+idpeca;  
      }   
    
     public String deletar(int id){
        FacesContext view = FacesContext.getCurrentInstance();        
        DaoPeca.deletar(DaoPeca.buscar(id));     
        view.addMessage(null, new FacesMessage("Peça deletada com sucesso"));
        
        return "pecas?faces-redirect=true";
     }
}
