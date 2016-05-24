/* klassifikaatorid */
INSERT INTO service_action_status_type (service_action_status_type, type_name)
VALUES (1, 'pooleli');
INSERT INTO service_action_status_type (service_action_status_type, type_name)
VALUES (2, 'valmis');

INSERT INTO so_status_type (so_status_type, type_name) VALUES (1, 'Too vastu voetud');
INSERT INTO so_status_type (so_status_type, type_name) VALUES (2, 'Valmis');
INSERT INTO so_status_type (so_status_type, type_name) VALUES (3, 'Hinnastatud');
INSERT INTO so_status_type (so_status_type, type_name) VALUES (4, 'Arve tehtud');
INSERT INTO so_status_type (so_status_type, type_name) VALUES (5, 'Seade tagastatud');

INSERT INTO invoice_status_type (invoice_status_type, type_name) VALUES (1, 'Pooleli');
INSERT INTO invoice_status_type (invoice_status_type, type_name) VALUES (2, 'Kinnitatud');
INSERT INTO invoice_status_type (invoice_status_type, type_name) VALUES (3, 'Makstud');

INSERT INTO service_device_status_type (service_device_status_type, type_name)
VALUES (1, 'vastu voetud');
INSERT INTO service_device_status_type (service_device_status_type, type_name)
VALUES (2, 'too seadmega lopetatud');

INSERT INTO service_device_status_type (service_device_status_type, type_name)
VALUES (3, 'seade kliendile tagastatud');


INSERT INTO service_unit_type (service_unit_type, type_name) VALUES (1, 'tundi');
INSERT INTO service_unit_type (service_unit_type, type_name) VALUES (2, 'minutit');
INSERT INTO service_unit_type (service_unit_type, type_name) VALUES (3, 'too-operatsioon');

INSERT INTO service_request_status_type (service_request_status_type, type_name) VALUES (1, 'registreeritud');
INSERT INTO service_request_status_type (service_request_status_type, type_name) VALUES (2, 'tagasi lykatud');
INSERT INTO service_request_status_type (service_request_status_type, type_name) VALUES (3, 'tellimus tehtud');

INSERT INTO service_type (type_name, service_unit_type_fk, service_price)
VALUES ('Raske remonditoo', 1, 100);

INSERT INTO service_type (type_name, service_unit_type_fk, service_price)
VALUES ('Hooldus', 1, 10);

INSERT INTO service_type (type_name, service_unit_type_fk, service_price)
VALUES ('Laserkahuri aku vahetus', 3, 1000);

/* seadme tyybid */
INSERT INTO device_type (type_name, level, super_type) VALUES ('laserseadmed', 1, 0);
INSERT INTO device_type (type_name, level, super_type) VALUES ('raadiotehnika', 1, 0);
INSERT INTO device_type (type_name, level, super_type) VALUES ('arvutid', 1, 0);
INSERT INTO device_type (type_name, level, super_type) VALUES ('kodutehnika', 1, 0);

INSERT INTO device_type (type_name, level, super_type) VALUES ('hyperboloidid', 2, 1);
INSERT INTO device_type (type_name, level, super_type) VALUES ('laserkahurid', 2, 1);
INSERT INTO device_type (type_name, level, super_type) VALUES ('lasersihikud', 2, 1);

INSERT INTO device_type (type_name, level, super_type) VALUES ('raadiod', 2, 2);
INSERT INTO device_type (type_name, level, super_type) VALUES ('magnetoolad', 2, 2);
INSERT INTO device_type (type_name, level, super_type) VALUES ('ressiiverid', 2, 2);

INSERT INTO device_type (type_name, level, super_type) VALUES ('laptopid', 2, 3);
INSERT INTO device_type (type_name, level, super_type) VALUES ('desktopid', 2, 3);

INSERT INTO device_type (type_name, level, super_type) VALUES ('pesumasinad', 2, 4);
INSERT INTO device_type (type_name, level, super_type) VALUES ('kylmkapid', 2, 4);
INSERT INTO device_type (type_name, level, super_type) VALUES ('elektripliidid', 2, 4);

/* tuleb klient. tahab remonti tuua seadme nimega "Insener garini Hyperboloid", tyyp GAR1444 */
/* klienditeenindaja (created_by=1, viitab tabelisse SUBJEKT.employee) registreerib seadme ja votab vastu */
/* Eeldatud on et customer_fk=1 on tabelis SUBJEKT.customer olemas */
/* teise seadmena toob laserpystoli ka parandamiseks */

