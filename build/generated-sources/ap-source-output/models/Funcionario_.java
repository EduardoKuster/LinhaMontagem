package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Projeto;
import models.Supervisor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-28T20:06:18")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, String> senha;
    public static volatile CollectionAttribute<Funcionario, Projeto> projetoCollection;
    public static volatile SingularAttribute<Funcionario, Integer> idfuncionario;
    public static volatile SingularAttribute<Funcionario, Supervisor> fksupervisor;
    public static volatile SingularAttribute<Funcionario, String> nome;
    public static volatile SingularAttribute<Funcionario, String> login;
    public static volatile SingularAttribute<Funcionario, String> cargo;

}