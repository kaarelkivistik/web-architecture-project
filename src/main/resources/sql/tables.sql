DROP TABLE IF EXISTS device;
DROP TABLE IF EXISTS device_type;

/*DROP TABLE IF EXISTS service_request;
DROP TABLE IF EXISTS service_order;
DROP TABLE IF EXISTS service_note;
DROP TABLE IF EXISTS service_device;
DROP TABLE IF EXISTS service_device_status_type;
DROP TABLE IF EXISTS service_request_status_type;
DROP TABLE IF EXISTS so_status_type;
DROP TABLE IF EXISTS service_action;
DROP TABLE IF EXISTS service_part;
DROP TABLE IF EXISTS service_action_status_type;
DROP TABLE IF EXISTS service_type;
DROP TABLE IF EXISTS service_unit_type;
DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS invoice_status_type;
DROP TABLE IF EXISTS invoice_row;*/

DROP SEQUENCE IF EXISTS device_type_id;
CREATE SEQUENCE device_type_id;

CREATE TABLE device_type 
( device_type_id int NOT NULL DEFAULT nextval('device_type_id'),
  super_type_id int,
  level int NOT NULL,
  type_name varchar(200),
  CONSTRAINT device_type_pk PRIMARY KEY (device_type_id),
  CONSTRAINT device_super_type_fk FOREIGN KEY (super_type_id) REFERENCES device_type (device_type_id)
);

DROP SEQUENCE IF EXISTS device_id;
CREATE SEQUENCE device_id;

CREATE TABLE device 
( device_id int NOT NULL DEFAULT nextval('device_id'),
  device_type_id int,
  name varchar(100),
  reg_no varchar(100),
  description text,
  model varchar(100),
  manufacturer varchar(100),
  CONSTRAINT device_pk PRIMARY KEY (device_id),
  CONSTRAINT device_type_fk FOREIGN KEY (device_type_id) REFERENCES device_type (device_type_id)
);
