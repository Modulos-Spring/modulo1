# API REST com CRUD de Pessoas

Este projeto consiste em uma API REST desenvolvida com **Spring Boot**, implementando um **CRUD completo de Pessoa** com paginação, filtro de usuários ativos e integração com o **Graylog** para centralização de logs.  
Também inclui um ambiente totalmente conteinerizado via **Docker Compose** (aplicação, banco de dados e Graylog).

---

## 🚀 Funcionalidades

- **CRUD de Pessoa**
    - Criar, listar(por página/por ID), atualizar e deletar pessoas.

- **Consulta paginada**
    - A consulta que busca todas as pessoas retorna resultados paginados, sendo 10 registros por página.
    - Apenas pessoas com `ativo = true` são retornadas nas consultas.

- **Deleção de pessoas**
    - Ao deletar uma pessoa, o atributo `ativo` passa a ser `false`, não sendo mais retornado na consulta

- **Integração com Graylog**
    - Todos os logs da aplicação são enviados para o servidor Graylog configurado no `docker-compose`.

- **Ambiente em containers**
    - Aplicação Spring Boot
    - Banco de dados MongoDB
    - Graylog 


---

## 📦 Tecnologias Utilizadas

- Java 18
- Spring Boot
- MongoDB
- Docker & Docker Compose
- Graylog
- Maven

---

## 📡 Endpoints

### ➤ Criar pessoa
`POST /pessoa/v1`

### ➤ Listar pessoas (paginado, apenas ativos)
`GET /pessoa/v1`

### ➤ Buscar por ID
`GET /pessoa/v1/{id}`

### ➤ Atualizar pessoa
`PUT /pessoa/v1/{id}`

### ➤ Remover pessoa
`DELETE /pessoa/v1/{id}`

---

## 📝 Logs no Graylog

A aplicação envia logs estruturados para o Graylog via **GELF UDP**. Após subir o ambiente, acesse o Graylog em:

`http://localhost:9000`

Usuário padrão:
- **Login:** admin
- **Senha:** senha123

---

## 🔍 Análise de Código com SonarQube

Relatório do módulo disponível aqui:
👉 https://sonarcloud.io/organizations/modulos-spring/projects
