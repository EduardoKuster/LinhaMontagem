package beans;

import DAO.DaoFerramenta;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Ferramenta;

@ManagedBean
@ViewScoped
public class BeanFerramenta {
private int idferramenta;
private String nome;
private String unidademedida;
private int tamanho;
private List<Ferramenta> lista = new ArrayList<>();

    public List<Ferramenta> getLista() {
        return lista;
    }

    public void setLista(List<Ferramenta> lista) {
        this.lista = lista;
    }

    public int getIdferramenta() {
        return idferramenta;
    }

    public void setIdferramenta(int idFerramenta) {
        this.idferramenta = idFerramenta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidademedida() {
        return unidademedida;
    }

    public void setUnidademedida(String UnidadeMedida) {
        this.unidademedida = UnidadeMedida;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
     public String editarRedirect(int id){
       return "editaFerramenta.jsf?faces-redirect=true&idferramenta="+id;      
    }

     public void buscar(int id){
        Ferramenta f = DaoFerramenta.buscar(id);
        
        this.idferramenta = f.getIdferramenta();
        this.nome = f.getNome();
        this.tamanho = f.getTamanho();
        this.unidademedida = f.getUnidademedida();
     }
     
    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome da ferramenta");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Ferramenta f = new Ferramenta(this.nome, this.unidademedida, this.tamanho);            
            if (DaoFerramenta.persist(f)) {
                msg = new FacesMessage("Ferramenta salva com sucesso");
                view.addMessage(null, msg);
            }
        }
    }
    
    public void consultar(){  
          //if(!filtrar)
          //     lista = new Produto().consultar(descricao, 0);
          //else
          this.lista = new Ferramenta().consultar();              
    }

    
    public String editar(){
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.equals("")) {
            msg = new FacesMessage("Informe o nome da ferramenta");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Ferramenta f = new Ferramenta(this.idferramenta, this.nome, this.unidademedida, this.tamanho);            
            if (DaoFerramenta.editar(f)) {
                msg = new FacesMessage("Ferramenta editada com sucesso");
                view.addMessage(null, msg);
            }
        }
        return "editaFerramenta.jsf?faces-redirect=true&idferramenta="+idferramenta;  
      }      
    
      public String deletar(int id){
        FacesContext view = FacesContext.getCurrentInstance();     
        Ferramenta f = DaoFerramenta.buscar(id);
        DaoFerramenta.deletar(f);     
        view.addMessage(null, new FacesMessage("Ferramenta deletada com sucesso"));
        
        return "ferramentas?faces-redirect=true";
     }
}
