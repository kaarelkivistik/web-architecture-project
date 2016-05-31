CREATE OR REPLACE FUNCTION check_device_type_level() RETURNS TRIGGER AS $check_device_type_level$
  BEGIN
    IF (SELECT level FROM device_type WHERE device_type_id = NEW.device_type_id) = 1 THEN
      RAISE EXCEPTION 'Seadme tüübi tase ei tohi olla 1';
    END IF;
  END;
$check_device_type_level$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS check_device_type_level_trigger ON device;

CREATE TRIGGER check_device_type_level_trigger
  BEFORE INSERT OR UPDATE ON device
  FOR EACH ROW
  EXECUTE PROCEDURE check_device_type_level();