CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14),
    data_nascimento DATE,
    email VARCHAR(100),
    ativo BOOLEAN,
    telefone VARCHAR(20)
);