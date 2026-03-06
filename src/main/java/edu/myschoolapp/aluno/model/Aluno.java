package edu.myschoolapp.aluno.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import edu.myschoolapp.aluno.exceptions.RegraDeNegocioException;

/**
 * Entidade que representa um aluno no sistema.
 * Contém validações de regras de negócio básicas.
 */
public class Aluno {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private boolean ativo;
    private String telefone;

    public Aluno(Long id, String nome, String cpf, LocalDate dataNascimento, String email,
            boolean ativo, String telefone) {

        this.id = validarId(id);
        this.nome = validarCampoObrigatorio(nome, "Nome");
        this.cpf = validarCPF(cpf);
        this.dataNascimento = validarDataNascimento(dataNascimento);
        this.email = validarCampoObrigatorio(email, "E-mail");
        this.ativo = ativo;
        this.telefone = validarCampoObrigatorio(telefone, "Telefone");
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Aluno))
            return false;
        Aluno aluno = (Aluno) o;
        return id.equals(aluno.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {

        String cpfFormatado = formatarCpf(cpf);

        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("CPF: ").append(cpfFormatado).append("\n");
        sb.append("Data Nascimento: ").append(dataNascimento.format(DATE_FORMAT)).append("\n");
        sb.append("E-mail: ").append(email).append("\n");
        sb.append("Telefone: ").append(telefone).append("\n");
        sb.append("Ativo: ").append(ativo).append("\n");

        return sb.toString();
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    private String validarCampoObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new RegraDeNegocioException(campo + " é obrigatório(a)");
        }
        return valor;
    }

    private String formatarCpf(String cpf) {

        if (cpf == null || cpf.length() != 11) {
            throw new IllegalStateException("CPF inválido para formatação");
        }

        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    private LocalDate validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new RegraDeNegocioException("Data de nascimento é obrigatória");
        }

        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new RegraDeNegocioException("Data de nascimento inválida");
        }
        return dataNascimento;
    }

    private String validarCPF(String cpf) {
        cpf = validarCampoObrigatorio(cpf, "CPF");

        if (!cpf.matches("\\d{11}")) {
            throw new RegraDeNegocioException("CPF inválido");
        }

        return cpf;
    }

    private Long validarId(Long id) {
        if (id == null) {
            throw new RegraDeNegocioException("Id é obrigatório");
        }
        return id;
    }

}
