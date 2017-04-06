/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oguia;


import entidades.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adoniran
 */
public class Oguia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //FAZER BANCO - FEITO
        // FAZER PERCISTENCE - FEITO
        //FAZER OPERAÇOES DE CRUD
                // Calendar c= Calendar.getInstance();
                //c.set(1980, 5, 26);
        Date data = new Date(1980, 5, 26);
        Usuario novoUser = criaUsuario("adoniran",data,"damashio@gorute.com","123456ff");
        
        EntityManagerFactory emf=null;
        EntityManager gerente = emf.createEntityManager();
        addUsuario(gerente, novoUser);
    }
    
    
    public static Usuario criaUsuario( String nome, Date dataNasc, String email, String senha){
    Usuario novoUser = new Usuario();
    novoUser.setDataNasc(dataNasc);
    novoUser.setEmail(email);
    novoUser.setNome(nome);
    novoUser.setSenha(senha);
            
       return novoUser;
    
    }

    private static void addUsuario(EntityManager em, Usuario user) {
         try {
           
            em.getTransaction().begin();           
            em.persist(user);           
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
