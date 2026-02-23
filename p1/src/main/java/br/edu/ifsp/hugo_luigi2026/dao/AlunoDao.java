package br.edu.ifsp.hugo_luigi2026.dao;

import br.edu.ifsp.hugo_luigi2026.modelo.Aluno;
import jakarta.persistence.EntityManager;

public class AlunoDao {
    private EntityManager em;

    public AlunoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar (Aluno aluno){
        this.em.persist(aluno);
    }
}
