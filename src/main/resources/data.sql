INSERT INTO customers (id_customer, id, pin, first_name, last_name) VALUES (1, 5555, 5555, 'Bartosz', 'Kozak'),
(2, 1111, 2222, 'Marcin', 'Kowalski');

INSERT INTO doctors (id_doctor, first_name, last_name) VALUES (1, 'Dominik','Bik'),
(2,'Maciej','Słaby');

INSERT INTO appointments (id_appointment, date, time, id_customer, id_doctor, is_active)
VALUES (1, '2021-01-18', '15:35', 1, 1, true);