INSERT INTO service_request (customer_fk, created_by, created, service_desc_by_customer, service_desc_by_employee, service_request_status_type_fk)
VALUES (1, 1, NOW(), 'Laser:laserkiir on nork. Laserkahur:aku labi',
        'Lamp vajab vist vahetamist, votame sisse.Kahuri aku vahetame ara', 1);

/* Keegi toob pesumasina , device_type_fk=13 */
INSERT INTO service_request (customer_fk, created_by, created, service_desc_by_customer, service_desc_by_employee, service_request_status_type_fk)
VALUES (2, 1, NOW(), 'Ei kai ringi', 'Mootor vist labi, teeme korda, mis see meie kaes on siis! Teeme ara!', 1);

/* -------------HYPERBOLOIDI JA LASERKAHURI tegevused-----------------------------------------------------*/
/* tootaja nr. 2 vaatab masinad yle ja teeb hyperboloidi kohta teeninduse-tellimuse  */
/* Need seadmed pole veel siin remondis kainud - paneme seadmete tabelisse */
INSERT INTO device (type, name, reg_no, model, manufacturer)
VALUES (5, 'Insener Garini hyperboloid', 'G327347273', 'GAR1444', 'Garin Industries');

INSERT INTO device (type, name, reg_no, model, manufacturer)
VALUES (6, 'Laserkahur', 'LK327347273', 'KL-11', 'Laser Weapons Ltd.');

INSERT INTO service_order (so_status_type_fk, service_request_fk, created_by, created, note)
VALUES (1, 1, 2, NOW(), 'Hyperboloidi ja laserpystoli parandus');

/* muudame ka service_request-i seisundit */
UPDATE service_request
SET service_request_status_type_fk = 3
WHERE service_request = 1;
/* seome seadmed konkreetse tellimusega */

INSERT INTO service_device (device_fk, service_order_fk, service_device_status_type_fk, to_store)
VALUES (1, 1, 1, NOW());

INSERT INTO service_device (device_fk, service_order_fk, service_device_status_type_fk, to_store)
VALUES (2, 1, 1, NOW());

/* Hakatakse parandama. Tehakse marge et vahetatakse olemasolev lamp OL233 uue teise-tyybilise lambi OL433 vastu */
/* markmeid teeb tootaja nr. 2, author_type=1 (employee) */
INSERT INTO service_note (service_order_fk, employee_fk, service_device_fk, note, note_author_type, created)
VALUES (1, 2, 1, 'OL233 vahetatud OL433 vastu', 1, NOW());


INSERT INTO service_note (service_order_fk, employee_fk, service_device_fk, note, note_author_type, created)
VALUES (1, 2, 2, 'Kahuri aku vahetamine laks raskelt aga edukalt', 1, NOW());

UPDATE service_device
SET service_description = 'lambi vahetus, asendatud lambiga LHY77 '
WHERE service_device = 1;
UPDATE service_device
SET service_description = 'aku vahetus'
WHERE service_device = 2;
/* sisestame tegevused mille eest kliendilt raha kysime */

INSERT INTO service_action (service_order_fk, service_device_fk, service_type_fk, service_amount, price, price_updated, action_description, created, created_by, service_action_status_type_fk)
VALUES (1, 1, 1, 10, 100, NOW(), 'lambi vahetus', NOW(), 1, 2);
INSERT INTO service_action (service_order_fk, service_device_fk, service_type_fk, service_amount, price, price_updated, action_description, created, created_by, service_action_status_type_fk)
VALUES (1, 1, 2, 1, 50, NOW(), 'puhastamine', NOW(), 1, 2);

INSERT INTO service_part (service_order_fk, service_device_fk, part_name, serial_no, part_count, part_price, created, created_by)
VALUES (1, 1, 'Hyperboloidi lamp LHY77', 'A9999999', 1, 4000, NOW(), 1);

/* laserkahuri aku vahetamise operatsioon maksab 1000 EUR-i, service_type_fk=3 */
INSERT INTO service_action (service_order_fk, service_device_fk, service_type_fk, service_amount, price, price_updated, action_description, created, created_by, service_action_status_type_fk)
VALUES (1, 2, 3, 1, 1000, NOW(), 'aku vahetus', NOW(), 1, 2);

INSERT INTO service_part (service_order_fk, service_device_fk, part_name, serial_no, part_count, part_price, created, created_by)
VALUES (1, 2, 'laserkahuri aku AK74', 'A39304982', 1, 5000, NOW(), 1);

