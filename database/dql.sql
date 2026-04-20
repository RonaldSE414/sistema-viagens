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
