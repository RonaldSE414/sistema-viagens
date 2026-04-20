# ✈️ Sistema de Viagens

## 📌 Descrição
Sistema de gerenciamento de viagens desenvolvido em **Java (Swing)** com integração ao **PostgreSQL**.

O sistema permite realizar operações completas de **CRUD** (Criar, Ler, Atualizar e Deletar) de reservas, relacionando **clientes** e **destinos**, além de evitar duplicações no banco de dados.

---

## 💻 Tecnologias Utilizadas
- Java (Swing)
- PostgreSQL
- JDBC
- Maven

---

## ⚙️ Funcionalidades
- Cadastro de clientes e viagens  
- Listagem de reservas com JOIN (cliente + destino + reserva)  
- Atualização de reservas  
- Exclusão de reservas  
- Interface gráfica (Swing)  

---

## 🗂️ Estrutura do Projeto
```
SistemaViagens/
 ├── pom.xml
 ├── README.md
 ├── src/
 │    └── main/
 │         └── java/
 │              └── com/mycompany/sistemaviagens/
 │                   ├── Main.java
 │                   ├── Tela.java
 │                   ├── Conexao.java
 │                   └── ViagemDAO.java
 │
 ├── database/
 │    ├── ddl.sql
 │    ├── dml.sql
 │    ├── dql.sql
 │    └── diagrama(DER)
 │      
```

---

## 🧱 DDL - Criação das Tabelas
```sql
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
```

---

## 🧪 DML - Inserção / Atualização / Exclusão
```sql
-- INSERT(INSERIR DADOS)
-- CLIENTES
INSERT INTO clientes (nome, email, telefone)
VALUES 
('João Silva', 'joao@email.com', '99999-1111'),
('Maria Souza', 'maria@email.com', '98888-2222'),
('Carlos Lima', 'carlos@email.com', '97777-3333'),
('Ana Pereira', 'ana@email.com', '96666-4444');

-- DESTINOS
INSERT INTO destino (cidade, pais)
VALUES 
('São Paulo', 'Brasil'),
('Rio de Janeiro', 'Brasil'),
('Paris', 'França'),
('Lisboa', 'Portugal');

-- RESERVAS 
INSERT INTO reserva (data_viagem, status, fk_id_cliente, fk_id_destino)
VALUES 
('2026-05-10', 'Confirmada', 1, 1),
('2026-06-15', 'Pendente', 2, 3),
('2026-07-20', 'Cancelada', 3, 2),
('2026-08-05', 'Confirmada', 4, 4);

-- UPDATE(ATUALIZAR)
-- Atualizar telefone de um cliente
UPDATE clientes
SET telefone = '98888-0000'
WHERE id_cliente = 1;

-- Atualizar status de uma reserva
UPDATE reserva
SET status = 'Cancelada'
WHERE id_reserva = 2;

-- Atualizar cidade de destino
UPDATE destino
SET cidade = 'Rio de Janeiro'
WHERE id_destino = 1;

-- DELETE(APAGAR)
-- Deletar uma reserva
DELETE FROM reserva
WHERE id_reserva = 2;

-- Deletar um cliente
DELETE FROM clientes
WHERE id_cliente = 2;

-- Deletar um destino
DELETE FROM destino
WHERE id_destino = 2;
```

---

## 🔎 DQL - Consulta com JOIN
```sql
-- Listar todas as reservas completas
SELECT 
    r.id_reserva,
    c.nome AS cliente,
    d.cidade,
    d.pais,
    r.data_viagem,
    r.status
FROM reserva r
JOIN clientes c ON r.fk_id_cliente = c.id_cliente
JOIN destino d ON r.fk_id_destino = d.id_destino;

-- Ver clientes
SELECT * FROM clientes;

-- Ver destinos
SELECT * FROM destino;

-- Ver reservas
SELECT * FROM reserva;
```

---

## 📷 Prints da Aplicação

### 🏠 Menu Principal
<img width="600" alt="Captura de tela 2026-04-20 005133" src="https://github.com/user-attachments/assets/4e10097c-d937-4836-849b-55a09cd26c92" />


### 📝 Cadastro
<img width="600" alt="Captura de tela 2026-04-20 005312" src="https://github.com/user-attachments/assets/06263170-8029-448b-ac8d-87e19e288f52" />
<img width="600" alt="Captura de tela 2026-04-20 005337" src="https://github.com/user-attachments/assets/14353d29-8a1d-4991-8592-24ca43b4e50b" />


### 📋 Listagem com JOIN
<img width="600" alt="Captura de tela 2026-04-20 005409" src="https://github.com/user-attachments/assets/71c3f754-93cc-4cfa-b335-583189567755" />

---

## 🚀 Como Executar

### 1. Criar o banco de dados
```sql
CREATE DATABASE sistema_viagens;
```

### 2. Executar o DDL
Execute o script de criação das tabelas acima.

### 3. Configurar a conexão

No arquivo `Conexao.java`:
```java
String url = "jdbc:postgresql://localhost:5432/sistema_viagens";
String user = "postgres";
String password = "SUA_SENHA";
```

> ⚠️ Substitua `"SUA_SENHA"` pela senha do seu PostgreSQL local.

### 4. Rodar o projeto
- Abrir no NetBeans  
- Executar a classe `Main.java`

---

## 👨‍💻 Autor
Ronald Machado Guimarães de Sousa
