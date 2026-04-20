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
