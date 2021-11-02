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
import models.Supervisor;

/**
 *
 * @author Eduardo
 */
public class DaoSupervisor {
    
     public static boolean persist(Supervisor s){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
     
     public static List<Supervisor> todos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Supervisor> tq = em.createQuery("select f  from Supervisor f", Supervisor.class);
        return tq.getResultList();
    }
     
     
     
     public static Supervisor buscar(int Id){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");  
      EntityManager em = emf.createEntityManager();
      if(Id==0)
         return null;
      return em.find(Supervisor.class, Id);    
    } 

    
   public static boolean delete(Supervisor supervisor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(supervisor)) {
            supervisor = em.merge(supervisor);
         }
        em.remove(supervisor);
        em.close();
        return true;         
    } 
    
     public static boolean editar(Supervisor c){
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
     
      
       public static boolean deletar(Supervisor supervisor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(supervisor)) {
            supervisor = em.merge(supervisor);
         }
        em.remove(supervisor);
        em.close();
        return true;         
    } 
}
