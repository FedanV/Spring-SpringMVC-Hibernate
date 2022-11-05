ALTER TABLE users
    ADD IF NOT EXISTS group_id int references groups (id) ON DELETE SET NULL;
ALTER TABLE users
    ADD IF NOT EXISTS course_id int references course (id) ON DELETE SET NULL;