TRUNCATE TABLE teacher_course RESTART IDENTITY;
INSERT INTO teacher_course(user_id, course_id) VALUES (1, 2);
INSERT INTO teacher_course(user_id, course_id) VALUES (1, 3);
INSERT INTO teacher_course(user_id, course_id) VALUES (2, 3);