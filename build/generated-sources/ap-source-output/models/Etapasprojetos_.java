package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Etapa;
import models.Projeto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-27T13:10:48")
@StaticMetamodel(Etapasprojetos.class)
public class Etapasprojetos_ { 

    public static volatile SingularAttribute<Etapasprojetos, Etapa> fketapa;
    public static volatile SingularAttribute<Etapasprojetos, Integer> idetapasprojetos;
    public static volatile SingularAttribute<Etapasprojetos, Projeto> fkprojeto;

}