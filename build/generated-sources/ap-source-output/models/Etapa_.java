package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Etapasprojetos;
import models.Ferramenta;
import models.Peca;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-27T13:10:48")
@StaticMetamodel(Etapa.class)
public class Etapa_ { 

    public static volatile SingularAttribute<Etapa, Integer> idetapa;
    public static volatile SingularAttribute<Etapa, Ferramenta> fkferramenta;
    public static volatile CollectionAttribute<Etapa, Etapasprojetos> etapasprojetosCollection;
    public static volatile SingularAttribute<Etapa, Date> tempoestimado;
    public static volatile SingularAttribute<Etapa, Peca> fkpeca;
    public static volatile SingularAttribute<Etapa, String> descricao;

}