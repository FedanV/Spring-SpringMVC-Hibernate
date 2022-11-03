TRUNCATE TABLE users RESTART IDENTITY CASCADE;
INSERT INTO users(login, password, role, group_id, user_type)
VALUES ('login4', 'pass1', 'ROLE_STUDENT', 3, 'STUDENT');
INSERT INTO users(login, password, role, group_id, user_type)
VALUES ('login5', 'pass2', 'ROLE_TEACHER', 2, 'STUDENT');
INSERT INTO users(login, password, role, group_id, user_type)
VALUES ('login6', 'pass3', 'ROLE_ADMIN', 1, 'STUDENT');