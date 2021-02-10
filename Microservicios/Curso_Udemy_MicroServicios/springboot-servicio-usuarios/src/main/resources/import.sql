INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Alvaro', '$2a$10$HzAzVt4KEY8I1.2i9z9Bxe4BVcKdEH5WBKDqIN0upmYlxWJ3JIC5S', 1, 'Alvaro', 'Martin', 'alvaro@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Administrador', '$2a$10$6iJ.lmsiqkGJjU24iHLCheZzWYikzSiSy1pKRIL0AWPh64MlTOWZi', 1, 'Paco', 'Fernandez', 'paco@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROL_USER');
INSERT INTO roles (nombre) VALUES ('ROL_ADMIN');

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 1);