/* seadmed on parandatud. muudame staatuseid */
UPDATE service_device
SET service_device_status_type_fk = 2
WHERE service_device = 1;
UPDATE service_device
SET service_device_status_type_fk = 2
WHERE service_device = 2;
UPDATE service_order
SET so_status_type_fk = 2
WHERE service_order = 1;

/* arvutame kokku palju tellimuse kogumaksumus kliendi jaoks */
SELECT
  (SA.price * SA.service_amount) AS summa_kokku,
  SA.service_amount              AS kogus,
  SUT.type_name
FROM service_action SA INNER JOIN service_type ST ON SA.service_type_fk = ST.service_type
  INNER JOIN service_unit_type SUT ON ST.service_unit_type_fk = SUT.service_unit_type
WHERE SA.service_order_fk = 1;

SELECT sum(price * service_amount)
FROM service_action
WHERE service_order_fk = 1;

/* arvutame kokku osade maksumuse kliendi jaoks */
SELECT
  part_name,
  part_price,
  part_count
FROM service_part
WHERE service_order_fk = 1;
SELECT sum(part_price * part_count)
FROM service_part
WHERE service_order_fk = 1;

SELECT sum(price * service_amount) AS subtotal
FROM service_action
WHERE service_order_fk = 1
UNION SELECT sum(part_price * part_count) AS subotal
      FROM service_part
      WHERE service_order_fk = 1;

SELECT sum(subtotal)
FROM (SELECT sum(price * service_amount) AS subtotal
      FROM service_action
      WHERE service_order_fk = 1
      UNION SELECT sum(part_price * part_count) AS subotal
            FROM service_part
            WHERE service_order_fk = 1) AS subtotals;

/* kokku laks hyperboloidi ja laserkahuri parandamine (too + varuosad) maksma 11050.-  */
UPDATE service_order
SET price_total = 11050
WHERE service_order = 1;

/* kinnitame tellimuse hinna (staatus='Hinnastamine') */
/* enne vaatame yle (rakenduses, kasutajavormil) varuosade hinna ja hulga (service_part) */
/* ja toode hinna ja hulga (service_action), vajadusel UPDATE nendes tabelites */
/* kui andmed tabelites service_part ja service_action on muudetud siis tuleb tellimuse kogusumma */
/* ka uuesti arvutada ja uuednada tabelis service_order (vt. ylevalt) */

UPDATE service_order
SET so_status_type_fk = 3
WHERE service_order = 1;


/* nyyd genereerime arve. arved pa"ise jaks tulevad lahteandmed tabelist service_order */
/* arve ridade jaoks tulevad lahteandmed tabelitest service_part (kulunud osad) ja service_action (to"o") */
/* klienti (customer_fk) peaks saama valida, vaikimisi on kliendiks see klient kes seadme toi (saab katte service_request tabelist) */

INSERT INTO invoice (customer_fk, invoice_status_type_fk, service_order_fk, invoice_date, due_date, price_total, receiver_name, reference_number, receiver_accounts, description)
VALUES (1, 1, 1, NOW(), '11.11.2020', 600, 'OY Paranduskoda', '5678000', 'Swedbank 73737482374',
        '"Hyperboloidi ja laserpystoli parandus"');

INSERT INTO invoice_row (invoice_fk, service_part_fk, action_part_description, unit_price, price_total, unit_type, amount, invoice_row_type)
VALUES (1, 1, 'Hyperboloidi lamp LHY77', 4000, 4000, 'tk.', 1, 1);

INSERT INTO invoice_row (invoice_fk, service_part_fk, action_part_description, unit_price, price_total, unit_type, amount, invoice_row_type)
VALUES (1, 2, '"laserkahuri aku AK74"', 5000, 5000, 'tk.', 1, 1);


INSERT INTO invoice_row (invoice_fk, service_action_fk, action_part_description, unit_price, price_total, unit_type, amount, invoice_row_type)
VALUES (1, 1, 'lambi vahetus', 1000, 100, 'tundi.', 10, 2);

INSERT INTO invoice_row (invoice_fk, service_action_fk, action_part_description, unit_price, price_total, unit_type, amount, invoice_row_type)
VALUES (1, 2, 'puhastamine', 50, 50, 'tundi.', 1, 2);

INSERT INTO invoice_row (invoice_fk, service_action_fk, action_part_description, unit_price, price_total, unit_type, amount, invoice_row_type)
VALUES (1, 2, 'aku vahetus', 1000, 1000, 'too-operatsioon', 1, 2);

/* tellimuse staatuseks saab 'Arve tehtud' */
/* sellega on tegevused selle tellimusega loppenud */
UPDATE service_order
SET so_status_type_fk = 4
WHERE service_order = 1;

