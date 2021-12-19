DROP TYPE IF EXISTS skills CASCADE ;
CREATE TYPE skills AS ENUM ('Java', 'C#', 'Python');

DROP TABLE IF EXISTS student CASCADE;
CREATE TABLE student
(
    id               INT GENERATED ALWAYS AS IDENTITY,
    name             VARCHAR(64) NOT NULL CHECK (name !~* '[@#$]'),
    surname          VARCHAR(64) NOT NULL,
    date_of_birth    DATE        NOT NULL,
    phone_number     NUMERIC(10,0)   NULL,
    primary_skill    skills      NOT NULL,
    created_datetime TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_datetime TIMESTAMP,
    country          VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE OR REPLACE FUNCTION update_updated_column()
              RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_datetime = now();
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_student_updated_datetime BEFORE UPDATE
    ON student FOR EACH ROW EXECUTE PROCEDURE
    update_updated_column();

DROP TYPE IF EXISTS complexity CASCADE;
CREATE TYPE complexity AS ENUM ('Easy', 'Medium', 'Hard');

DROP TABLE IF EXISTS subject CASCADE ;
CREATE TABLE subject
(
    id               INT GENERATED ALWAYS AS IDENTITY,
    name             VARCHAR(255) NOT NULL UNIQUE,
    tutor            VARCHAR(255) NOT NULL,
    complexity_level complexity   NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS exam_result CASCADE ;
CREATE TABLE exam_result
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    mark       SMALLINT CHECK (mark > 0 AND mark < 6),
    PRIMARY KEY (id),
    CONSTRAINT student_fk
        FOREIGN KEY (student_id)
            REFERENCES student (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE ,
    CONSTRAINT subject_fk
        FOREIGN KEY (subject_id)
            REFERENCES subject (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

