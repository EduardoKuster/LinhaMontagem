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
import models.Funcionario;
/**
 *
 * @author Eduardo
 */
public class DaoFuncionario {
    
     public static boolean persist(Funcionario s){
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
     
     public static List<Funcionario> todos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Funcionario> tq = em.createQuery("select f  from Funcionario f", Funcionario.class);
        return tq.getResultList();
    }
     
     
     
     public static Funcionario buscar(int Id){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");  
      EntityManager em = emf.createEntityManager();
      return em.find(Funcionario.class, Id);    
    } 

    
   public static boolean delete(Funcionario func){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(func)) {
            func = em.merge(func);
         }
        em.remove(func);
        em.close();
        return true;         
    } 
    
     public static boolean editar(Funcionario func){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(func);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
     
      public static Funcionario encontraLogin(String login){       
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");  
      EntityManager em = emf.createEntityManager();
      if(login == "")
         return null;
      try{        
        Funcionario sup = (Funcionario) em.createQuery("SELECT s FROM Funcionario s where s.login = :value1").setParameter("value1", login).getSingleResult();
        return sup;    
      }catch(Exception ex){
          return null;
      }
    } 
      
       public static boolean deletar(Funcionario func){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        if (!em.contains(func)) {
            func = em.merge(func);
         }
        em.remove(func);
        em.close();
        return true;         
    } 
}
