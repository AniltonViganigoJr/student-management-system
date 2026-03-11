# My School App

Sistema simples de cadastro de alunos desenvolvido em **Java** com persistência em **PostgreSQL** utilizando **Docker Compose**.

O projeto foi criado com o objetivo de praticar conceitos de:

* Programação Orientada a Objetos (POO)
* Arquitetura em camadas
* Persistência com JDBC
* Boas práticas de organização de código
* Versionamento com Git

---

# Tecnologias utilizadas

* Java 17+
* Maven
* PostgreSQL
* Docker
* Docker Compose
* JDBC

---

# Estrutura do projeto

```
src
└── main
    ├── java
    │   └── edu.myschoolapp
    │       ├── Main.java
    │       │
    │       └── aluno
    │           ├── model
    │           │   └── Aluno.java
    │           │
    │           ├── repository
    │           │   └── AlunoRepository.java
    │           │
    │           ├── service
    │           │   ├── AlunoService.java
    │           │   └── RelatorioService.java
    │           │
    │           └── ui
    │               └── Menu.java
    │
    └── resources
        └── db.properties
```

---

# Funcionalidades

* Cadastrar aluno
* Listar alunos ativos
* Listar alunos inativos
* Ativar aluno
* Desativar aluno (soft delete)
* Gerar relatório de alunos

---

# Banco de dados

A aplicação utiliza **PostgreSQL** executando via **Docker Compose**.

## Estrutura da tabela

```sql
CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14),
    data_nascimento DATE,
    email VARCHAR(100),
    ativo BOOLEAN,
    telefone VARCHAR(20)
);
```

---

# Subindo o banco com Docker

Execute na raiz do projeto:

```bash
docker-compose up -d
```

Verificar containers em execução:

```bash
docker ps
```

Acessar o banco PostgreSQL dentro do container:

```bash
docker exec -it postgres_db psql -U postgres
```

---

# Configuração do banco

Arquivo de configuração:

```
src/main/resources/db.properties
```

Exemplo de configuração:

```
db.url=jdbc:postgresql://localhost:5432/mydb
db.username=postgres
db.password=postgres
```

⚠️ Esse arquivo não deve ser versionado no Git.

---

# Executando o projeto

Compilar o projeto:

```bash
mvn clean install
```

Executar a aplicação:

```bash
mvn exec:java -Dexec.mainClass="edu.myschoolapp.Main"
```

---

# Exemplo do menu da aplicação

```
============ MENU ============
1 - Cadastrar aluno
2 - Listar alunos ativos
3 - Listar alunos inativos
4 - Ativar aluno
5 - Desativar aluno
6 - Gerar relação de alunos
0 - Sair
```

---

# Autor

Projeto desenvolvido por **Anilton Joaquim Viganigo Júnior** para estudo de desenvolvimento Java.

## Próximos passos

- Implementar API REST com Spring Boot
- Adicionar validação de CPF
- Implementar testes unitários
- Criar container Docker da aplicação