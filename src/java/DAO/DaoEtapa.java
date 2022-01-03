/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import models.Etapa;
import models.Etapasprojetos;

/**
 *
 * @author Eduardo Kuster
 */
public class DaoEtapa {
 
     public static boolean persist(Etapa f){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
     
     public static boolean persistProj(Etapasprojetos e){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
     
     public static List<Etapa> todos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Etapa> tq = em.createQuery("select e  from Etapa e", Etapa.class);
        return tq.getResultList();
    }
     
     public static Etapa buscar(int Id){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");  
      EntityManager em = emf.createEntityManager();
      return em.find(Etapa.class, Id);    
    } 

    
   public static boolean delete(Etapa compromisso){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(compromisso)) {
            compromisso = em.merge(compromisso);
         }
        em.remove(compromisso);
        em.close();
        return true;         
    } 
    
     public static boolean editar(Etapa c){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
     
      
       public static boolean deletar(Etapa etapa){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();   
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Etapa e where e.idetapa = :value1").setParameter("value1", etapa.getIdetapa());
            int quantidadedeletada = query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
        return true;       
    } 

    public static void deletarDoProj(Integer idetapa, Integer idprojeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();       
        TypedQuery<Etapasprojetos> tq = em.createQuery("select ep from Etapa e inner join e.etapasprojetosCollection ep where ep.fkprojeto = :value1 and ep.fketapa = :value2", Etapasprojetos.class).setParameter("value1", DaoProjeto.buscar(idprojeto)).setParameter("value2", DaoEtapa.buscar(idetapa));        
        Etapasprojetos ep = tq.getSingleResult();
        //em.remove(ep);// não funciona //broken
       
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Etapasprojetos ep where ep.idetapasprojetos = :value1").setParameter("value1", ep.getIdetapasprojetos());
            int quantidadedeletada = query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
    }

    public static List<Etapa> consultarProjeto(int idprojeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();       
        TypedQuery<Etapa> tq = em.createQuery("select e from Etapa e inner join e.etapasprojetosCollection ep where ep.fkprojeto = :value1", Etapa.class).setParameter("value1", DaoProjeto.buscar(idprojeto));        
        return tq.getResultList();
    }
}
