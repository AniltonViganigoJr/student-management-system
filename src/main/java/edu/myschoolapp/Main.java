package edu.myschoolapp;

import edu.myschoolapp.aluno.repository.AlunoRepository;
import edu.myschoolapp.aluno.service.AlunoService;
import edu.myschoolapp.aluno.service.RelatorioService;
import edu.myschoolapp.aluno.ui.Menu;

public class Main {

    public static void main(String[] args) {
        iniciarAplicacao();
    }
    
    public static void iniciarAplicacao() {
        final AlunoRepository alunoRepository = new AlunoRepository();
        final AlunoService alunoService = new AlunoService(alunoRepository);
        final RelatorioService relatorioService = new RelatorioService(alunoService);
        final Menu menu = new Menu(alunoService, relatorioService);
        menu.iniciar();
    }

}
