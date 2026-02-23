package br.edu.ifsp.hugo_luigi2026.dao;

import br.edu.ifsp.hugo_luigi2026.modelo.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

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

    public void alterarAluno(String nome){
        try{
            String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
            List<Aluno> lista = em.createQuery(jpql, Aluno.class).setParameter("n", nome).getResultList();

            if(lista.isEmpty()){
                System.out.println("Aluno não encontrado");
                return;
            }
            Aluno a = lista.get(0);

            Scanner scan = new Scanner(System.in);

            System.out.print("Digite o novo nome: ");
            String novoNome = scan.nextLine();

            System.out.print("Digite o novo RA: ");
            String novoRa = scan.nextLine();

            System.out.print("Digite o novo e-mail: ");
            String novoEmail = scan.nextLine();

            System.out.print("Digite a nova nota 1: ");
            BigDecimal n1 = scan.nextBigDecimal();

            System.out.print("Digite a nova nota 2: ");
            BigDecimal n2 = scan.nextBigDecimal();

            System.out.print("Digite a nova nota 3: ");
            BigDecimal n3 = scan.nextBigDecimal();

            if(novoNome.isBlank() || novoRa.isBlank() || novoEmail.isBlank()){
                System.out.println("Nome, RA e e-mail não podem ser vazios!");
                return;
            }
            em.getTransaction().begin();

            a.setNome(novoNome);
            a.setRa(novoRa);
            a.setEmail(novoEmail);
            a.setNota1(n1);
            a.setNota2(n2);
            a.setNota3(n3);

            em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

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
