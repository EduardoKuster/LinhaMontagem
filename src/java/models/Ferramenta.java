/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import DAO.DaoFerramenta;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "ferramenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ferramenta.findAll", query = "SELECT f FROM Ferramenta f"),
    @NamedQuery(name = "Ferramenta.findByIdferramenta", query = "SELECT f FROM Ferramenta f WHERE f.idferramenta = :idferramenta"),
    @NamedQuery(name = "Ferramenta.findByNome", query = "SELECT f FROM Ferramenta f WHERE f.nome = :nome"),
    @NamedQuery(name = "Ferramenta.findByUnidademedida", query = "SELECT f FROM Ferramenta f WHERE f.unidademedida = :unidademedida"),
    @NamedQuery(name = "Ferramenta.findByTamanho", query = "SELECT f FROM Ferramenta f WHERE f.tamanho = :tamanho")})
public class Ferramenta implements Serializable {
    @OneToMany(mappedBy = "fkferramenta")
    private Collection<Etapa> etapaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idferramenta")
    private Integer idferramenta;
    @Column(name = "nome")
    private String nome;
    @Column(name = "unidademedida")
    private String unidademedida;
    @Column(name = "tamanho")
    private Integer tamanho;

    public Ferramenta() {
    }

    public Ferramenta(Integer idferramenta) {
        this.idferramenta = idferramenta;
    }

     public Ferramenta(Integer idferramenta, String nome, String unidadeMedida, int tamanho) {
      this.idferramenta = idferramenta;
      this.nome= nome;
      this.unidademedida = unidadeMedida;
      this.tamanho = tamanho;
    }
    
    public Ferramenta(String nome, String unidadeMedida, int tamanho) {
      this.nome= nome;
      this.unidademedida = unidadeMedida;
      this.tamanho = tamanho;
    }

    public Ferramenta(int i, String semferramenta) {
        this.idferramenta = i; this.nome=semferramenta;
    }

    public Integer getIdferramenta() {
        return idferramenta;
    }

    public void setIdferramenta(Integer idferramenta) {
        this.idferramenta = idferramenta;
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

    public void setUnidademedida(String unidademedida) {
        this.unidademedida = unidademedida;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idferramenta != null ? idferramenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ferramenta)) {
            return false;
        }
        Ferramenta other = (Ferramenta) object;
        if ((this.idferramenta == null && other.idferramenta != null) || (this.idferramenta != null && !this.idferramenta.equals(other.idferramenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Ferramenta[ idferramenta=" + idferramenta + " ]";
    }

    public List<Ferramenta> consultar() {
      return DaoFerramenta.todos();
    }

    @XmlTransient
    public Collection<Etapa> getEtapaCollection() {
        return etapaCollection;
    }

    public void setEtapaCollection(Collection<Etapa> etapaCollection) {
        this.etapaCollection = etapaCollection;
    }
    
    
}
