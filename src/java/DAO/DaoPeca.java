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
import models.Peca;

/**
 *
 * @author Eduardo
 */
public class DaoPeca {
     
     public static boolean persist(Peca p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
     
     public static List<Peca> todos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Peca> tq = em.createQuery("select p  from Peca p", Peca.class);
        return tq.getResultList();
    }
     
     public static Peca buscar(int Id){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");  
      EntityManager em = emf.createEntityManager();
      return em.find(Peca.class, Id);    
    } 

    
   public static boolean deletar(Peca peca){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();   
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Peca p where p.idpeca = :value1").setParameter("value1", peca.getIdpeca());
            int quantidadedeletada = query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
        return true;         
    } 
    
     public static boolean editar(Peca p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
     
}