/* klient viib seadmed minema */
UPDATE service_device
SET from_store = NOW(), service_device_status_type_fk = 3, status_changed = NOW()
WHERE service_device = 1;
UPDATE service_device
SET from_store = NOW(), service_device_status_type_fk = 3, status_changed = NOW()
WHERE service_device = 2;

/* -------------KYLMKAPI tegevused-----------------------------------------------------*/
/* kylmkapi service_order + device + service_device + service_action + service_part */
INSERT INTO service_order (so_status_type_fk, service_request_fk, created_by, created, note)
VALUES (1, 2, 2, NOW(), 'Mootori vahetus');

/* muudame ka service_request-i seisundit */
UPDATE service_request
SET service_request_status_type_fk = 3
WHERE service_request = 2;

INSERT INTO device (type, name, reg_no, model, manufacturer)
VALUES (13, 'Electrolux', 'EL2399102', 'EWF106110W', 'Electrolux');

INSERT INTO service_device (device_fk, service_order_fk, service_device_status_type_fk, to_store)
VALUES (3, 2, 1, NOW());

INSERT INTO service_action (service_order_fk, service_device_fk, service_type_fk, service_amount, price, price_updated, action_description, created, created_by, service_action_status_type_fk)
VALUES (2, 3, 1, 1, 100, NOW(), 'Mootori vahetus', NOW(), 1, 2);

INSERT INTO service_part (service_order_fk, service_device_fk, part_name, serial_no, part_count, part_price, created, created_by)
VALUES (2, 3, 'mootor EL78-MK', 'MM56565283', 1, 500, NOW(), 1);

/* seadmed on parandatud. muudame staatuseid */
UPDATE service_device
SET service_device_status_type_fk = 2
WHERE service_device = 3;
UPDATE service_order
SET so_status_type_fk = 2
WHERE service_order = 2;

/* arvutame tellimuse hinna */


SELECT sum(subtotal)
FROM (SELECT sum(price * service_amount) AS subtotal
      FROM service_action
      WHERE service_order_fk = 2
      UNION SELECT sum(part_price * part_count) AS subotal
            FROM service_part
            WHERE service_order_fk = 2) AS subtotals;

/* kokku laks kylmkapi parandamine (too + varuosad) maksma 600.-  */
UPDATE service_order
SET price_total = 600
WHERE service_order = 2;

/* kinnitame tellimuse hinna (staatus='Hinnastamine') */
/* enne vaatame yle (rakenduses, kasutajavormil) varuosade hinna ja hulga (service_part) */
/* ja toode hinna ja hulga (service_action), vajadusel UPDATE nendes tabelites */
/* kui andmed tabelites service_part ja service_action on muudetud siis tuleb tellimuse kogusumma */
/* ka uuesti arvutada ja uuendada tabelis service_order (vt. ylevalt) */

UPDATE service_order
SET so_status_type_fk = 3
WHERE service_order = 2;

/* nyyd genereerime arve. arved pa"ise jaks tulevad lahteandmed tabelist service_order */
/* arve ridade jaoks tulevad lahteandmed tabelitest service_part (kulunud osad) ja service_action (to"o") */
/* klienti (customer_fk) peaks saama valida, vaikimisi on kliendiks see klient kes seadme toi (saab katte service_request tabelist) */

INSERT INTO invoice (customer_fk, invoice_status_type_fk, service_order_fk, invoice_date, due_date, price_total, receiver_name, reference_number, receiver_accounts, description)
VALUES (2, 1, 2, NOW(), '11.11.2020', 600, 'OY Paranduskoda', '5678000', 'Swedbank 73737482374', 'Mootori vahetus');
INSERT INTO invoice_row (invoice_fk, service_part_fk, action_part_description, unit_price, price_total, unit_type, amount, invoice_row_type)
VALUES (2, 3, 'mootor EL78-MK', 500, 500, 'tk.', 1, 1);
INSERT INTO invoice_row (invoice_fk, service_action_fk, action_part_description, unit_price, price_total, unit_type, amount, invoice_row_type)
VALUES (2, 4, 'Mootori vahetus', 100, 1000, 'tk.', 1, 2);

/* tellimuse staatuseks saab 'Arve tehtud' */
/* sellega on tegevused selle tellimusega loppenud */
UPDATE service_order
SET so_status_type_fk = 4
WHERE service_order = 2;

/* klient viib seadme minema */
UPDATE service_device
SET from_store = NOW(), service_device_status_type_fk = 3, status_changed = NOW()
WHERE service_device = 3;