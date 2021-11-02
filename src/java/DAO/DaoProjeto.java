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
import javax.persistence.TypedQuery;
import models.Projeto;

/**
 *
 * @author Eduardo Kuster
 */
public class DaoProjeto {
     public static boolean persist(Projeto f){
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
     
     public static List<Projeto> todos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Projeto> tq = em.createQuery("select f  from Projeto f", Projeto.class);
        return tq.getResultList();
    }
     
     
     
     public static Projeto buscar(int Id){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");  
      EntityManager em = emf.createEntityManager();
      return em.find(Projeto.class, Id);    
    } 

    
   public static boolean delete(Projeto compromisso){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(compromisso)) {
            compromisso = em.merge(compromisso);
         }
        em.remove(compromisso);
        em.close();
        return true;         
    } 
    
     public static boolean editar(Projeto c){
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
     
      
       public static boolean deletar(Projeto ferramenta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(ferramenta)) {
            ferramenta = em.merge(ferramenta);
         }
        em.remove(ferramenta);
        em.close();
        return true;         
    } 
}
