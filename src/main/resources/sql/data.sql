INSERT INTO employee (name) VALUES ('kaarel');

INSERT INTO customer (name) VALUES ('mart');
INSERT INTO customer (name) VALUES ('oliver');
INSERT INTO customer (name) VALUES ('rico');
INSERT INTO customer (name) VALUES ('siim');
INSERT INTO customer (name) VALUES ('mäe');

/* --- */

INSERT INTO device_type (device_type_id, type_name, level) VALUES (1, 'laserseadmed', 1);
INSERT INTO device_type (device_type_id, type_name, level) VALUES (2, 'raadiotehnika', 1);
INSERT INTO device_type (device_type_id, type_name, level) VALUES (3, 'arvutid', 1);
INSERT INTO device_type (device_type_id, type_name, level) VALUES (4, 'kodutehnika', 1);

INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (5, 'hyperboloidid', 2, 1);
INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (6, 'laserkahurid', 2, 1);
INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (7, 'lasersihikud', 2, 1);

INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (8, 'raadiod', 2, 2);
INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (9, 'magnetoolad', 2, 2);
INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (10, 'ressiiverid', 2, 2);

INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (11, 'laptopid', 2, 3);
INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (12, 'desktopid', 2, 3);

INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (13, 'pesumasinad', 2, 4);
INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (14, 'kylmkapid', 2, 4);
INSERT INTO device_type (device_type_id, type_name, level, super_type_id) VALUES (15, 'elektripliidid', 2, 4);

/* --- */

INSERT INTO device (device_type_id, name, reg_no, model, manufacturer) VALUES (5, 'Insener Garini hyperboloid', 'G327347273', 'GAR1444', 'Garin Industries');
INSERT INTO device (device_type_id, name, reg_no, model, manufacturer) VALUES (6, 'Laserkahur', 'LK327347273', 'KL-11', 'Laser Weapons Ltd.');

/* --- */

INSERT INTO service_request_status_type (service_request_status_type_id, type_name) VALUES (1, 'Registreeritud');
INSERT INTO service_request_status_type (service_request_status_type_id, type_name) VALUES (2, 'Tagasi lükatud');
INSERT INTO service_request_status_type (service_request_status_type_id, type_name) VALUES (3, 'Tellimus tehtud');

/* --- */

INSERT INTO so_status_type (so_status_type_id, type_name) VALUES (1, 'Töö vastu võetud');
INSERT INTO so_status_type (so_status_type_id, type_name) VALUES (2, 'Valmis');
INSERT INTO so_status_type (so_status_type_id, type_name) VALUES (3, 'Hinnastatud');
INSERT INTO so_status_type (so_status_type_id, type_name) VALUES (4, 'Arve tehtud');
INSERT INTO so_status_type (so_status_type_id, type_name) VALUES (5, 'Seade tagastatud');

/* --- */

INSERT INTO service_device_status_type (service_device_status_type_id, type_name) VALUES (1, 'Vastu võetud');
INSERT INTO service_device_status_type (service_device_status_type_id, type_name) VALUES (2, 'Töö seadmega lõpetatud');
INSERT INTO service_device_status_type (service_device_status_type_id, type_name) VALUES (3, 'Seade kliendile tagastatud');

/* --- */

INSERT INTO service_action_status_type (service_action_status_type_id, type_name) VALUES (1, 'Pooleli');
INSERT INTO service_action_status_type (service_action_status_type_id, type_name) VALUES (2, 'Valmis');

/* --- */

INSERT INTO service_unit_type (service_unit_type_id, type_name) VALUES (1, 'tundi');
INSERT INTO service_unit_type (service_unit_type_id, type_name) VALUES (2, 'minutit');
INSERT INTO service_unit_type (service_unit_type_id, type_name) VALUES (3, 'töö-operatsioon');

/* --- */

INSERT INTO service_type (service_type_id, type_name, service_unit_type_id, service_price) VALUES (1, 'Raske remonditoo', 1, 100);
INSERT INTO service_type (service_type_id, type_name, service_unit_type_id, service_price) VALUES (2, 'Hooldus', 1, 10);
INSERT INTO service_type (service_type_id, type_name, service_unit_type_id, service_price) VALUES (3, 'Laserkahuri aku vahetus', 3, 1000);