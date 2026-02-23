package br.edu.ifsp.hugo_luigi2026.dao;

import br.edu.ifsp.hugo_luigi2026.modelo.Aluno;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlunoDao {
    private EntityManager em;

    public AlunoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar (Aluno aluno){
        this.em.persist(aluno);
    }

    /*
    public Aluno excluirAluno(String nome){

    }

    public Aluno alterarAluno(String nome){

    }

    public Aluno buscarAluno(String nome){

    }

    */

    /*
    public List<Aluno> buscarTodosAlunos(){
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = ?1";
        return em.createQuery(jpql, Aluno.class)
                .setParameter(1, nome)
                .getResultList();
    }

 */
}
