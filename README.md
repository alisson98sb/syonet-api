# API — Estoque (Spring Boot + Maven + PostgreSQL + React)

API de estoque com autenticação por **sessão (HttpSession)**, seguindo uma arquitetura limpa (ports in/out + adapters).  
Funcionalidades: **login/registro**, **CRUD mínimo de itens**, **movimentações (IN/OUT)** com **histórico** e persistência em **PostgreSQL**.

---

## Tecnologias

- **Backend:** Java 21 (ou 17), Spring Boot 3.x, Maven
- **Persistência:** Spring Data JPA, PostgreSQL
- **Auth:** HttpSession (sem JWT)
- **Validação:** Jakarta Validation
- **Criptografia:** BCrypt (`spring-security-crypto`)
- **Ferramentas:** Docker (Postgres), DBeaver, Postman/Insomnia

---

## Estrutura de pastas

```
src/main/java/com/example/api/
├─ config/
├─ domain/
│  ├─ model/                
│  └─ exception/            
├─ application/
│  ├─ port/
│  │  ├─ in/                
│  │  └─ out/               
│  └─ service/              
├─ infrastructure/
│  └─ persistence/
│     └─ jpa/               
├─ api/
│  └─ rest/
│     ├─ controller/        
│     └─ dto/               
└─ ApiApplication.java
```

---

## Banco de Dados

### Docker Compose (Postgres)
```yaml
version: '3.9'
services:
  db:
    image: postgres:16
    container_name: estoque-postgres
    environment:
      POSTGRES_USER: estoque
      POSTGRES_PASSWORD: estoque
      POSTGRES_DB: estoque
    ports:
      - "5432:5432"
    volumes:
      - dbdata:/var/lib/postgresql/data
volumes:
  dbdata:
```
Subir:
```bash
  docker compose up -d
```

---

## Configuração da Aplicação

**`src/main/resources/application.yml`**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/estoque
    username: estoque
    password: estoque
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate.format_sql: true
      hibernate.jdbc.time_zone: UTC
    open-in-view: false
server:
  port: 8080
```

**Dependências chave (pom.xml)**  
- spring-boot-starter-web  
- spring-boot-starter-data-jpa  
- postgresql (runtime)  
- spring-boot-starter-validation  
- spring-security-crypto  

---

## Como executar (backend)

```bash
  # subir banco
docker compose up -d

  # buildar e rodar
mvn spring-boot:run

  # teste
curl http://localhost:8080/ping
# -> {"status":"ok"}
```

---

## Endpoints & Testes (curl/Postman)

> A API usa **sessão (cookie JSESSIONID)** após login.  
> Os endpoints `/auth/**` são públicos; `/items/**` e `/movements/**` estão protegidos (via interceptor).

### Auth
```bash
  # registrar
POST /auth/register
Body: {"name":"Admin","email":"admin@a.com","password":"123"}

  # login (guarde o cookie JSESSIONID do Set-Cookie)
POST /auth/login
Body: {"email":"admin@a.com","password":"123"}

  # me (com cookie)
GET /auth/me
Headers: Cookie: JSESSIONID=<valor>

  # logout (com cookie)
POST /auth/logout
Headers: Cookie: JSESSIONID=<valor>
```

### Users 
```bash
  # criar usuário
POST /users
{"name":"User 1","email":"user1@a.com","password":"123"}

  # listar
GET /users

  # buscar por id
GET /users/1
```

### Items (protegido)
```bash
  # criar item (com cookie de sessão)
POST /items
{"name":"Caneta","sku":"SKU-001"}

  # listar
GET /items
```

### Movements (protegido)
```bash
  # entrada (+10)
POST /movements
{"itemId":1,"type":"IN","amount":10,"note":"Compra"}

  # saída (-3)
POST /movements
{"itemId":1,"type":"OUT","amount":3,"note":"Venda"}

  # histórico do item
GET /movements?itemId=1
```

---

## Postman (sugestão de coleção)

1. **Auth/Register**
2. **Auth/Login**
3. **Auth/Me**
4. **Users/Create**
5. **Users/List**
6. **Items/Create**
7. **Items/List**
8. **Movements/IN**
9. **Movements/OUT**
10. **Movements/History**
11. **Auth/Logout**

---


