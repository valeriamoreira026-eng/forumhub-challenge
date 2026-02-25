# 📚 ForumHub API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![JWT](https://img.shields.io/badge/JWT-Authentication-purple)

API REST desenvolvida durante o desafio **ForumHub** do programa **Oracle Next Education (ONE)** em parceria com a Alura.  
O projeto simula um fórum onde usuários autenticados podem criar, listar, atualizar e deletar tópicos, com regras de autorização para garantir que apenas o autor possa modificar suas próprias postagens.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security** (Autenticação e Autorização)
- **JWT** (JSON Web Tokens)
- **JPA / Hibernate**
- **MySQL**
- **Maven**
- **BCrypt** (Criptografia de senhas)

---

## 📋 Funcionalidades

- ✅ Cadastro e autenticação de usuários com JWT
- ✅ CRUD completo de tópicos
- ✅ Listagem de todos os tópicos
- ✅ Busca de tópico por ID
- ✅ Regras de autorização:
  - Apenas usuários autenticados podem criar tópicos
  - Apenas o autor pode atualizar ou deletar seu próprio tópico
- ✅ Validações com Bean Validation
- ✅ Banco de dados MySQL com script de criação automática

---

## 🔗 Endpoints da API

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| POST | `/login` | Autenticar usuário e gerar token | ❌ |
| POST | `/topicos` | Criar novo tópico | ✅ |
| GET | `/topicos` | Listar todos os tópicos | ✅ |
| GET | `/topicos/{id}` | Buscar tópico por ID | ✅ |
| PUT | `/topicos/{id}` | Atualizar tópico (apenas autor) | ✅ |
| DELETE | `/topicos/{id}` | Deletar tópico (apenas autor) | ✅ |

---

## 🛠️ Como executar o projeto

### Pré-requisitos

- Java 17
- MySQL 8.0
- Maven
- Postman ou Insomnia (para testar)

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/valeriamoreira026-eng/forumhub-challenge.git
Configure o banco de dados

Crie um banco MySQL chamado forum_hub_db

O arquivo schema.sql (em src/main/resources) criará as tabelas automaticamente

Configure as credenciais do banco

Copie o arquivo application-example.properties para application.properties

Preencha com seu usuário e senha do MySQL:

properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
Execute a aplicação

bash
./mvnw spring-boot:run
Ou execute pelo IntelliJ

Teste os endpoints

Use o Postman para fazer as requisições

Primeiro, faça login em /login para obter o token

Use o token no header Authorization: Bearer {token} para acessar os endpoints protegidos

📝 Exemplos de Requisições
Login
json
POST /login
{
  "email": "val@email.com",
  "senha": "123456"
}
Criar Tópico
json
POST /topicos
Authorization: Bearer {seu-token}
{
  "titulo": "Dúvida sobre Spring Boot",
  "mensagem": "Como funciona a injeção de dependências?",
  "autorId": 1,
  "curso": "Spring Boot"
}
Atualizar Tópico
json
PUT /topicos/1
Authorization: Bearer {seu-token}
{
  "titulo": "Título atualizado",
  "mensagem": "Nova mensagem",
  "curso": "Java"
}
🗄️ Estrutura do Banco de Dados
sql
-- Usuários
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Cursos
CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

-- Tópicos
CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    curso VARCHAR(100) NOT NULL,
    autor_id BIGINT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);
👩‍💻 Autora
Valéria Costa
