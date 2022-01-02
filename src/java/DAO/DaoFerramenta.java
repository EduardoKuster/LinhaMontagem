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
import models.Ferramenta;

/**
 *
 * @author Eduardo
 */
public class DaoFerramenta {
    
     public static boolean persist(Ferramenta f){
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
     
     public static List<Ferramenta> todos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Ferramenta> tq = em.createQuery("select f  from Ferramenta f", Ferramenta.class);
        return tq.getResultList();
    }
     
     
     
     public static Ferramenta buscar(int Id){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");  
      EntityManager em = emf.createEntityManager();
      return em.find(Ferramenta.class, Id);    
    } 

    
   public static boolean delete(Ferramenta compromisso){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(compromisso)) {
            compromisso = em.merge(compromisso);
         }
        em.remove(compromisso);
        em.close();
        return true;         
    } 
    
     public static boolean editar(Ferramenta c){
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
     
      
       public static boolean deletar(Ferramenta ferramenta){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();   
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Ferramenta f where f.idferramenta = :value1").setParameter("value1", ferramenta.getIdferramenta());
            int quantidadedeletada = query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
        return true;         
    } 
}
