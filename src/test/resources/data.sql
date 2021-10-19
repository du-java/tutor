insert into ttr_tutors (email, password, firstname, lastname)
VALUES ('email', 'pass', 'first', 'last');

insert into ttr_groups (group_name, tutor_id)
VALUES ('gr1', 1);

insert into ttr_courses (period_start, period_end, group_id)
VALUES ('2021-09-01', '2021-12-31', 1);

insert into ttr_lessons (duration, start, course_id)
VALUES (1800000000000, '2021-09-14 11:30:00', 1),
       (1800000000000, '2021-09-14 12:30:00', 1),
       (1800000000000, '2021-09-14 13:30:00', 1);

