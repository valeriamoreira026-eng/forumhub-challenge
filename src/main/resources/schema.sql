-- =============================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS - FORUM HUB
-- =============================================

-- Criar o banco de dados (caso não exista)
CREATE DATABASE IF NOT EXISTS forum_hub_db;
USE forum_hub_db;

-- =============================================
-- TABELA: usuarios
-- =============================================
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- =============================================
-- TABELA: cursos
-- =============================================
CREATE TABLE IF NOT EXISTS cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

-- =============================================
-- TABELA: topicos
-- =============================================
CREATE TABLE IF NOT EXISTS topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    curso VARCHAR(100) NOT NULL,
    autor_id BIGINT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);

-- =============================================
-- INSERIR DADOS INICIAIS (OPCIONAL)
-- =============================================

-- Inserir um curso padrão
INSERT INTO cursos (nome, categoria)
SELECT 'Spring Boot', 'Programação'
WHERE NOT EXISTS (SELECT 1 FROM cursos WHERE nome = 'Spring Boot');

INSERT INTO cursos (nome, categoria)
SELECT 'Java', 'Programação'
WHERE NOT EXISTS (SELECT 1 FROM cursos WHERE nome = 'Java');

-- Inserir um usuário de exemplo (senha: 123456)
-- A senha já está criptografada com BCrypt
INSERT INTO usuarios (nome, email, senha)
SELECT 'Val', 'val@email.com', '$2a$10$3NeGAokcIJnGsrZ9oeoUKOAjYHbGfa1UzSiF07F7f/LnT7uaoh7VK'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'val@email.com');

-- =============================================
-- CONSULTAR DADOS INSERIDOS
-- =============================================
SELECT '--- USUÁRIOS ---' AS '';
SELECT id, nome, email FROM usuarios;

SELECT '--- CURSOS ---' AS '';
SELECT * FROM cursos;

SELECT '--- TABELAS CRIADAS ---' AS '';
SHOW TABLES;