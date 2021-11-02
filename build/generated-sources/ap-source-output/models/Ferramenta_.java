package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Etapa;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-28T20:06:18")
@StaticMetamodel(Ferramenta.class)
public class Ferramenta_ { 

    public static volatile SingularAttribute<Ferramenta, Integer> tamanho;
    public static volatile SingularAttribute<Ferramenta, String> unidademedida;
    public static volatile SingularAttribute<Ferramenta, String> nome;
    public static volatile SingularAttribute<Ferramenta, Integer> idferramenta;
    public static volatile CollectionAttribute<Ferramenta, Etapa> etapaCollection;

}