TRUNCATE TABLE users RESTART IDENTITY CASCADE;
INSERT INTO users(login, password, role, user_type) VALUES ('login1', 'pass1', 'ROLE_STUDENT','USER');
INSERT INTO users(login, password, role, user_type) VALUES ('login2', 'pass2', 'ROLE_TEACHER', 'USER');
INSERT INTO users(login, password, role, user_type) VALUES ('login3', 'pass3', 'ROLE_ADMIN', 'USER');