# 💰 Processador Encargos

> API de processamento de lançamentos financeiros em lote com Spring Batch e Kafka.

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=flat-square&logo=apache-kafka&logoColor=white)](https://kafka.apache.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white)](https://www.docker.com/)

---

## 📋 Sobre o Projeto

**Processador Encargos** é uma API desenvolvida com **Spring Boot** para processamento de lançamentos financeiros em lote. Utiliza **Spring Batch** para execução paralela e particionada, com integração via **Apache Kafka** para comunicação assíncrona entre serviços.

---

## 🚀 Funcionalidades

| Funcionalidade | Descrição |
|---|---|
| ✅ Processamento em Lote | Execução de jobs com Spring Batch |
| ✅ Cálculo de Encargos | Aplicação de regras de negócio sobre lançamentos |
| ✅ Integração com Kafka | Produção e consumo de eventos assíncronos |
| ✅ APIs REST | Controle e monitoramento do processamento |
| ✅ Persistência | Armazenamento de dados com JPA/Hibernate |
| ✅ Leitura de Arquivos | Processamento de arquivos CSV |

---

## 🛠️ Tecnologias

- **Java 17+**
- **Spring Boot**
- **Spring Batch**
- **Spring Data JPA**
- **Apache Kafka**
- **Lombok**
- **Maven**
- **Docker**

---

## 🏗️ Arquitetura

O projeto segue a **Arquitetura Hexagonal (Ports & Adapters)**, garantindo baixo acoplamento e alta coesão entre as camadas.

```
src/main/java/org/example/processador_encargos/
├── adapters/
│   ├── inbound/        # Controllers e Batch Jobs
│   └── outbound/       # Kafka, JPA e Repositórios
├── application/
│   ├── service/        # Implementações de serviço
│   └── usecases/       # Casos de uso
├── domain/             # Entidades e regras de negócio
├── config/             # Configurações (Kafka, Batch)
├── mapper/             # Conversores de objetos
└── ProcessadorEncargosApplication.java
```

---

## 🔄 Fluxo de Processamento

```
1. Upload/Leitura de arquivo CSV com lançamentos
        ↓
2. Spring Batch inicia o processamento
        ↓
3. Dados são particionados e processados em paralelo
        ↓
4. Regras de encargos são aplicadas
        ↓
5. Dados são persistidos no banco
        ↓
6. Eventos são publicados no Kafka
        ↓
7. Consumidores processam os eventos
```

---

## 🚦 Endpoints

### ▶️ Processamento

```http
POST /jobs/start
```

Inicia o processamento em lote dos lançamentos.

---

### 📊 Lançamentos

```http
GET /lancamentos
```

Retorna todos os lançamentos processados.

---

```http
GET /lancamentos/{id}
```

Retorna os detalhes de um lançamento específico.

---

## 🚀 Como Executar

### Pré-requisitos

- Java 17+
- Maven
- Docker (para Kafka e banco de dados)

### Passos

```bash
# Clone o repositório
git clone https://github.com/ValmirboJr/Processador_Encargos.git

# Acesse o diretório
cd Processador_Encargos

# Execute a aplicação
mvn spring-boot:run
```


