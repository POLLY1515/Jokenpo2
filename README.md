# 🎮 Jokenpo 2 — Full Stack Application

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen)
![React](https://img.shields.io/badge/React-19-blue)
![Vite](https://img.shields.io/badge/Vite-8-purple)
![License](https://img.shields.io/badge/license-MIT-blue)

## 📌 Sobre o projeto

O **Jokenpo 2** é uma aplicação web Full Stack baseada no clássico jogo **Pedra, Papel e Tesoura**, desenvolvida com **React no frontend** e **Spring Boot no backend**.

O projeto foi criado com o objetivo de aplicar conceitos modernos de desenvolvimento de software, incluindo:

- Desenvolvimento de interfaces web responsivas;
- Criação de APIs REST;
- Comunicação entre frontend e backend;
- Arquitetura em camadas;
- Organização de código;
- Tratamento de regras de negócio;
- Consumo de serviços utilizando HTTP/JSON.

A aplicação permite que o usuário informe seu nome, escolha a quantidade de rodadas e dispute partidas contra o computador, recebendo os resultados em tempo real.

---

# 🏗️ Arquitetura do Projeto

O projeto utiliza uma arquitetura Full Stack separada em duas aplicações:

```
Jokenpo2
│
├── jokenpo-frontend
│   └── React + Vite
│
└── jokenpo-backend
    └── Java + Spring Boot
```

Fluxo da aplicação:

```
Usuário
   |
   ↓
React Frontend
   |
   | HTTP / JSON (Axios)
   |
   ↓
Spring Boot API
   |
   ↓
Controller
   |
   ↓
Service
   |
   ↓
Regras do jogo
   |
   ↓
Resposta JSON
```

---

# 🚀 Tecnologias utilizadas

## Frontend

- React 19
- Vite
- JavaScript
- Axios
- CSS
- HTML5

Responsável por:

- Interface do usuário;
- Captura das escolhas;
- Envio das requisições;
- Apresentação dos resultados.

---

## Backend

- Java 21
- Spring Boot 4.0.5
- Maven
- Spring Web
- API REST

Responsável por:

- Processamento das regras do jogo;
- Gerenciamento das partidas;
- Validação das jogadas;
- Comunicação via endpoints REST.

---

# 🎯 Funcionalidades

## Inicialização de partida

O usuário informa:

- Nome do jogador;
- Quantidade de rodadas desejadas.

A aplicação cria uma nova partida.

---

## Sistema de jogadas

O jogador pode escolher:

- 🪨 Pedra
- 📄 Papel
- ✂️ Tesoura

O computador realiza uma escolha automática e o sistema calcula o resultado.

---

## Processamento de resultados

A aplicação identifica:

- Vitória do jogador;
- Vitória do computador;
- Empate.

---

# 🔌 API REST

## Base URL

```
http://localhost:8080/api/jokenpo
```

---

## Iniciar jogo

### Endpoint

```
POST /iniciar
```

### Parâmetros

| Parâmetro | Tipo |
|---|---|
| nomeJogador | String |
| numRodadas | Integer |

Exemplo:

```
POST
/api/jokenpo/iniciar?nomeJogador=Maria&numRodadas=5
```

---

## Realizar jogada

### Endpoint

```
POST /jogar
```

### Request Body

```json
{
  "escolhaJogador": "PEDRA"
}
```

---

# 💻 Como executar o projeto

## Pré-requisitos

Antes de iniciar, tenha instalado:

- Java 21+
- Node.js 18+
- Maven (opcional, pois o projeto possui Maven Wrapper)

---

# ▶️ Executando o Backend

Entre na pasta:

```bash
cd jokenpo-backend
```

Execute:

Linux/Mac:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

O backend estará disponível em:

```
http://localhost:8080
```

---

# ▶️ Executando o Frontend

Entre na pasta:

```bash
cd jokenpo-frontend
```

Instale as dependências:

```bash
npm install
```

Execute:

```bash
npm run dev
```

O frontend estará disponível em:

```
http://localhost:5173
```

---

# ⚙️ Configuração de ambiente

O frontend utiliza variável de ambiente para definir a URL da API.

Arquivo:

```
jokenpo-frontend/.env
```

Exemplo:

```env
VITE_API_URL=http://localhost:8080/api/jokenpo
```

Em produção, basta alterar para a URL pública do backend.

---

# 📂 Estrutura de pastas

```
Jokenpo2
│
├── jokenpo-backend
│   │
│   ├── src/main/java
│   │   │
│   │   ├── controllers
│   │   ├── services
│   │   ├── model
│   │   └── enums
│   │
│   └── pom.xml
│
│
└── jokenpo-frontend
    │
    ├── src
    ├── public
    ├── package.json
    └── vite.config.js
```

---

# 🧠 Conceitos aplicados

Durante o desenvolvimento foram aplicados conceitos importantes:

### Backend

- API REST;
- Controllers;
- Services;
- DTOs;
- Injeção de dependência;
- Regras de negócio.

### Frontend

- Componentização React;
- Hooks;
- Comunicação com API;
- Gerenciamento de estado;
- Build de produção utilizando Vite.

---

# 📸 Demonstração

*(Adicionar futuramente imagens ou GIF da aplicação funcionando)*

---

# 🌐 Deploy

Frontend:

```
GitHub Pages
```

Backend:

```
Spring Boot hospedado em ambiente cloud
```

Arquitetura final:

```
Usuário
   |
   ↓
GitHub Pages
   |
   ↓
React Application
   |
   ↓
Spring Boot API
```

---

# 👩‍💻 Desenvolvido por

**Poliana**

Projeto desenvolvido para estudo e demonstração de desenvolvimento Full Stack.

---

⭐ Se este projeto foi útil, considere deixar uma estrela no repositório.
