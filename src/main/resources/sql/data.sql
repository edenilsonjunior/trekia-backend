INSERT INTO trip (user_id, title, description, start_date, end_date, created_at)
VALUES (1, 'Viagem para Paris', 'Conhecendo os principais pontos turísticos', DATE '2025-09-01', DATE '2025-09-10', CURRENT_TIMESTAMP);

INSERT INTO trip (user_id, title, description, start_date, end_date, created_at)
VALUES (1, 'Férias no Rio de Janeiro', 'Aproveitando as praias cariocas', DATE '2025-12-20', DATE '2026-01-05', CURRENT_TIMESTAMP);

INSERT INTO trip (user_id, title, description, start_date, end_date, created_at)
VALUES (1, 'Workation em Lisboa', 'Trabalho remoto em Portugal', DATE '2026-03-02', DATE '2026-03-30', CURRENT_TIMESTAMP);


-- Paris
INSERT INTO schedule (
    trip_id, planned_at, title, description, latitude, longitude, current_local_balance, currency_code, min_temperature, max_temperature, precipitation_chance
)
VALUES (
           (SELECT id FROM trip WHERE title LIKE 'Viagem para Paris'),
           CURRENT_TIMESTAMP, 'Torre Eiffel', 'Visita à Torre Eiffel', 48.858370, 2.294481, 1200.00, 'EUR', 12.5, 22.0, 10
       );

INSERT INTO schedule (
    trip_id, planned_at, title, description, latitude, longitude, current_local_balance, currency_code, min_temperature, max_temperature, precipitation_chance
)
VALUES (
           (SELECT id FROM trip WHERE title LIKE 'Viagem para Paris'),
           CURRENT_TIMESTAMP, 'Museu do Louvre', 'Ver Mona Lisa', 48.860611, 2.337644, 1150.50, 'EUR', 14.0, 20.0, 5
       );

-- Rio de Janeiro
INSERT INTO schedule (
    trip_id, planned_at, title, description, latitude, longitude, current_local_balance, currency_code, min_temperature, max_temperature, precipitation_chance
)
VALUES (
           (SELECT id FROM trip WHERE title LIKE 'Férias no Rio de Janeiro'),
           CURRENT_TIMESTAMP, 'Cristo Redentor', 'Subida ao Cristo', -22.951916, -43.210487, 2500.00, 'BRL', 24.0, 32.5, 30
       );

INSERT INTO schedule (
    trip_id, planned_at, title, description, latitude, longitude, current_local_balance, currency_code, min_temperature, max_temperature, precipitation_chance
)
VALUES (
           (SELECT id FROM trip WHERE title LIKE 'Férias no Rio de Janeiro'),
           CURRENT_TIMESTAMP, 'Praia de Copacabana', 'Dia de sol na praia', -22.971964, -43.182545, 2350.00, 'BRL', 25.5, 33.0, 20
       );

-- Lisboa
INSERT INTO schedule (
    trip_id, planned_at, title, description, latitude, longitude, current_local_balance, currency_code, min_temperature, max_temperature, precipitation_chance
)
VALUES (
           (SELECT id FROM trip WHERE title LIKE 'Workation em Lisboa'),
           CURRENT_TIMESTAMP, 'Mosteiro dos Jerónimos', 'Visitar patrimônio histórico', 38.698021, -9.206483, 900.00, 'EUR', 15.0, 25.0, 12
       );

INSERT INTO schedule (
    trip_id, planned_at, title, description, latitude, longitude, current_local_balance, currency_code, min_temperature, max_temperature, precipitation_chance
)
VALUES (
           (SELECT id FROM trip WHERE title LIKE 'Workation em Lisboa'),
           CURRENT_TIMESTAMP, 'Trabalho no coworking', 'Dia de trabalho remoto', 38.736946, -9.142685, 850.00, 'EUR', 16.0, 24.0, 8
       );

-- Paris
INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Viagem para Paris'), 'Passaporte', 1);

INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Viagem para Paris'), 'Seguro viagem', 0);

INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Viagem para Paris'), 'Roteiro impresso', 1);

-- Rio de Janeiro
INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Férias no Rio de Janeiro'), 'Protetor solar', 1);

INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Férias no Rio de Janeiro'), 'Reservar hotel', 0);

-- Lisboa
INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Workation em Lisboa'), 'Notebook', 1);

INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Workation em Lisboa'), 'Adaptador de tomada', 0);

INSERT INTO check_item (trip_id, description, is_checked)
VALUES ((SELECT id FROM trip WHERE title LIKE 'Workation em Lisboa'), 'Testar internet móvel', 1);
