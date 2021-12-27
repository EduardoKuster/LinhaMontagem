package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Etapasprojetos;
import models.Funcionario;
import models.Supervisor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-27T13:10:48")
@StaticMetamodel(Projeto.class)
public class Projeto_ { 

    public static volatile SingularAttribute<Projeto, Integer> repeticaoatual;
    public static volatile SingularAttribute<Projeto, Integer> repeticoes;
    public static volatile SingularAttribute<Projeto, Integer> situacao;
    public static volatile SingularAttribute<Projeto, Funcionario> fkfuncionario;
    public static volatile SingularAttribute<Projeto, Date> tempoprevisto;
    public static volatile SingularAttribute<Projeto, Supervisor> fksupervisor;
    public static volatile SingularAttribute<Projeto, String> nome;
    public static volatile CollectionAttribute<Projeto, Etapasprojetos> etapasprojetosCollection;
    public static volatile SingularAttribute<Projeto, Integer> idprojeto;

}