/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduardo Kuster
 */
@Entity
@Table(name = "etapasprojetos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etapasprojetos.findAll", query = "SELECT e FROM Etapasprojetos e"),
    @NamedQuery(name = "Etapasprojetos.findByIdetapasprojetos", query = "SELECT e FROM Etapasprojetos e WHERE e.idetapasprojetos = :idetapasprojetos")})
public class Etapasprojetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idetapasprojetos")
    private Integer idetapasprojetos;
    @JoinColumn(name = "fketapa", referencedColumnName = "idetapa")
    @ManyToOne
    private Etapa fketapa;
    @JoinColumn(name = "fkprojeto", referencedColumnName = "idprojeto")
    @ManyToOne
    private Projeto fkprojeto;

    public Etapasprojetos() {
    }

    public Etapasprojetos(Etapa fketapa, Projeto fkprojeto) {
        this.fketapa = fketapa;
        this.fkprojeto = fkprojeto;
    }

    public Etapasprojetos(Integer idetapasprojetos) {
        this.idetapasprojetos = idetapasprojetos;
    }

    public Integer getIdetapasprojetos() {
        return idetapasprojetos;
    }

    public void setIdetapasprojetos(Integer idetapasprojetos) {
        this.idetapasprojetos = idetapasprojetos;
    }

    public Etapa getFketapa() {
        return fketapa;
    }

    public void setFketapa(Etapa fketapa) {
        this.fketapa = fketapa;
    }

    public Projeto getFkprojeto() {
        return fkprojeto;
    }

    public void setFkprojeto(Projeto fkprojeto) {
        this.fkprojeto = fkprojeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapasprojetos != null ? idetapasprojetos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etapasprojetos)) {
            return false;
        }
        Etapasprojetos other = (Etapasprojetos) object;
        if ((this.idetapasprojetos == null && other.idetapasprojetos != null) || (this.idetapasprojetos != null && !this.idetapasprojetos.equals(other.idetapasprojetos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Etapasprojetos[ idetapasprojetos=" + idetapasprojetos + " ]";
    }
    
}
