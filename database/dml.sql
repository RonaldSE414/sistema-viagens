INSERT INTO clientes (nome, email, telefone)
VALUES 
('João Silva', 'joao@email.com', '99999-1111'),
('Maria Souza', 'maria@email.com', '98888-2222');

INSERT INTO destino (cidade, pais)
VALUES 
('São Paulo', 'Brasil'),
('Paris', 'França');

INSERT INTO reserva (data_viagem, status, fk_id_cliente, fk_id_destino)
VALUES 
('2026-05-10', 'Confirmada', 1, 1),
('2026-06-15', 'Pendente', 2, 2);
