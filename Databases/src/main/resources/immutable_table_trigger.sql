DROP TABLE IF EXISTS mutable_student_address CASCADE;
CREATE TABLE mutable_student_address
(
    id           INT,
    country      VARCHAR(64)    NOT NULL CHECK (country !~* '[@#$]'),
    region       VARCHAR(64)    NOT NULL,
    city         varchar        NOT NULL,
    street       VARCHAR(64),
    house_number INT            NOT NULL,
    postal_code  NUMERIC(10, 0) NULL,
    changed_on   timestamp(6)   NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS student_address CASCADE;
CREATE TABLE student_address
(
    id           INT GENERATED ALWAYS AS IDENTITY,
    country      VARCHAR(64)    NULL CHECK (country !~* '[@#$]'),
    region       VARCHAR(64)    NULL,
    city         varchar        NULL,
    street       VARCHAR(64),
    house_number INT            NOT NULL,
    postal_code  NUMERIC(10, 0) NULL,
    PRIMARY KEY (id)
);

CREATE OR REPLACE FUNCTION fnprevent_update()
    RETURNS trigger AS
$BODY$
declare
    country_l      VARCHAR;
    region_l       VARCHAR;
    city_l         varchar;
    street_l       varchar;
    house_number_l int;
    postal_code_l  numeric(10, 0);
BEGIN
    country_l := old.country;
    region_l := old.region;
    city_l := old.city;
    street_l := old.street;
    house_number_l := old.house_number;
    postal_code_l := old.postal_code;
    IF NEW.country <> OLD.country THEN
        country_l := new.country;
    END IF;
    IF NEW.region <> OLD.region THEN
        region_l := new.region;
    END IF;
    IF NEW.street <> OLD.street THEN
        street_l := new.street;
    END IF;
    IF NEW.house_number <> OLD.house_number THEN
        house_number_l := new.house_number;
    END IF;
    IF NEW.city <> OLD.city THEN
        city_l := new.city;
    END IF;
    IF NEW.postal_code <> OLD.postal_code THEN
        postal_code_l := new.postal_code;
    END IF;
    insert into mutable_student_address (id, country, region, city, street, house_number, postal_code, changed_on)
    values (old.id, country_l, region_l, city_l, street_l, house_number_l, postal_code_l, now());
    return new;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
                     COST 100;


CREATE TRIGGER trg_prevent_update
    BEFORE UPDATE
    ON student_address
    FOR EACH ROW
EXECUTE PROCEDURE fnprevent_update();

insert into student_address(country, region, city, street, house_number, postal_code)
VALUES ('India', 'test', 'test', 'test', 15, 1234567890);
update student_address
set country = 'USA'
where country = 'India';
select *
from mutable_student_address;