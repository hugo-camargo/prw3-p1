package br.edu.ifsp.hugo_luigi2026.dao;

import br.edu.ifsp.hugo_luigi2026.modelo.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AlunoDao {
    private EntityManager em;

    public AlunoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar (Aluno aluno){
        try{
            em.getTransaction().begin();
            this.em.persist(aluno);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        }

    }


    public void excluirAluno(String nome){
        try{
            String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";

            List<Aluno> lista = em.createQuery(jpql, Aluno.class)
                    .setParameter("n", nome)
                    .getResultList();

            if(lista.isEmpty()){
                System.out.println("Aluno não encontrado!");
            } else {
                em.getTransaction().begin();

                for (Aluno a : lista) {
                    em.remove(a);
                }

                em.getTransaction().commit();
                System.out.println("Aluno removido com sucesso!");
            }
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }
    /*
    public Aluno alterarAluno(String nome){

    }
    */

    public Aluno buscarAluno(String nome){
        String jpql = "SELECT a from Aluno a WHERE a.nome = :pNome";

        return em.createQuery(jpql,Aluno.class)
                .setParameter("pNome", nome)
                .getSingleResult();
    }

    public List<Aluno> buscarTodosAlunos(){
        String jpql = "SELECT a FROM Aluno a";

        return em.createQuery(jpql,Aluno.class).getResultList();
    }




}
