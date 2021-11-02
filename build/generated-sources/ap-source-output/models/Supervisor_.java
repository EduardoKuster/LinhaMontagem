package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Funcionario;
import models.Projeto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-28T20:06:18")
@StaticMetamodel(Supervisor.class)
public class Supervisor_ { 

    public static volatile SingularAttribute<Supervisor, String> senha;
    public static volatile SingularAttribute<Supervisor, String> setor;
    public static volatile CollectionAttribute<Supervisor, Projeto> projetoCollection;
    public static volatile CollectionAttribute<Supervisor, Funcionario> funcionarioCollection;
    public static volatile SingularAttribute<Supervisor, String> nome;
    public static volatile SingularAttribute<Supervisor, Integer> idsupervisor;
    public static volatile SingularAttribute<Supervisor, String> login;
    public static volatile SingularAttribute<Supervisor, String> cargo;

}