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
	