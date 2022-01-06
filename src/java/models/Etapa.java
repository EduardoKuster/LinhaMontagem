/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import DAO.DaoEtapa;
import DAO.DaoFerramenta;
import DAO.DaoPeca;
import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo Kuster
 */
@Entity
@Table(name = "etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etapa.findAll", query = "SELECT e FROM Etapa e"),
    @NamedQuery(name = "Etapa.findByIdetapa", query = "SELECT e FROM Etapa e WHERE e.idetapa = :idetapa"),
    @NamedQuery(name = "Etapa.findByDescricao", query = "SELECT e FROM Etapa e WHERE e.descricao = :descricao"),
    @NamedQuery(name = "Etapa.findByTempoestimado", query = "SELECT e FROM Etapa e WHERE e.tempoestimado = :tempoestimado")})
public class Etapa implements Serializable {

    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fketapa")
    private Collection<Etapasprojetos> etapasprojetosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idetapa")
    private Integer idetapa;
    @Column(name = "tempoestimado")
    @Temporal(TemporalType.TIME)
    private Date tempoestimado;
    @JoinColumn(name = "fkferramenta", referencedColumnName = "idferramenta")
    @ManyToOne
    private Ferramenta fkferramenta;
    @JoinColumn(name = "fkpeca", referencedColumnName = "idpeca")
    @ManyToOne
    private Peca fkpeca;

    public Etapa() {
    }

    public Etapa(Integer idetapa) {
        this.idetapa = idetapa;
    }

    public Etapa(int idetapa, String descricao, Date tempoestimado, int ferramenta, int peca) {
         this.idetapa = idetapa; this.descricao = descricao; this. tempoestimado = tempoestimado; this.fkferramenta = DaoFerramenta.buscar(ferramenta); this.fkpeca = DaoPeca.buscar(peca);
    }

    public Etapa(String descricao, Date tempoestimado, int ferramenta, int peca) {
       this.descricao = descricao; this. tempoestimado = tempoestimado; this.fkferramenta = DaoFerramenta.buscar(ferramenta); this.fkpeca = DaoPeca.buscar(peca);
    }

    public Integer getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(Integer idetapa) {
        this.idetapa = idetapa;
    }


    public Date getTempoestimado() {
        return tempoestimado;
    }

    public void setTempoestimado(Date tempoestimado) {
        this.tempoestimado = tempoestimado;
    }

    public Ferramenta getFkferramenta() {
        return fkferramenta;
    }

    public void setFkferramenta(Ferramenta fkferramenta) {
        this.fkferramenta = fkferramenta;
    }

    public Peca getFkpeca() {
        return fkpeca;
    }

    public void setFkpeca(Peca fkpeca) {
        this.fkpeca = fkpeca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapa != null ? idetapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etapa)) {
            return false;
        }
        Etapa other = (Etapa) object;
        if ((this.idetapa == null && other.idetapa != null) || (this.idetapa != null && !this.idetapa.equals(other.idetapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Etapa[ idetapa=" + idetapa + " ]";
    }
    
    public List<Etapa> consultar() {
      return DaoEtapa.todos();
    }
    
     public String nomeferramenta(){
        if(fkferramenta != null && DaoFerramenta.buscar(fkferramenta.getIdferramenta()) != null)
            return DaoFerramenta.buscar(fkferramenta.getIdferramenta()).getNome();
        else 
            return "Sem ferramenta";
    }
      public String nomepeca(){
        if(fkpeca != null && DaoPeca.buscar(fkpeca.getIdpeca()) != null)
            return DaoPeca.buscar(fkpeca.getIdpeca()).getNome();
        else 
            return "Sem peça";
    }
          
     public String tempoString(){
         return new Time(tempoestimado.getTime()).toString();
     }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Etapasprojetos> getEtapasprojetosCollection() {
        return etapasprojetosCollection;
    }

    public void setEtapasprojetosCollection(Collection<Etapasprojetos> etapasprojetosCollection) {
        this.etapasprojetosCollection = etapasprojetosCollection;
    }
}
