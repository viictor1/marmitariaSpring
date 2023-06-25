INSERT INTO roles (name) values ('User');
INSERT INTO roles (name) values ('Admin');
INSERT INTO roles (name) values ('Bank');
INSERT INTO roles (name) values ('Entregador');

INSERT INTO users (first_name, last_name, cpf, email, password, role_id) values ('Teste', 'De User', '111.111.111-11', 'adm@test.com', '$2a$12$FodxaaXhuLZNA2jnRdXT5ODrS0bvQmwzHCouS4y4NwDduuTrRI5AS', 2);
INSERT INTO users (first_name, last_name, cpf, email, password, role_id) values ('Teste', 'De User2', '111.111.111-12', 'user@test.com', '$2a$12$FodxaaXhuLZNA2jnRdXT5ODrS0bvQmwzHCouS4y4NwDduuTrRI5AS', 1);
INSERT INTO users (first_name, last_name, cpf, email, password, role_id) values ('Bank', 'Bank', '111.111.111-13', 'bank@test.com', '$2a$12$FodxaaXhuLZNA2jnRdXT5ODrS0bvQmwzHCouS4y4NwDduuTrRI5AS', 3);  
INSERT INTO users (first_name, last_name, cpf, email, password, role_id) values ('Entregador', 'Entregador', '111.111.111-14', 'entregador@test.com', '$2a$12$FodxaaXhuLZNA2jnRdXT5ODrS0bvQmwzHCouS4y4NwDduuTrRI5AS', 4);

INSERT INTO messages (recipient, title, message) VALUES ('teste@teste3.com', 'Title 1', 'Message 1');
