# 🚀 Feedback API

API para gerenciamento completo de **feedbacks**, com suporte a **triagem por nota** e **níveis de urgência**. Desenvolvida em **Java 21** com **MongoDB** para persistência de dados.

---

## 🛠️ Tecnologias e Infraestrutura

* **Linguagem:** Java 21 (Eclipse Temurin)
* **Gerenciador de Dependências:** Maven
* **Banco de Dados:** MongoDB
* **Containerização:** Docker (Multi-stage Build)

---

## 🐳 Dockerização

O projeto utiliza **Multi-stage Build** para gerar uma imagem final mais leve e segura.

### 📦 Build da imagem

```bash
docker build -t feedback-api .
```

### 🧱 Estrutura do Dockerfile

* **Builder:** Compila o projeto utilizando **Maven 3.9.9** e **JDK 21**.
* **Runner:** Executa a aplicação em **Alpine Linux** com **JRE 21**, expondo a porta **9085**.

> ℹ️ **Nota:** O arquivo `docker-compose.yml` será adicionado futuramente para integrar esta API ao MongoDB e aos demais microserviços da arquitetura.

---

## 🛰️ Endpoints da API

A API responde por padrão na porta **9085**.

---

### 1️⃣ Registrar Feedback

**POST** `/feedback`

* **Resposta:** `201 Created`

```bash
curl -X POST http://localhost:9085/feedback \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Excelente atendimento e rapidez!",
    "nota": 9,
    "urgencyTypeEnum": "MEDIA"
  }'
```

---

### 2️⃣ Listar Feedbacks (com filtros)

**GET** `/feedback`

#### Parâmetros opcionais

* `descricao` — Texto do feedback
* `tipoUrgencia` — Nível de urgência
* `limit` — Quantidade máxima de registros
* `offset` — Paginação

```bash
curl -X GET "http://localhost:9085/feedback?tipoUrgencia=ALTA&limit=5"
```

#### Exemplo de resposta (`200 OK`)

```json
[
  {
    "id": "652ff3a9b1c2d40012ab45de",
    "descricao": "Excelente atendimento",
    "nota": 8,
    "urgencyTypeEnum": "MEDIA",
    "dataEnvio": "2025-10-22T20:30:00Z"
  }
]
```
---

### 3️⃣ Buscar Feedback por ID

**GET** `/feedback/{id}`

```bash
curl -X GET http://localhost:9085/feedback/652ff3a9b1c2d40012ab45de
```

* **Resposta:** `200 OK`

---

### 4️⃣ Atualizar Feedback

**PUT** `/feedback/{id}`

* **Resposta:** `204 No Content`

```bash
curl -X PUT http://localhost:9085/feedback/652ff3a9b1c2d40012ab45de \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Texto atualizado",
    "nota": 10,
    "urgencyTypeEnum": "URGENTE"
  }'
```

---

### 5️⃣ Remover Feedback

**DELETE** `/feedback/{id}`

* **Resposta:** `204 No Content`

```bash
curl -X DELETE http://localhost:9085/feedback/652ff3a9b1c2d40012ab45de
```

---

## 📋 Regras de Negócio e Enums

### 🚨 Níveis de Urgência (`urgencyTypeEnum`)

* `BAIXA`
* `MEDIA`
* `ALTA`
* `URGENTE`

---

## ❌ Padrão de Resposta de Erro

Em casos de erro (4xx ou 5xx), a API retorna o seguinte payload:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Mensagem detalhada do erro",
  "path": "/feedback",
  "timestamp": "2025-10-11T10:30:00Z"
}
```

---

## 📘 Documentação OpenAPI (Swagger)

A API segue o padrão **OpenAPI 3.0**, permitindo fácil integração com ferramentas como **Swagger UI** e **Redoc**.

* Endpoints, contratos e modelos estão totalmente alinhados com esta documentação.
* Recomenda-se utilizar o Swagger como fonte de verdade para integrações externas.

