/* otsime klientide poordumise registreerimisi (service_request), */
/* nendele vastavaid remonditellimusi (service_order) ja nendele tellimustele vastavaid */
/* arveid */

SELECT SR.service_request, SO.service_order, P.first_name,P.last_name FROM service_request SR
LEFT JOIN customer C ON SR.customer_fk = C.customer INNER JOIN person P ON C.subject_fk = P.person
LEFT JOIN service_order SO ON SR.service_request = SO.service_request_fk
WHERE C.subject_type_fk = 1 ;


/* otsime mida on tehtud parandusse toodud seadmega mille registreerimis number on "G327347273" */
/* konverteerime SELECT-is timestamp-i kuupaevaks */
SELECT D.device, SD.service_description,SO.service_order AS tellimuse_nr,to_char(SD.from_store,'DD.MM.YYYY') AS katte_antud FROM device D 
LEFT JOIN service_device SD ON D.device= SD.device_fk 
LEFT JOIN service_order SO ON SD.service_order_fk = SO.service_order
WHERE D.reg_no LIKE 'G327347273';

/* seadme "G327347273" arved */

SELECT D.reg_no AS seadme_reg_nr, I.invoice AS arve_nr, I.price_total AS summa FROM device D 
LEFT JOIN service_device SD ON D.device= SD.device_fk 
LEFT JOIN service_order SO ON SD.service_order_fk = SO.service_order
LEFT JOIN invoice I ON SO.service_order = I.service_order_fk
WHERE D.reg_no LIKE 'G327347273';

/* tellimusega nr. 2 (kylmkapi parandus) seotud varosad */

SELECT SO.service_order AS tellimuse_id, SP.part_name, SP.part_count, (SP.part_count* SP.part_price) AS kulu FROM service_order SO
LEFT JOIN service_part SP ON SO.service_order = SP.service_order_fk 
WHERE SO.service_order = 2;


/* koik tellimused mille registreerijaks on tootaja perekonnanimega "maasikas" */
/* PostgreSQL-i paringud on case-sensitive, et SELECT oleks tostutundetu kasutame UPPER-funktsiooni */

SELECT SO.service_order AS tellimuse_id, (P.first_name || ' ' || P.last_name) AS tellimuse_sisestaja FROM service_order SO
LEFT JOIN employee E ON SO.created_by = E.employee
INNER JOIN person P ON E.person_fk = P.person
WHERE UPPER(P.last_name) LIKE UPPER('Maasikas');

/* klient kasutajanimega 'kaarel' logib systeemi sisse et naha oma tellimusi */
SELECT C.customer,UA.user_account, P.first_name, P.last_name FROM customer C INNER JOIN user_account UA ON C.customer = UA.subject_fk 
INNER JOIN person P ON C.subject_fk = P.person
WHERE C.subject_type_fk = 1 AND UA.subject_type_fk = 4 AND UA.username='kaarel'  AND UA.passw='kmmm89';

/* tootaja kasutajanimega 'marten' logib systeemi sisse  */
SELECT E.employee,UA.user_account, P.first_name, P.last_name FROM employee E INNER JOIN user_account UA ON E.employee = UA.subject_fk 
INNER JOIN person P ON E.person_fk = P.person
WHERE UA.subject_type_fk = 3  AND UA.username='marten'  AND UA.passw='37b4931088193a73b6561bae19bf06d9';