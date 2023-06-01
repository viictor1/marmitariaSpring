INSERT INTO roles (name) values ('User');
INSERT INTO roles (name) values ('Admin');

INSERT INTO users (first_name, last_name, cpf, email, password, role_id) values ('Teste', 'De User', '111.111.111-11', 'adm@test.com', '$2a$12$FodxaaXhuLZNA2jnRdXT5ODrS0bvQmwzHCouS4y4NwDduuTrRI5AS', 2);
INSERT INTO users (first_name, last_name, cpf, email, password, role_id) values ('Teste', 'De User2', '111.111.111-12', 'user@test.com', '$2a$12$FodxaaXhuLZNA2jnRdXT5ODrS0bvQmwzHCouS4y4NwDduuTrRI5AS', 1);
