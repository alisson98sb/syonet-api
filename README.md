# Estoque API
### Java Spring Boot
Esta é uma **API REST** construída com **Java Spring Boot** e **PostgreSQL**, projetada para
gerenciar usuários, itens de estoque e movimentações (entradas e saídas).
Seu propósito é fornecer endpoints seguros e padronizados para controle de estoque, incluindo
histórico de movimentações e autenticação de usuários.
A API foi desenvolvida como parte do projeto **Gestão de Estoque**, integrando-se ao frontend
React hospedado via Nginx/Docker.
---
##  Instalação
Clone o repositório:
git clone https://github.com/seuusuario/estoque-api.git
Instale as dependências usando **Maven**:
mvn clean install
Ou execute diretamente com Docker Compose:
docker compose up -d --build
---
##  Usabilidade
Inicie a aplicação localmente:
mvn spring-boot:run
A API estará acessível em:
http://localhost:8081
Swagger (documentação interativa):
http://localhost:8081/swagger-ui.html
---
##  Endpoints da API
###  Autenticação
- POST `/auth/register` — Cadastra um novo usuário
- POST `/auth/login` — Realiza login e cria sessão
###  Usuários
- GET `/users` — Lista todos os usuários cadastrados
###  Itens de Estoque
- GET `/items` — Retorna todos os itens cadastrados
- POST `/items` — Cria um novo item no estoque
###  Movimentações
- GET `/movements?itemId={id}` — Retorna o histórico de movimentações
- POST `/movements/in` — Registra uma entrada
- POST `/movements/out` — Registra uma saída
---
##  Banco de Dados
O projeto utiliza **PostgreSQL 16**, inicializado automaticamente via Docker Compose.
A criação das tabelas é realizada pela própria API através do script `schema.sql`.
---
##  Configuração
Arquivo `application.yml`:
server:
port: 8081
spring:
datasource:
url: jdbc:postgresql://db:5432/estoque
username: postgres
password: postgres
jpa:
hibernate:
ddl-auto: none
sql:
init:
mode: always
---
##  Docker Compose
Inclui:
- API (Spring Boot)
- Banco PostgreSQL
- Adminer
- Frontend React (Nginx)
  Acesse:
- API: http://localhost:8081
- Adminer: http://localhost:8082
- Frontend: http://localhost:3000