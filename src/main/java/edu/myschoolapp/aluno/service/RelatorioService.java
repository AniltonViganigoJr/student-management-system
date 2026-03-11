package edu.myschoolapp.aluno.service;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import edu.myschoolapp.aluno.model.Aluno;

public class RelatorioService {

    private final AlunoService alunoService;

    public RelatorioService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    public void gerarRelatorio() {
        
        Path downloads = Path.of(System.getProperty("user.home"), "Downloads");

        List<Aluno> ativos = alunoService.listarAlunosAtivos();
        List<Aluno> inativos = alunoService.listarAlunosInativos();


        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(downloads + "\\Relacao_alunos.txt"))) {
            
            writer.write("Relação de Alunos\n");
            writer.write("Data: " + LocalDate.now() + "\n\n");
            writer.write("==============================================================================================\n");
            
            writer.write("ALUNOS ATIVOS:\n");
            for (Aluno a : ativos) {
                writer.write(a.toString());
                writer.newLine();
            }
            
            writer.write("==============================================================================================\n");

            writer.write("ALUNOS INATIVOS:\n");
            for (Aluno a : inativos) {
                writer.write(a.toString());
                writer.newLine();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relação de alunos.");
        }

    }
}
