TRUNCATE TABLE users RESTART IDENTITY CASCADE;
INSERT INTO users(login, password, role, course_id, user_type)
VALUES ('login4', 'pass1', 'ROLE_STUDENT', 1, 'TEACHER');
INSERT INTO users(login, password, role, course_id, user_type)
VALUES ('login5', 'pass2', 'ROLE_TEACHER', 3, 'TEACHER');
INSERT INTO users(login, password, role, course_id, user_type)
VALUES ('login6', 'pass3', 'ROLE_ADMIN', 3, 'TEACHER');