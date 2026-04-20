# ✈️ Sistema de Viagens

## 📌 Descrição
Sistema de gerenciamento de viagens desenvolvido em Java com interface gráfica (Swing) e banco de dados PostgreSQL.

O sistema permite cadastrar, listar, atualizar e excluir reservas, relacionando clientes e destinos, evitando duplicações no banco de dados.

---

## 💻 Tecnologias Utilizadas
- Java (Swing)
- PostgreSQL
- JDBC
- Maven

---

## ⚙️ Funcionalidades
- Cadastro de clientes e viagens
- Listagem com JOIN (cliente + destino + reserva)
- Atualização de reservas
- Exclusão de reservas
- Interface gráfica (bônus do trabalho)

---

## 🗂️ Estrutura do Projeto

SistemaViagens/
│
├── diagrama/
├── ddl/
├── dml/
├── dql/
├── src/
└── README.md

---

## 🧱 DDL - Criação das Tabelas

CREATE TABLE clientes (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    telefone VARCHAR(20)
);

CREATE TABLE destino (
    id_destino SERIAL PRIMARY KEY,
    cidade VARCHAR(100),
    pais VARCHAR(100),
    UNIQUE (cidade, pais)
);

CREATE TABLE reserva (
    id_reserva SERIAL PRIMARY KEY,
    data_viagem DATE,
    status VARCHAR(50),
    fk_id_cliente INT REFERENCES clientes(id_cliente),
    fk_id_destino INT REFERENCES destino(id_destino)
);

##🧪 DML - Inserção / Atualização / Exclusão

-- INSERT CLIENTE
INSERT INTO clientes (nome, email, telefone)
VALUES ('João Silva', 'joao@email.com', '99999-9999');

-- INSERT DESTINO
INSERT INTO destino (cidade, pais)
VALUES ('Paris', 'França');

-- INSERT RESERVA
INSERT INTO reserva (data_viagem, status, fk_id_cliente, fk_id_destino)
VALUES ('2026-12-10', 'Confirmado', 1, 1);

-- UPDATE
UPDATE reserva
SET status = 'Cancelado'
WHERE id_reserva = 1;

-- DELETE
DELETE FROM reserva
WHERE id_reserva = 1;

## 🔎 DQL - Consulta com JOIN

SELECT 
    r.id_reserva,
    c.nome,
    c.email,
    c.telefone,
    d.cidade,
    d.pais,
    r.data_viagem,
    r.status
FROM reserva r
JOIN clientes c ON r.fk_id_cliente = c.id_cliente
JOIN destino d ON r.fk_id_destino = d.id_destino;

##📷 Prints da Aplicação

🏠 Menu Principal

(coloque aqui o print)

📝 Cadastro

(coloque aqui o print)

📋 Listagem com JOIN

(coloque aqui o print)

##🚀 Como Executar
1. Criar banco no PostgreSQL

CREATE DATABASE sistema_viagens;

2. Executar o DDL

Execute o script de criação das tabelas acima.

3. Configurar conexão

No arquivo Conexao.java:
String url = "jdbc:postgresql://localhost:5432/sistema_viagens";
String user = "postgres";
String password = "SUA_SENHA";

4. Rodar o projeto
   
Abrir no NetBeans
Executar a classe Main.java

##👨‍💻 Autor

Ronald Machado Guimarães de Sousa



