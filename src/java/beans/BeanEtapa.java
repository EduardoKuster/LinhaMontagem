package beans;

import DAO.DaoEtapa;
import DAO.DaoFerramenta;
import DAO.DaoPeca;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Etapa;
import models.Ferramenta;
import models.Peca;

@ManagedBean
@ViewScoped
public class BeanEtapa {

    private int idetapa;
    private String descricao;
    private int peca;
    private int ferramenta;
    private String tempoestimadoString;

    public String getTempoestimadoString() {
        return tempoestimadoString;
    }

    public void setTempoestimadoString(String tempoestimadoString) {
        this.tempoestimadoString = tempoestimadoString;
    }
    private Date tempoestimado;
    private List<Ferramenta> ferramentas;
    private List<Peca> pecas;

    public int getPeca() {
        return peca;
    }

    public void setPeca(int peca) {
        this.peca = peca;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    public int getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(int ferramenta) {
        this.ferramenta = ferramenta;
    }

    public List<Ferramenta> getFerramentas() {
        return ferramentas;
    }

    public void setFerramentas(List<Ferramenta> ferramentas) {
        this.ferramentas = ferramentas;
    }

    private List<Etapa> lista = new ArrayList<>();

    public List<Etapa> getLista() {
        return lista;
    }

    public void setLista(List<Etapa> lista) {
        this.lista = lista;
    }

    public int getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(int idetapa) {
        this.idetapa = idetapa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getTempoestimado() {
        return tempoestimado;
    }

    public void setTempoestimado(Time tempoestimado) {
        this.tempoestimado = tempoestimado;
    }

    public String editarRedirect(int id) {
        return "editaEtapa.jsf?faces-redirect=true&idetapa=" + id;
    }

    public void buscar(int id) {
        Etapa e = DaoEtapa.buscar(id);
        if(e==null)
            return;
        this.idetapa = e.getIdetapa();
        this.descricao = e.getDescricao();
        this.tempoestimado = e.getTempoestimado();
        this.ferramenta = (e.getFkferramenta() == null? 0:e.getFkferramenta().getIdferramenta());
        this.peca = (e.getFkpeca() == null? 0:e.getFkpeca().getIdpeca());
    }

    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (descricao.equals("")) {
            msg = new FacesMessage("Informe a descrição da etapa");
            view.addMessage(null, msg);
        }

        if (tempoestimadoString.equals("")) {
            msg = new FacesMessage("Informe um tempo estimado para a etapa");
            view.addMessage(null, msg);
        }
        try {
            if (msg == null) {
                this.tempoestimado = new SimpleDateFormat("HH:mm:ss").parse(tempoestimadoString);
            }

            Etapa e = new Etapa(this.descricao, this.tempoestimado, this.ferramenta, this.peca);
            if (DaoEtapa.persist(e)) {
                msg = new FacesMessage("Etapa salva com sucesso");
                view.addMessage(null, msg);
            }
        } catch (Exception ex) {
            view.addMessage(null, new FacesMessage(ex.getMessage()));
        }
    }

     public String nomeferramenta(){
       return DaoFerramenta.buscar(this.ferramenta).getNome();
    }
     
     public void tempoString(){
         this.tempoestimadoString = new Time(tempoestimado.getTime()).toString();
     }
     
    public void buscarFerramentas() {
        List<Ferramenta> ferramentasEscolhas = new ArrayList<Ferramenta>();
        ferramentasEscolhas.add(new Ferramenta(0, "sem ferramenta"));
        ferramentasEscolhas.addAll(DaoFerramenta.todos());
        ferramentas = ferramentasEscolhas;
    }
    
     public void buscarPecas() {
        List<Peca> pecasEscolhas = new ArrayList<Peca>();
        pecasEscolhas.add(new Peca(0,"sem peça",0));
        pecasEscolhas.addAll(DaoPeca.todos());
        pecas = pecasEscolhas;
    }

    public void consultar() {
        this.lista = new Etapa().consultar();
    }   

    public String editar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

         try {
            if (msg == null) {
                this.tempoestimado = new SimpleDateFormat("HH:mm:ss").parse(tempoestimadoString);
            }      
        
        if (descricao.equals("")) {
            msg = new FacesMessage("Informe o nome da ferramenta");
            view.addMessage(null, msg);
        }
        if (msg == null) {
            Etapa e = new Etapa(this.idetapa, this.descricao, this.tempoestimado, this.ferramenta, this.peca);
            if (DaoEtapa.editar(e)) {
                msg = new FacesMessage("Etapa editada com sucesso");
                view.addMessage(null, msg);
            }
        }
            
        } catch (Exception ex) {
            view.addMessage(null, new FacesMessage(ex.getMessage()));
        }
        return "editaEtapa.jsf?faces-redirect=true&idetapa=" + idetapa;
    }

    public String deletar(int id) {
        FacesContext view = FacesContext.getCurrentInstance();
        Etapa e = DaoEtapa.buscar(id);
        DaoEtapa.deletar(e);
        view.addMessage(null, new FacesMessage("Etapa deletada com sucesso"));

        return "etapas?faces-redirect=true";
    }
}
