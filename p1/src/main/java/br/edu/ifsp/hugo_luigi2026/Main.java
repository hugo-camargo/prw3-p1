package br.edu.ifsp.hugo_luigi2026;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;

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
                    System.out.println(">> Iniciando cadastro de aluno...");
                    //chamar metodo cadastrarAluno();
                    break;
                case 2:
                    System.out.println(">> Digite o Nome para excluir...");
                    //chamar metodo excluiAluno(nome);
                    break;
                case 3:
                    System.out.println(">> Digite o Nome para Alterar os dados do aluno...");
                    //chamr metodo alterarAluno(nome);
                    break;
                case 4:
                    System.out.println(">> Digite o Nome para busca...");
                    //chamar metodo buscarAluno(nome)
                    break;
                case 5:
                    System.out.println(">> Listando alunos e status...");
                    //chamar metodo listarAlunos()
                    break;
                case 6:
                    System.out.println(">> Encerrando o sistema. Obrigado por usar! Até Logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        leitor.close();
    }
}