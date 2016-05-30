





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