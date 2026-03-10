package edu.myschoolapp.aluno.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import edu.myschoolapp.aluno.model.Aluno;
import edu.myschoolapp.aluno.service.AlunoService;
import edu.myschoolapp.aluno.service.RelatorioService;

public class Menu {

    private final RelatorioService relatorioService;
    private final AlunoService alunoService;
    private final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Menu(AlunoService alunoService, RelatorioService relatorioService) {
        this.alunoService = alunoService;
        this.relatorioService = relatorioService;
    }
    
    public void iniciar() {
        int opcao;

        do {
            mostrarMenu();
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> listarAlunosAtivos();
                case 3 -> listarAlunosInativos();
                case 4 -> ativarAluno();
                case 5 -> desativarAluno();
                case 6 -> gerarRelacaoAlunos();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }

    private void cadastrarAluno() {
        try {
            System.out.print("Nome do aluno: ");
            String nome = sc.nextLine();
            System.out.print("CPF do aluno: ");
            String cpf = sc.nextLine();
            System.out.print("Data de Nascimento do aluno (dd/MM/yyyy): ");
            String strDataNascimento = sc.nextLine();
            LocalDate dataNascimento = LocalDate.parse(strDataNascimento, formatter);
            System.out.print("E-mail de contato: ");
            String email = sc.nextLine();
            System.out.print("Telefone do responsável: ");
            String telefone = sc.nextLine();

            Aluno aluno = new Aluno(nome, cpf, dataNascimento, email, true, telefone);
            alunoService.salvarAluno(aluno);

            System.out.println("Aluno cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void ativarAluno() {
        System.out.print("Informe o Id do Aluno: ");
        long id = sc.nextLong();
        sc.nextLine();
        try {
            alunoService.ativarAluno(id);
            System.out.println("Aluno ativado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void desativarAluno() {
        System.out.print("Informe o Id do Aluno: ");
        long id = sc.nextLong();
        sc.nextLine();
        try {
            alunoService.desativarAluno(id);
            System.out.println("Aluno desativado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    } 

    private void listarAlunosAtivos() {
        List<Aluno> alunos = alunoService.listarAlunosAtivos();
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos ativos!");
        }else {
            alunos.forEach(System.out::println);
        }
    }

    private void listarAlunosInativos() {
        List<Aluno> alunos = alunoService.listarAlunosInativos();
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos inativos!");
        } else {
            alunos.forEach(System.out::println);
        }
    }

    private void gerarRelacaoAlunos() {
        try {
            relatorioService.gerarRelatorio();
            System.out.println("Relação de alunos gerada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void mostrarMenu() {
        System.out.println("\n============ MENU ============");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Listar alunos ativos");
        System.out.println("3 - Listar alunos inativos");
        System.out.println("4 - Ativar aluno");
        System.out.println("5 - Desativar aluno");
        System.out.println("6 - Gerar relação de alunos");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }
}
