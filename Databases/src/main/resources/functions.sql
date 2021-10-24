-- Create function that will return average mark for input user. (0.3 point)
create or replace function student_avg_mark(std_id int)
    returns numeric(3, 2)
    language plpgsql
as
$$
declare
    avg_mark numeric(3, 2);
begin
    select avg(mark) into avg_mark from exam_result where student_id = std_id;
    return avg_mark;
end;
$$;

-- Create function that will return average mark for input subject name.
create or replace function subject_avg_mark_by_name(subject_name varchar(255))
    returns numeric(3, 2)
    language plpgsql
as
$$
declare
    avg_mark numeric(3, 2);
    total    int;
    marks    int[];
    subj_id  float4;
    mark     int;
begin
    select id into subj_id from subject where name = subject_name;
    select mark into marks from exam_result where subject_id = subj_id;

    FOREACH mark IN ARRAY marks
        LOOP
            total := total + mark;
        END LOOP;

    avg_mark := total / cardinality(marks);
    return avg_mark;
end;
$$;

--Function that will return boolean value if user in "red zone" (red zone means at least 2 marks <=3)
CREATE OR REPLACE FUNCTION is_student_in_red_zone(user_id int)
    RETURNS boolean
    language plpgsql
AS
$$
declare
    bad_marks_counter smallint;
    student_marks     int[];
    mark              int;
BEGIN
    SELECT mark INTO student_marks FROM exam_result WHERE student_id = user_id;
    FOREACH mark IN ARRAY student_marks
        LOOP
            IF mark <= 3 THEN
                bad_marks_counter := bad_marks_counter + 1;
            END IF;
            IF bad_marks_counter > 2 THEN
                return true;
            END IF;
        END LOOP;
    return false;
END;
$$;