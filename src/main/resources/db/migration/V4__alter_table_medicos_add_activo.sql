USE med_database;

ALTER TABLE medicos ADD activo TINYINT;
UPDATE medicos SET activo = 1;