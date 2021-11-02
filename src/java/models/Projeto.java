/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import DAO.DaoProjeto;
import DAO.DaoSupervisor;
import java.io.Serializable;
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
@Table(name = "projeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p"),
    @NamedQuery(name = "Projeto.findByIdprojeto", query = "SELECT p FROM Projeto p WHERE p.idprojeto = :idprojeto"),
    @NamedQuery(name = "Projeto.findByNome", query = "SELECT p FROM Projeto p WHERE p.nome = :nome"),
    @NamedQuery(name = "Projeto.findByTempoprevisto", query = "SELECT p FROM Projeto p WHERE p.tempoprevisto = :tempoprevisto"),
    @NamedQuery(name = "Projeto.findByRepeticoes", query = "SELECT p FROM Projeto p WHERE p.repeticoes = :repeticoes"),
    @NamedQuery(name = "Projeto.findByRepeticaoatual", query = "SELECT p FROM Projeto p WHERE p.repeticaoatual = :repeticaoatual"),
    @NamedQuery(name = "Projeto.findBySituacao", query = "SELECT p FROM Projeto p WHERE p.situacao = :situacao")})
public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprojeto")
    private Integer idprojeto;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Column(name = "tempoprevisto")
    @Temporal(TemporalType.TIME)
    private Date tempoprevisto;
    @Column(name = "repeticoes")
    private Integer repeticoes;
    @Column(name = "repeticaoatual")
    private Integer repeticaoatual;
    @Column(name = "situacao")
    private Integer situacao;
    @JoinColumn(name = "fkfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne
    private Funcionario fkfuncionario;
    @JoinColumn(name = "fksupervisor", referencedColumnName = "idsupervisor")
    @ManyToOne
    private Supervisor fksupervisor;
    @OneToMany(mappedBy = "fkprojeto")
    private Collection<Etapasprojetos> etapasprojetosCollection;

    public Projeto() {
    }

    public Projeto(Integer idprojeto) {
        this.idprojeto = idprojeto;
    }

    public Integer getIdprojeto() {
        return idprojeto;
    }

    public Projeto(String nome, Integer repeticoes, Integer situacao, Funcionario fkfuncionario, Supervisor fksupervisor) {
        this.repeticaoatual = 0;
        this.nome = nome;
        this.tempoprevisto = tempoprevisto;
        this.repeticoes = repeticoes;
        this.situacao = situacao;
        this.fkfuncionario = fkfuncionario;
        this.fksupervisor = fksupervisor;
    }

    public Projeto(Integer idprojeto, String nome, Integer repeticoes, Integer situacao, Funcionario fkfuncionario, Supervisor fksupervisor) {
        this.idprojeto = idprojeto;
        this.nome = nome;
        this.tempoprevisto = tempoprevisto;
        this.repeticoes = repeticoes;
        this.repeticaoatual = repeticaoatual;
        this.situacao = situacao;
        this.fkfuncionario = fkfuncionario;
        this.fksupervisor = fksupervisor;
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

    public Funcionario getFkfuncionario() {
        return fkfuncionario;
    }

    public void setFkfuncionario(Funcionario fkfuncionario) {
        this.fkfuncionario = fkfuncionario;
    }

    public Supervisor getFksupervisor() {
        return fksupervisor;
    }

    public void setFksupervisor(Supervisor fksupervisor) {
        this.fksupervisor = fksupervisor;
    }

    @XmlTransient
    public Collection<Etapasprojetos> getEtapasprojetosCollection() {
        return etapasprojetosCollection;
    }

    public void setEtapasprojetosCollection(Collection<Etapasprojetos> etapasprojetosCollection) {
        this.etapasprojetosCollection = etapasprojetosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprojeto != null ? idprojeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.idprojeto == null && other.idprojeto != null) || (this.idprojeto != null && !this.idprojeto.equals(other.idprojeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Projeto[ idprojeto=" + idprojeto + " ]";
    }
     public List<Projeto> consultar() {
      return DaoProjeto.todos();
    }
     
     public String nomesupervisor(){
        if(fksupervisor != null)
            return fksupervisor.getNome();
        else 
            return "Sem supervisor";
    }
     
     public String tempoprevisto(){
         //calcular o tempo combinado das etapas restantes
         return "Sem tempo previsto";
     }
     
     public String repeticoesRestantes(){
         return String.valueOf(repeticoes - ((repeticaoatual == null)? 0:repeticaoatual));
     }
     
     public String etapaAtual(){
         //verificar qual etapa está sendo feita(precisaria de um campo status e sequencia na tabela Etapasprojetos)
         return "Projeto não iniciado";
     }
     
     public String buscarSituacao(){
         switch(this.situacao){
             case 1:
             {
                 return "Não Iniciado";
             }
             //bolar mais status
             default:
             {
                 return "Status inválido";
             }
         }
     }
}
