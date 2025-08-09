# Authentication – Segurança em APIs REST

Este projeto é um serviço de **autenticação e autorização** desenvolvido com **Spring Boot**, projetado para proteger APIs REST usando **JWT (JSON Web Token)**.
Ele implementa autenticação baseada em tokens, controle de acesso por **roles**, e boas práticas de segurança para garantir a integridade e confidencialidade dos dados.

---

## 🛠 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Security**
* **JWT (JSON Web Token)**
* **Maven**
* **JPA / Hibernate**
* **Base de dados relacional** (MySQL)

---

## 🔒 Funcionalidades de Segurança

* **Autenticação com JWT**

  * Login retorna um token assinado com chave secreta definida no `.env`.
  * Tokens têm **tempo de expiração** para reduzir risco de uso indevido.
* **Autorização baseada em Roles**

  * Perfis de usuário definidos por `enum Role` (`USER`, `ADMIN`).
  * Proteção de endpoints via anotações e configuração de rotas seguras.
* **Security Filter personalizado**

  * Intercepta requisições, valida tokens e insere usuário autenticado no contexto de segurança.
* **Armazenamento seguro de credenciais**

  * Senhas criptografadas com **BCrypt** antes de serem persistidas.
* **Tratamento de exceções**

  * Respostas padronizadas para acessos não autorizados ou recursos inexistentes.
* **Prevenção de vazamento de dados sensíveis**

  * `.gitignore` configurado para não expor `.env` e credenciais.

---

## 🚀 Como Executar



**Clonar o repositório**

```bash
git clone "https://github.com/ernestoadriano/authentication"
cd authentication
```

**Configurar variáveis de ambiente** (`.env`)

```env
DB_USER=root
DB_PASSWORD=password
DB_URL=jdbc:mysql://localhost:3306/db
JWT_SECRET=secret
```

**Instalar dependências e executar**


**Testar a API** (ex.: via Postman)
User inicial
username: admin

password: admin123

* **POST** `api/v1//auth/login` → retorna JWT
*  **POST** `/api/v1/auth/register` somente depois do login e é com tokens com roles ADMIN

---

## 🔄 Fluxo de Autenticação

1. Usuário envia credenciais para `/api/v1/auth/login`.
2. O sistema autentica e gera um **JWT** com dados do usuário.
3. O token é enviado no **Authorization Header** em chamadas subsequentes.
4. O **Security Filter** valida o token e libera ou bloqueia o acesso.

---

## 🛡 Boas Práticas de Segurança Aplicadas

* **JWT seguro** com assinatura HMAC e expiração configurável.
* **BCrypt** para proteção de senhas contra ataques de força bruta.
* **Filtro de segurança** para validação de cada requisição.
* **Controle de acesso** por perfil de usuário.
* **Separação de configuração sensível** em variáveis de ambiente.
* **Proteção contra ataques comuns** (CSRF desativado para APIs stateless, validação de entrada, cabeçalhos seguros).
