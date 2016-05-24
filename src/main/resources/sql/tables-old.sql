

DROP SEQUENCE IF EXISTS service_note_id;
CREATE SEQUENCE service_note_id;

CREATE TABLE service_note 
( service_note_id int NOT NULL DEFAULT nextval('service_note_id'),
  customer_fk int,
  employee_fk int,
  service_order_fk int,
  service_device_fk int,
  note_author_type int,
  created timestamp,
  note text,
  CONSTRAINT service_note_pk PRIMARY KEY (service_note)
);





DROP SEQUENCE IF EXISTS service_action_id;
CREATE SEQUENCE service_action_id;

CREATE TABLE service_action 
( service_action_id int NOT NULL DEFAULT nextval('service_action_id'),
  service_action_status_type_fk int,
  service_type_fk int,
  service_device_fk int,
  service_order_fk int,
  service_amount numeric,
  price numeric,
  price_updated timestamp,
  action_description text,
  created timestamp,
  created_by int,
  CONSTRAINT service_action_pk PRIMARY KEY (service_action)
);

DROP SEQUENCE IF EXISTS service_part_id;
CREATE SEQUENCE service_part_id;

CREATE TABLE service_part
( service_part_id int NOT NULL DEFAULT nextval('service_part_id'),
  service_order_fk int,
  service_device_fk int,
  part_name text,
  serial_no text,
  part_count int,
  part_price numeric,
  created timestamp,
  created_by int,
  CONSTRAINT service_part_pk PRIMARY KEY (service_part)
);


CREATE TABLE service_action_status_type 
( service_action_status_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT service_action_status_type_pk PRIMARY KEY (service_action_status_type)
);

DROP SEQUENCE IF EXISTS service_type_id;
CREATE SEQUENCE service_type_id;

CREATE TABLE service_type 
( service_type_id int NOT NULL DEFAULT nextval('service_type_id') ,
  service_unit_type_fk int,
  type_name varchar(200),
  service_price numeric,
  CONSTRAINT service_type_pk PRIMARY KEY (service_type)
);





CREATE TABLE service_unit_type 
( service_unit_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT service_unit_pk PRIMARY KEY (service_unit_type)
);


DROP SEQUENCE IF EXISTS invoice_id;
CREATE SEQUENCE invoice_id;

CREATE TABLE invoice 
( invoice_id int NOT NULL DEFAULT nextval('invoice_id') ,
  invoice_status_type_fk int,
  service_order_fk int,
  customer_fk int,
  invoice_date date,
  due_date date,
  price_total numeric,
  receiver_name text,
  reference_number text,
  receiver_accounts text,
  payment_date date,
  description text,
  CONSTRAINT invoice_pk PRIMARY KEY (invoice)
);

CREATE TABLE invoice_status_type 
( invoice_status_type_id int NOT NULL ,
  type_name varchar(200),
  CONSTRAINT invoice_status_type_pk PRIMARY KEY (invoice_status_type)
);


DROP SEQUENCE IF EXISTS invoice_row_id;
CREATE SEQUENCE invoice_row_id;

CREATE TABLE invoice_row 
( invoice_row_id int NOT NULL DEFAULT nextval('invoice_row_id') ,
  invoice_fk int,
  service_action_fk int,
  service_part_fk int,
  action_part_description text,
  amount numeric,
  price_total numeric,
  unit_type varchar(200),
  unit_price numeric,
  invoice_row_type numeric(1,0),
  CONSTRAINT invoice_row_pk PRIMARY KEY (invoice_row)
);


/* service_request tabeli indeksid ja piirangud */

CREATE INDEX service_request_idx1
 ON service_request
  ( service_request);

CREATE INDEX service_request_idx2
 ON service_request
  ( customer_fk);

CREATE INDEX service_request_idx3
 ON service_request
  ( customer_fk, service_desc_by_customer varchar_pattern_ops,service_desc_by_employee varchar_pattern_ops);

CREATE INDEX service_request_idx4
 ON service_request
  ( customer_fk, created);
  

/* service_note tabeli indeksid ja piirangud */

CREATE INDEX service_note_idx1
 ON service_note
  ( service_note);

CREATE INDEX service_note_idx2
 ON service_note
  ( service_order_fk);
  
/* service_order tabeli indeksid ja piirangud */

CREATE INDEX service_order_idx1
 ON service_order
  ( service_order);


CREATE INDEX service_order_idx2
 ON service_order
  ( service_request_fk);

