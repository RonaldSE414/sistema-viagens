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
