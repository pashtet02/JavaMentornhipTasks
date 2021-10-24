Update student set name = 'Pavlo', surname = 'Khodachok', phone_number = 8800555353 where id = 555;

--Find user by name (exact match)
---------------------------------
explain analyze select * from student where name = 'Pavlo'; --about 8ms

DROP INDEX IF EXISTS student_name_idx CASCADE;
CREATE INDEX student_name_idx ON student USING gist (name);
-- about 0.4ms -> btree 0.021 -> hash 0.041 -> gin 0.131 -> gist
explain analyze select * from student where name = 'Pavlo';

--Find user by surname (partial match)
--------------------------------------
--with full match it takes 11.070 ms without indexes
explain analyze select * from student where surname = 'Khodachok';

DROP INDEX IF EXISTS student_surname_idx CASCADE ;
CREATE INDEX student_surname_idx ON student USING btree (surname);
--with full name it takes about btree -> 0.521 ms, hash -> 0.23, gin -> 0.33 ms
explain analyze select * from student where surname = 'Khodachok';

--with partial match at 'Khodacho%' or 'Kho%chok' indexes does not work :(
DROP INDEX IF EXISTS student_surname_idx CASCADE ;
CREATE INDEX student_surname_idx ON student USING btree (surname);
explain analyze select * from student where surname LIKE 'Kho%hok';

--Find user by phone number (partial match)
-------------------------------------------
--without index execution took 14.429 ms
explain analyze select * from student where phone_number > 8800555350;
DROP INDEX IF EXISTS student_phone_number_idx CASCADE ;
CREATE INDEX student_phone_number_idx ON student USING btree (phone_number);
--btree -> 6.024
--hash -> 14.022
--gin -> 5.424
explain analyze select * from student where phone_number > 8800555350;

--Find user with marks by user surname (partial match)
------------------------------------------------------
--without index -> 67 ms
--hash -> 70.7
--btree -> 77
--gin -> 71 ms
DROP INDEX IF EXISTS student_surname_idx;
CREATE INDEX student_surname_idx ON student USING gin(surname);
explain analyze select s.name, s.surname, s.country, er.mark from student s
JOIN exam_result er on s.id = er.student_id
where s.surname LIKE 'Khoda%';

-- Create snapshot that will contain next data: student name, student surname, subject name, mark
-- (snapshot means that in case of changing some data in source table – your snapshot should not change)
CREATE TABLE IF NOT EXISTS student_subject_mark AS SELECT s.name as student_name, s.surname as student_surname, sub.name as subject_name, er.mark from student s
INNER JOIN exam_result er on s.id = er.student_id
INNER JOIN subject sub on er.subject_id = sub.id;