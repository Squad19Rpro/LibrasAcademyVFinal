-- Criação do banco de dados se não existir
CREATE DATABASE IF NOT EXISTS libras_academy_6eku;

-- Conexão ao banco de dados
\c libras_academy_6eku;

-- Criação da tabela "alunos" se não existir
CREATE TABLE IF NOT EXISTS alunos (
    id serial PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE,
    data_nascimento DATE,
    email VARCHAR(255) UNIQUE,
    nome VARCHAR(255),
    senha VARCHAR(255),
    genero VARCHAR(20),
    telefone VARCHAR(20)
);

-- Inserção de dados na tabela "alunos" se não existirem
INSERT INTO alunos (id, cpf, data_nascimento, email, nome, senha, genero, telefone)
VALUES 
    (1, '111.222.222-22', '1995-10-10', 'heitorpimentel@gmail.com', 'Heitor Pimentel', '$2a$10$FVBYxG1ogE6ym1BaArEngeNptl5eoTWww8hO3iOatk6VxDyzNtbCG', 'Masculino', '(84) 90000-0000')
    ON CONFLICT (id) DO NOTHING;

-- Criação da tabela "cargo" se não existir
CREATE TABLE IF NOT EXISTS cargo (
    id serial PRIMARY KEY,
    nome VARCHAR(255) UNIQUE
);

-- Inserção de dados na tabela "cargo" se não existirem
INSERT INTO cargo (id, nome)
VALUES 
    (1, 'Gerente')
    ON CONFLICT (id) DO NOTHING;

-- Criação da tabela "curso_aluno" se não existir
CREATE TABLE IF NOT EXISTS curso_aluno (
    id serial PRIMARY KEY,
    aluno_id INT,
    curso_id INT
);

-- Inserção de dados na tabela "curso_aluno" se não existirem
INSERT INTO curso_aluno (id, aluno_id, curso_id)
VALUES 
    (1, 1, 1)
    ON CONFLICT (id) DO NOTHING;

-- Criação da tabela "cursos" se não existir
CREATE TABLE IF NOT EXISTS cursos (
    id serial PRIMARY KEY,
    ano VARCHAR(4),
    duracao INT,
    descricao TEXT,
    nome VARCHAR(255),
    vagas INT,
    cargo_id INT
);

-- Inserção de dados na tabela "cursos" se não existirem
INSERT INTO cursos (id, ano, duracao, descricao, nome, vagas, cargo_id)
VALUES 
    (1, '2024', 20, 'Este é o curso de Java voltado para iniciantes... Teste', 'Java', 29, 1)
    ON CONFLICT (id) DO NOTHING;

-- Criação da tabela "fale_conosco" se não existir
CREATE TABLE IF NOT EXISTS fale_conosco (
    id serial PRIMARY KEY,
    assunto VARCHAR(255),
    email VARCHAR(255),
    mensagem TEXT,
    nome VARCHAR(255)
);

-- Inserção de dados na tabela "fale_conosco" se não existirem
INSERT INTO fale_conosco (id, assunto, email, mensagem, nome)
VALUES 
    (1, 'Teste 2024', 'heitorpimentel@hotmail.com', 'Olá', 'Heitor Pimentel')
    ON CONFLICT (id) DO NOTHING;

-- Criação da tabela "funcionario" se não existir
CREATE TABLE IF NOT EXISTS funcionario (
    id serial PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE,
    data_admissao DATE,
    email VARCHAR(255) UNIQUE,
    nome VARCHAR(255),
    senha VARCHAR(255),
    genero VARCHAR(20),
    telefone VARCHAR(20),
    data_nascimento DATE,
    data_demissao DATE,
    salario DECIMAL(10, 2),
    cargo_id INT
);

-- Inserção de dados na tabela "funcionario" se não existirem
INSERT INTO funcionario (id, cpf, data_admissao, email, nome, senha, genero, telefone, data_nascimento, data_demissao, salario, cargo_id)
VALUES 
    (1, '000.000.000-00', '2024-01-24', 'admin@academy.com', 'Administrador', '$2a$10$Yr4M2riWvR2PxrPbyrAnY..TwPSdlrK.6PecZL361PpK/Qtp.tPW6', 'Outro', '(00) 00000-0000', '2024-01-24', NULL, 5000.00, 1)
    ON CONFLICT (id) DO NOTHING;

-- Criação da tabela "professor" se não existir
CREATE TABLE IF NOT EXISTS professor (
    id serial PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE,
    data_admissao DATE,
    email VARCHAR(255) UNIQUE,
    nome VARCHAR(255),
    senha VARCHAR(255),
    genero VARCHAR(20),
    telefone VARCHAR(20),
    data_nascimento DATE,
    data_demissao DATE,
    salario DECIMAL(10, 2),
    cargo_id INT
);
