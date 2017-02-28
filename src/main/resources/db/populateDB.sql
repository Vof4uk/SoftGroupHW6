DELETE FROM public.service_stations_clients;
DELETE FROM public.mechanics;
DELETE FROM public.cars;
DELETE FROM public.service_stations;

ALTER SEQUENCE car_seq RESTART 1;
INSERT INTO cars(model, maker, engine_id, make_date, price) VALUES
('x5','bmw',5454,'2009-11-21', 20000),
('lanos','daewoo',4994,'2001-01-01', 5000),
('clk','mercedez',88733,'2014-02-14', 50000),
('pajero','mitsubishi',8773,'2007-04-15', 17000);


ALTER SEQUENCE s_station_seq RESTART 1;
INSERT INTO service_stations(address) VALUES
('pushkina, 10'),
('shevchenka, 112'),
('энгельса, 666'),
('5th avenue, 4432');

ALTER SEQUENCE mechanic_seq RESTART 1;
INSERT INTO mechanics(first_name, last_name, service_station_id) VALUES
('Дим Димыч','Вишня', 1),
('Степан', 'Січ', 1),
('Анна', 'Каренина', 2),
('Дональд', 'Трамп', 2),
('Бонифаций', 'Максимус', 2),
('Данило', 'Иванов', 3),
('Роберт', 'Дент', 3),
('Харви', 'Дент', 3),
('Алла', 'Пугачева', 4),
('Антонио', 'Фокаччо', 4),
('Грэг', 'просто Грэг', 4),
('Иван', 'Лежибока', NULL);


ALTER SEQUENCE service_car_seq RESTART 1;
INSERT INTO service_stations_clients(car_id, service_station_id) VALUES
(1,1),
(1,2),
(2,2),
(3,1),
(2,3);