CREATE INDEX service_order_idx3
 ON service_order
  ( created );
  
CREATE INDEX service_order_idx4
 ON service_order
  ( status_changed );
  

/* service_device tabeli indeksid ja piirangud */

CREATE INDEX service_device_idx1
 ON service_device
  ( service_device);

CREATE INDEX service_device_idx2
 ON service_device
  ( device_fk);
  
CREATE INDEX service_device_idx3
 ON service_device
  ( service_order_fk);
  
CREATE INDEX service_device_idx4
 ON service_device
  ( service_device_status_type_fk);
  
CREATE INDEX service_device_idx5
 ON service_device
  ( service_device_status_type_fk, status_changed);
  
  
/* device tabeli indeksid ja piirangud */

CREATE INDEX device_idx1
 ON device
  ( device);
  
CREATE INDEX device_idx2
 ON device
  ( upper(reg_no) varchar_pattern_ops);
  
CREATE INDEX device_idx3
 ON device
  ( upper(description) varchar_pattern_ops);
  
CREATE INDEX device_idx4
 ON device
  ( upper(name) varchar_pattern_ops);
  
CREATE INDEX device_idx5
 ON device
  ( upper(model) varchar_pattern_ops);
  
CREATE INDEX device_idx6
 ON device
  ( upper(manufacturer) varchar_pattern_ops);
  
  
/* device_type tabeli indeksid ja piirangud */

CREATE INDEX device_type_idx1
 ON device_type
  ( device_type);

CREATE INDEX device_type_idx2
 ON device_type
  ( super_type_fk);
  
CREATE INDEX device_type_idx3
 ON device_type
  ( upper(type_name) varchar_pattern_ops);
  
/* service_action tabeli indeksid ja piirangud */

CREATE INDEX service_action_idx1
 ON service_action
  ( service_action);
  
CREATE INDEX service_action_idx2
 ON service_action
  ( service_order_fk);

CREATE INDEX service_action_idx3
 ON service_action
  ( service_device_fk);
  

CREATE INDEX service_action_idx4
 ON service_action
  ( upper(action_description) varchar_pattern_ops);
  
CREATE INDEX service_action_idx5
 ON service_action
  ( created);
  
CREATE INDEX service_action_idx6
 ON service_action
  ( created_by);

CREATE INDEX service_action_idx7
 ON service_action
 ( service_action_status_type_fk);
  
/* service_part tabeli indeksid ja piirangud */

CREATE INDEX service_part_idx1
 ON service_part
  ( service_part); 

CREATE INDEX service_part_idx2
 ON service_part
  ( service_order_fk); 
  
CREATE INDEX service_part_idx3
 ON service_part
  ( created, created_by); 
  

CREATE INDEX service_part_idx4
 ON service_part
  ( upper(part_name) varchar_pattern_ops); 

CREATE INDEX service_part_idx5
 ON service_part
  ( upper(serial_no) varchar_pattern_ops); 

CREATE INDEX service_part_idx6
 ON service_part
  ( service_device_fk); 
  
  
/* invoice tabeli indeksid ja piirangud */

CREATE INDEX invoice_idx1
 ON invoice
  ( invoice);

CREATE INDEX invoice_idx2
 ON invoice
  ( invoice_status_type_fk);

CREATE INDEX invoice_idx3
 ON invoice
  ( invoice_status_type_fk);

CREATE INDEX invoice_idx4
 ON invoice
  ( service_order_fk);
  
CREATE INDEX invoice_idx5
 ON invoice
  ( invoice_date);

CREATE INDEX invoice_idx6
 ON invoice
  ( due_date);
  
CREATE INDEX invoice_idx7
 ON invoice
  ( customer_fk);
  
CREATE INDEX invoice_idx8
 ON invoice
  ( payment_date);  
  
CREATE INDEX invoice_idx9
 ON invoice
  ( upper(description) varchar_pattern_ops);
  
  
/* invoice_row tabeli indeksid ja piirangud */

CREATE INDEX invoice_row_idx1
 ON invoice_row
  ( invoice_row);

CREATE INDEX invoice_row_idx2
 ON invoice_row
  ( invoice_fk);
  

/* service_type tabeli indeksid ja piirangud */

CREATE INDEX service_type_idx1
 ON service_type
  ( service_type);  
  
CREATE INDEX service_type_idx2
 ON service_type
  ( upper(type_name) varchar_pattern_ops);