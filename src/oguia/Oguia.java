/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oguia;


import entidades.Atividade;
import entidades.Usuario;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        //"OguiaPU"EntityManagerFactory  emf =null;
       EntityManagerFactory  emf = Persistence.createEntityManagerFactory("OguiaPU");
             
        EntityManager gerente;
        gerente = emf.createEntityManager();
     //acha usuario   
    gerente.find(Usuario.class, 2);
        addUsuario(gerente, novoUser);
        emf.close();
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
    private static void Remove(EntityManager em, Usuario user) {
         try {
           
            em.getTransaction().begin();
            
            //remove lista de atividades do usuario antes de remover para nao ficar filhos orfaos
            Collection<Atividade> atividadeCollection = user.getAtividadeCollection();
                //percorre a coleção e remove os usuarios
            for (Atividade CollectionAtividade : atividadeCollection) {
                CollectionAtividade.getUsuarioCollection().remove(user);
                CollectionAtividade = em.merge(CollectionAtividade);
            }
            
                em.remove(user);            
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    
    
}
