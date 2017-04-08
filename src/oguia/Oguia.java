/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oguia;

import entidades.Atividade;
import entidades.Local;
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
        Date data = new Date(1980, 05, 26);
        Usuario novoUser = criaUsuario("adoniran", data, "damashio@gorute.com", "123456ff");
        //EntityManagerFactory  emf =null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OguiaPU");

        EntityManager gerente;
        gerente = emf.createEntityManager();
         
        addUsuario(gerente, novoUser);
        Usuario testUser = findUsuario(1,gerente);
        
        System.out.println("------Imprindo valores usuario------");
        System.out.println(testUser.getNome()+"/n"+testUser.getEmail());
        
       /*
        Date dataini = new Date(2017, 05, 18);
        Date datafim = new Date(2017, 05, 24);
        Atividade ativi = criaAtividade(dataini, datafim, "Comic con" );
        addAtividade(gerente, ativi);
        
        Atividade ativ = findAtividade(1, gerente);
        System.out.println("------Imprindo valores Atividade------");
        System.out.println(ativ.getNome()+"/n "+ativ.getDescrição()+ " /n inicio em :"+ativ.getDataInicio());*/
        emf.close();
    }
//acha usuario  
    public static Usuario findUsuario(int id, EntityManager gerente) {

        return gerente.find(Usuario.class, id);
    }
    
    
    public static Atividade findAtividade(int id, EntityManager gerente) {

        return gerente.find(Atividade.class, id);
    }

    public static Usuario criaUsuario(String nome, Date dataNasc, String email, String senha) {
        Usuario novoUser = new Usuario();
        novoUser.setDataNasc(dataNasc);
        novoUser.setEmail(email);
        novoUser.setNome(nome);
        novoUser.setSenha(senha);

        return novoUser;

    }
    private static Atividade criaAtividade(Date dataInicio, Date dataFnal, String nome )
    { 
        Atividade novaAtividade = new Atividade();
            novaAtividade.setDataInicio(dataInicio);
            novaAtividade.setDataFnal(dataFnal);
            novaAtividade.setNome(nome);
            novaAtividade.setDescrição("é um misterio os segredos do universo");
            
            Local local = criaLocal("PE", "Recife", "barirro do recife", "mamute banguela");
            
            novaAtividade.setLocal(local);
    
    
        return novaAtividade;
    }
    
    private static Local criaLocal( String uf, String cidade, String bairro, String rua)
    { 
        Local local = new Local();
        local.setUf(uf);
        local.setCidade(cidade);
        local.setBairro(bairro);
       local.setRua(rua);
        
    
    
        return local;
    }
    
    private static Local criaLocal( String uf, String cidade, String bairro, String rua, String cep)
    { 
        Local local = new Local();
        local.setUf(uf);
        local.setCidade(cidade);
        local.setBairro(bairro);
       local.setRua(rua);
       local.setCep(cep);
        
    
    
        return local;
    }
    
    
    
    private static void addAtividade(EntityManager em, Atividade at) {
        try {

            em.getTransaction().begin();
            em.persist(at);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
               // em.close();
            }
        }
    }

    private static void addUsuario(EntityManager em, Usuario user) {
        try {

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
               // em.close();
            }
        }
    }
   /* private void edita (int id,EntityManager gerente ,String senha)
    {
        Usuario UsuarioEdit = findUsuario(id, gerente);
        UsuarioEdit.setSenha(senha);
        
    }*/

    private static void Remove(EntityManager em, Usuario user) {
        try {

            em.getTransaction().begin();

            //remove lista de atividades do usuario antes de remover para nao ficar filhos orfaos
            if(user.getAtividadeCollection().isEmpty() || user.getAtividadeCollection() == null){
            Collection<Atividade> atividadeCollection = user.getAtividadeCollection();
            //percorre a coleção e remove os usuarios
            for (Atividade CollectionAtividade : atividadeCollection) {
                CollectionAtividade.getUsuarioCollection().remove(user);
                CollectionAtividade = em.merge(CollectionAtividade);
               
            }
            }

            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }
    
    private static void RemoveAtividade(EntityManager em, Atividade ativ) {
        try {

            em.getTransaction().begin();  
            em.remove(ativ);
            //falta remover a atividade das listas de atividades dos usuarios
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

}
