package br.edu.ifsp.hugo_luigi2026.testes;

import br.edu.ifsp.hugo_luigi2026.dao.AlunoDao;
import br.edu.ifsp.hugo_luigi2026.modelo.Aluno;
import br.edu.ifsp.hugo_luigi2026.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao alunoDao = new AlunoDao(em);

        while (opcao != 6) {
            System.out.println("\n--- SISTEMA DE CADASTRO DE ALUNOS ---");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar aluno (com status aprovação");
            System.out.println("6 - FIM");
            System.out.print("Escolha uma opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine();



            switch (opcao) {
                case 1:
                    System.out.println(">> CADASTRO DE ALUNO:");
                    Aluno novoAluno = new Aluno();
                    System.out.print("Nome Completo: ");
                    novoAluno.setNome(leitor.nextLine());

                    System.out.print("RA: ");
                    novoAluno.setRa(leitor.nextLine());

                    System.out.print("E-mail: ");
                    novoAluno.setEmail(leitor.nextLine());

                    System.out.print("Nota 1: ");
                    novoAluno.setNota1(leitor.nextBigDecimal());

                    System.out.print("Nota 2: ");
                    novoAluno.setNota2(leitor.nextBigDecimal());

                    System.out.print("Nota 3: ");
                    novoAluno.setNota3(leitor.nextBigDecimal());
                    leitor.nextLine();
                    try {
                        alunoDao.cadastrar(novoAluno);
                        System.out.println("\n [SUCESSO] Aluno cadastrado com êxito!");
                    }catch (Exception e){
                        System.err.println("\n[ERRO] Falha ao cadastrar: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(">> EXCLUIR ALUNO:");
                    //chamar metodo excluiAluno(nome);
                    System.out.print("Digite o nome: ");
                    String nome = leitor.nextLine();

                    alunoDao.excluirAluno(nome);
                    break;
                case 3:
                    System.out.println(">> ALTERAR ALUNO:");
                    //chamr metodo alterarAluno(nome);
                    break;
                case 4:
                    System.out.println(">> CONSULTAR ALUNO:");
                    System.out.println("Digite o nome: ");
                    String buscaAtual = leitor.nextLine();
                    try{
                        Aluno a = alunoDao.buscarAluno(buscaAtual);
                        System.out.println("Dados do Aluno:");
                        System.out.println("Nome: " + a.getNome());
                        System.out.println("RA: " + a.getRa());
                        System.out.println("E-mail: " + a.getEmail());
                        System.out.println("Notas: " + a.getNota1() + " - " + a.getNota2() + " - " + a.getNota3());

                    }catch (NoResultException e){
                        System.out.println("\n[ERRO] Aluno '" + buscaAtual + "' não encontrado! ");
                    }

                    break;
                case 5:
                    System.out.println(">> EXIBIR TODOS OS ALUNOS");
                    //chamar metodo listarAlunos()
                    break;
                case 6:
                    System.out.println(">> ENCERRANDO O SISTEMA. OBRIGADO POR USAR! ATÉ LOGO!");
                    em.close();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA! TENTE NOVAMENTE.");
                    break;
            }
        }

        leitor.close();
    }
}