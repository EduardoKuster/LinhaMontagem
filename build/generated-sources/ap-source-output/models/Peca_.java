package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Etapa;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-28T20:06:18")
@StaticMetamodel(Peca.class)
public class Peca_ { 

    public static volatile SingularAttribute<Peca, Integer> fkferramenta;
    public static volatile SingularAttribute<Peca, String> nome;
    public static volatile CollectionAttribute<Peca, Etapa> etapaCollection;
    public static volatile SingularAttribute<Peca, Integer> idpeca;

}