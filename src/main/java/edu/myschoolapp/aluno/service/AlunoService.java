package edu.myschoolapp.aluno.service;

import java.util.List;

import edu.myschoolapp.aluno.model.Aluno;
import edu.myschoolapp.aluno.repository.AlunoRepository;

public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void salvarAluno(Aluno aluno) {
        validarAluno(aluno);
        alunoRepository.salvarAluno(aluno);
    }

    public List<Aluno> listarAlunosByStatus(boolean ativo) {
        return alunoRepository.listarAlunosByStatus(ativo);
    }

    public void ativarAluno(Long id) {
        alunoRepository.ativarAluno(id);
    }

    public void desativarAluno(Long id) {
        alunoRepository.desativarAluno(id);
    }

    private void validarAluno(Aluno aluno) {
        if (aluno == null || aluno.getNome() == null) {
            throw new IllegalArgumentException("Aluno inválido");
        }
    }
}
