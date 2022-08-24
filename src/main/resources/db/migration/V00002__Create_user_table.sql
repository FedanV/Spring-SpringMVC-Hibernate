CREATE TABLE "user"
(
    id        int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name      varchar(100) NOT NULL,
    surname   varchar(100) NOT NULL,
    age       int CHECK ( age > 0 ),
    phone     varchar(20) UNIQUE,
    login     varchar(100),
    password  varchar(100),
    role      varchar(20),
    user_type varchar(20),
    specialization varchar(20),
    course   int CHECK ( course > 0 ),
    faculty  varchar(50),
    group_id int REFERENCES "group" (id) ON DELETE SET NULL
);