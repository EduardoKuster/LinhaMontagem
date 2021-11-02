/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import DAO.DaoFerramenta;
import DAO.DaoPeca;
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
@Table(name = "peca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peca.findAll", query = "SELECT p FROM Peca p"),
    @NamedQuery(name = "Peca.findByIdpeca", query = "SELECT p FROM Peca p WHERE p.idpeca = :idpeca"),
    @NamedQuery(name = "Peca.findByNome", query = "SELECT p FROM Peca p WHERE p.nome = :nome"),
    @NamedQuery(name = "Peca.findByFkferramenta", query = "SELECT p FROM Peca p WHERE p.fkferramenta = :fkferramenta")})
public class Peca implements Serializable {

    @OneToMany(mappedBy = "fkpeca")
    private Collection<Etapa> etapaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpeca")
    private Integer idpeca;
    @Column(name = "nome")
    private String nome;
    @Column(name = "fkferramenta")
    private Integer fkferramenta;

    public Peca() {
    }

    public Peca(Integer idpeca) {
        this.idpeca = idpeca;
    }

     public Peca(Integer idpeca, String nome, int fkferramenta) {
        this.nome = nome; this.fkferramenta = fkferramenta; this.idpeca = idpeca;
    }
     
    public Peca(String nome, int fkferramenta) {
        this.nome = nome; this.fkferramenta = fkferramenta;
    }

    public Integer getIdpeca() {
        return idpeca;
    }

    public void setIdpeca(Integer idpeca) {
        this.idpeca = idpeca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFkferramenta() {
        return fkferramenta;
    }

    public void setFkferramenta(Integer fkferramenta) {
        this.fkferramenta = fkferramenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpeca != null ? idpeca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peca)) {
            return false;
        }
        Peca other = (Peca) object;
        if ((this.idpeca == null && other.idpeca != null) || (this.idpeca != null && !this.idpeca.equals(other.idpeca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Peca[ idpeca=" + idpeca + " ]";
    }
    
     public List<Peca> consultar() {
      return DaoPeca.todos();
    }
     
    public String nomeferramenta(){
        if(DaoFerramenta.buscar(fkferramenta) != null)
            return DaoFerramenta.buscar(fkferramenta).getNome();
        else 
            return "Sem ferramenta";
    }

    @XmlTransient
    public Collection<Etapa> getEtapaCollection() {
        return etapaCollection;
    }

    public void setEtapaCollection(Collection<Etapa> etapaCollection) {
        this.etapaCollection = etapaCollection;
    }
    
    
}
