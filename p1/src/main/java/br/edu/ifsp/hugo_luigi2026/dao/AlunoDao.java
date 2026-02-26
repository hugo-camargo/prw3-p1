package br.edu.ifsp.hugo_luigi2026.dao;

import br.edu.ifsp.hugo_luigi2026.modelo.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
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


    public void excluirAluno(String nome) {
        try {
            Aluno aluno = buscarAluno(nome);

            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();

            System.out.println("Aluno '" + nome + "' removido com sucesso!");

        } catch (NoResultException e) {
            System.out.println("[ERRO] Aluno não encontrado para exclusão.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("[ERRO] Falha ao excluir: " + e.getMessage());
        }
    }

    public void alterarAluno(String nome){
        try{
            Aluno aluno = buscarAluno(nome);

            Scanner scan = new Scanner(System.in);

            System.out.print("Digite o novo nome: ");
            String novoNome = scan.nextLine();

            System.out.print("Digite o novo RA: ");
            String novoRa = scan.nextLine();

            System.out.print("Digite o novo e-mail: ");
            String novoEmail = scan.nextLine();

            if(novoNome.isBlank() || novoRa.isBlank() || novoEmail.isBlank()){
                System.out.println("Nome, RA e e-mail não podem ser vazios!");
                return;
            }

            BigDecimal n1 = BigDecimal.ZERO;
            BigDecimal n2 = BigDecimal.ZERO;
            BigDecimal n3 = BigDecimal.ZERO;

            boolean notaInvalida = true;

            while(notaInvalida) {
                System.out.print("Digite a nova nota 1: ");
                n1 = scan.nextBigDecimal();

                System.out.print("Digite a nova nota 2: ");
                n2 = scan.nextBigDecimal();

                System.out.print("Digite a nova nota 3: ");
                n3 = scan.nextBigDecimal();

                if(n1.compareTo(BigDecimal.ZERO) < 0 || n1.compareTo(new BigDecimal(10)) > 0){
                    System.out.println("Nota 1 deve estar entre 0 e 10!");
                }else if (n2.compareTo(BigDecimal.ZERO) < 0 || n2.compareTo(new BigDecimal(10)) > 0){
                    System.out.println("Nota 2 deve estar entre 0 e 10!");
                }else if (n3.compareTo(BigDecimal.ZERO) < 0 || n3.compareTo(new BigDecimal(10)) > 0){
                    System.out.println("Nota 3 deve estar entre 0 e 10!");
                }else{
                    notaInvalida = false;
                }
            }

            em.getTransaction().begin();

            aluno.setNome(novoNome);
            aluno.setRa(novoRa);
            aluno.setEmail(novoEmail);
            aluno.setNota1(n1);
            aluno.setNota2(n2);
            aluno.setNota3(n3);

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

    public boolean buscarRa(String ra){

        String jpql = "SELECT a from Aluno a WHERE a.ra = :pRa";

        return em.createQuery(jpql, Aluno.class)
                .setParameter("pRa", ra)
                .getResultList()
                .isEmpty();
    }

    public List<Aluno> buscarTodosAlunos(){
        String jpql = "SELECT a FROM Aluno a";

        return em.createQuery(jpql,Aluno.class).getResultList();
    }


}
