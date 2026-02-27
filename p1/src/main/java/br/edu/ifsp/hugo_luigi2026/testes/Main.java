package br.edu.ifsp.hugo_luigi2026.testes;

import br.edu.ifsp.hugo_luigi2026.dao.AlunoDao;
import br.edu.ifsp.hugo_luigi2026.modelo.Aluno;
import br.edu.ifsp.hugo_luigi2026.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// Luigi Anotonio Loddi Vanzella
// Hugo Camargo
public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);

        try (Scanner leitor = new Scanner(System.in)) {
            int opcao = 0;

            while (opcao != 6) {
                exibirMenu();


                if (leitor.hasNextInt()) {
                    opcao = leitor.nextInt();
                    leitor.nextLine(); // Limpa o buffer
                } else {
                    System.out.println("Por favor, digite um número válido.");
                    leitor.nextLine(); // Limpa a entrada inválida
                    continue;
                }

                switch (opcao) {
                    case 1 -> cadastrarAluno(leitor, alunoDao);
                    case 2 -> excluirAluno(leitor, alunoDao);
                    case 3 -> alterarAluno(leitor, alunoDao);
                    case 4 -> consultarAluno(leitor, alunoDao);
                    case 5 -> listarAlunos(alunoDao);
                    case 6 -> {
                        System.out.println(">> ENCERRANDO O SISTEMA. OBRIGADO POR USAR! ATÉ LOGO!");
                        em.close();
                    }
                    default -> System.out.println("OPÇÃO INVÁLIDA! TENTE NOVAMENTE.");
                }
            }
        }
    }


    private static void exibirMenu() {
        System.out.println("\n--- SISTEMA DE CADASTRO DE ALUNOS ---");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Excluir aluno");
        System.out.println("3 - Alterar aluno");
        System.out.println("4 - Buscar aluno pelo nome");
        System.out.println("5 - Listar aluno (com status aprovação)");
        System.out.println("6 - FIM");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarAluno(Scanner leitor, AlunoDao alunoDao) {
        System.out.println(">> CADASTRO DE ALUNO:");
        Aluno novoAluno = new Aluno();

        String nome, ra, email;
        System.out.print("Nome Completo: ");
        nome = leitor.nextLine();

        System.out.print("RA: ");
        ra = leitor.nextLine();
        if (!alunoDao.buscarRa(ra)){
          System.err.println("\n[ERRO] Este RA já esta cadastrado com outro aluno ");
        }else{
          System.out.print("E-mail: ");
          email = leitor.nextLine();

          if(nome.isBlank() || ra.isBlank() || email.isBlank()) {
              System.out.println("Nome, RA e e-mail não podem ser vazios!");
              return;
          }

          novoAluno.setNome(nome);
          novoAluno.setRa(ra);
          novoAluno.setEmail(email);

          boolean notaInvalida = true;
          BigDecimal n1 = BigDecimal.ZERO;
          BigDecimal n2 = BigDecimal.ZERO;
          BigDecimal n3 = BigDecimal.ZERO;

          while(notaInvalida) {
              System.out.print("Digite a nova nota 1: ");
              n1 = leitor.nextBigDecimal();

              System.out.print("Digite a nova nota 2: ");
              n2 = leitor.nextBigDecimal();

              System.out.print("Digite a nova nota 3: ");
              n3 = leitor.nextBigDecimal();

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

          novoAluno.setNota1(n1);
          novoAluno.setNota2(n2);
          novoAluno.setNota3(n3);

          try {
              alunoDao.cadastrar(novoAluno);
              System.out.println("\n [SUCESSO] Aluno cadastrado com êxito!");
          } catch (Exception e) {
              System.err.println("\n[ERRO] Falha ao cadastrar: " + e.getMessage());
          }
        }

    }

    private static void excluirAluno(Scanner leitor, AlunoDao alunoDao) {
        System.out.print(">> EXCLUIR ALUNO - Digite o nome: ");
        alunoDao.excluirAluno(leitor.nextLine());
    }

    private static void alterarAluno(Scanner leitor, AlunoDao alunoDao) {
        System.out.print(">> ALTERAR ALUNO - Digite o nome: ");
        alunoDao.alterarAluno(leitor.nextLine());
    }

    private static void consultarAluno(Scanner leitor, AlunoDao alunoDao) {
        System.out.print(">> CONSULTAR ALUNO - Digite o nome: ");
        String busca = leitor.nextLine();
        try {
            Aluno a = alunoDao.buscarAluno(busca);
            System.out.println("\nDados do Aluno:");
            System.out.println("Nome: " + a.getNome() + " | RA: " + a.getRa());
            System.out.println("E-mail: " + a.getEmail());
            System.out.println("Notas: " + a.getNota1() + " - " + a.getNota2() + " - " + a.getNota3());
        } catch (NoResultException e) {
            System.out.println("\n[ERRO] Aluno '" + busca + "' não encontrado!");
        }
    }

    private static void listarAlunos(AlunoDao alunoDao) {
        List<Aluno> lista = alunoDao.buscarTodosAlunos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado no momento.");
        } else {
            for (Aluno al : lista) {
                System.out.println("\n-------------------------");
                System.out.println("Nome: " + al.getNome() + " | Status: >> " + al.status() + " <<");
                System.out.println("RA: " + al.getRa());
                System.out.println("E-mail: " + al.getEmail());
                System.out.println("Notas: " + al.getNota1() + " - " + al.getNota2() + " - " + al.getNota3());
                System.out.println("Média: " + al.getMedia());

            }
        }
    }
}