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
     
     public static List<Projeto> consultarVinculado(String id, String tipo){ 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Projeto> tq = null;
                
        if(tipo == "func")
            tq = em.createQuery("select p from Projeto p where p.fkfuncionario = :value1", Projeto.class).setParameter("value1", DaoFuncionario.buscar(Integer.valueOf(id)));
        else if(tipo == "sup")
            tq = em.createQuery("select p from Projeto p where p.fksupervisor = :value1", Projeto.class).setParameter("value1", DaoSupervisor.buscar(Integer.valueOf(id)));
        else 
            return null;
        
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
     
      
       public static boolean deletar(Projeto proj){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("LinhaMontagemPU");
        EntityManager em = emf.createEntityManager();   
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Projeto p where p.idprojeto = :value1").setParameter("value1", proj.getIdprojeto());
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
