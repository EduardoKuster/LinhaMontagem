/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import DAO.DaoSupervisor;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo Kuster
 */
@Entity
@Table(name = "supervisor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supervisor.findAll", query = "SELECT s FROM Supervisor s"),
    @NamedQuery(name = "Supervisor.findByIdsupervisor", query = "SELECT s FROM Supervisor s WHERE s.idsupervisor = :idsupervisor"),
    @NamedQuery(name = "Supervisor.findByNome", query = "SELECT s FROM Supervisor s WHERE s.nome = :nome"),
    @NamedQuery(name = "Supervisor.findByLogin", query = "SELECT s FROM Supervisor s WHERE s.login = :login"),
    @NamedQuery(name = "Supervisor.findBySenha", query = "SELECT s FROM Supervisor s WHERE s.senha = :senha"),
    @NamedQuery(name = "Supervisor.findByCargo", query = "SELECT s FROM Supervisor s WHERE s.cargo = :cargo"),
    @NamedQuery(name = "Supervisor.findBySetor", query = "SELECT s FROM Supervisor s WHERE s.setor = :setor")})
public class Supervisor implements Serializable {

    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 45)
    @Column(name = "login")
    private String login;
    @Size(max = 45)
    @Column(name = "senha")
    private String senha;
    @Size(max = 45)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 45)
    @Column(name = "setor")
    private String setor;
    @OneToMany(mappedBy = "fksupervisor")
    private Collection<Projeto> projetoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsupervisor")
    private Integer idsupervisor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fksupervisor")
    private Collection<Funcionario> funcionarioCollection;

    public Supervisor() {
    }

    public Supervisor(Integer idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public Supervisor(String nome, String login, String senha, String setor) {
        this.nome = nome; this.login = login; this.senha=senha;this.setor=setor;
    }

    public Supervisor(int idsupervisor, String nome, String login, String senha, String setor) {
         this.idsupervisor = idsupervisor;this.nome = nome; this.login = login; this.senha=senha;this.setor=setor;
    }

    public Integer getIdsupervisor() {
        return idsupervisor;
    }

    public void setIdsupervisor(Integer idsupervisor) {
        this.idsupervisor = idsupervisor;
    }


    @XmlTransient
    public Collection<Funcionario> getFuncionarioCollection() {
        return funcionarioCollection;
    }

    public void setFuncionarioCollection(Collection<Funcionario> funcionarioCollection) {
        this.funcionarioCollection = funcionarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsupervisor != null ? idsupervisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisor)) {
            return false;
        }
        Supervisor other = (Supervisor) object;
        if ((this.idsupervisor == null && other.idsupervisor != null) || (this.idsupervisor != null && !this.idsupervisor.equals(other.idsupervisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Supervisor[ idsupervisor=" + idsupervisor + " ]";
    }

    public List<Supervisor> todos() {
        return DaoSupervisor.todos();
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @XmlTransient
    public Collection<Projeto> getProjetoCollection() {
        return projetoCollection;
    }

    public void setProjetoCollection(Collection<Projeto> projetoCollection) {
        this.projetoCollection = projetoCollection;
    }    
     
}
