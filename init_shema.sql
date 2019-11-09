create table groups
(
	id number(10),
	num number(10) not null
)
;

comment on table groups is 'Table of groups'

-- Generate ID using sequence and trigger
create sequence groups_seq start with 1 increment by 1;

create or replace trigger groups_seq_tr
 before insert on groups for each row
 when (new.id is null)
begin
 select groups_seq.nextval into :new.id from dual;
end;
/

create unique index groups_id_uindex
	on groups (id);

create unique index groups_num_uindex
	on groups (num);

alter table groups
	add constraint groups_pk
		primary key (id);

create table students
(
	stud_id number(10) null,
	name varchar2(50) not null,
	group number(10) not null,
	constraint students_pk
		primary key (stud_id),
	constraint students_groups_id_fk
		foreign key (group) references groups (id)
			on update cascade on delete cascade
)
;

comment on table students is 'Table of students'

create table teachers
(
	teach_id number(10),
	name varchar2(50) not null,
	constraint teachers_pk
		primary key (teach_id)
)
;

comment on table teachers is 'Table of teachers'

-- Generate ID using sequence and trigger
create sequence teachers_seq start with 1 increment by 1;

create or replace trigger teachers_seq_tr
 before insert on teachers for each row
 when (new.teach_id is null)
begin
 select teachers_seq.nextval into :new.teach_id from dual;
end;
/

create table subjects
(
	subj_id number(10),
	subj_name varchar2(30) not null,
	constraint subjects_pk
		primary key (subj_id)
)
;

comment on table subjects is 'Table of subjects'

-- Generate ID using sequence and trigger
create sequence subjects_seq start with 1 increment by 1;

create or replace trigger subjects_seq_tr
 before insert on subjects for each row
 when (new.subj_id is null)
begin
 select subjects_seq.nextval into :new.subj_id from dual;
end;
/

create unique index subjects_subj_name_uindex
	on subjects (subj_name);

create table activity 
(
	activity_id number(10),
	subject_id number(10) not null,
	teacher_id number(10) not null,
	group_id number(10) not null,
	constraint activity_pk
		primary key (activity_id)
);

-- Generate ID using sequence and trigger
create sequence activity_seq start with 1 increment by 1;

create or replace trigger activity_seq_tr
 before insert on activity for each row
 when (new.activity_id is null)
begin
 select activity_seq.nextval into :new.activity_id from dual;
end;
/	
	
alter table ooad.activity add unique (subject_id, teacher_id, group_id);

alter table activity
	add constraint activity_groups_id_fk
		foreign key (group_id) references groups (id)
			on update cascade on delete cascade;

alter table activity
	add constraint activity_subjects_subj_id_fk
		foreign key (subject_id) references subjects (subj_id)
			on update cascade on delete cascade;

alter table activity
	add constraint activity_teachers_teach_id_fk
		foreign key (teacher_id) references teachers (teach_id)
			on update cascade on delete cascade;

create table timetable
(
	id number(10),
	activity_id number(10) not null,
	start_time timestamp(0) not null,
	end_time timestamp(0) not null,
	day varchar2(20) not null,
	class number(10) not null,
	constraint timetable_pk
		primary key (id),
	constraint timetable_activity_activity_id_fk
		foreign key (activity_id) references activity (activity_id)
			on update cascade on delete cascade
)
;

comment on table timetable is 'Table describes timetable of the university'

-- Generate ID using sequence and trigger
create sequence timetable_seq start with 1 increment by 1;

create or replace trigger timetable_seq_tr
 before insert on timetable for each row
 when (new.id is null)
begin
 select timetable_seq.nextval into :new.id from dual;
end;
/

create table attendance
(
	stud_id number(10) not null,
	lesson_id number(10) not null,
	status char(1) not null,
	constraint attendance_pk
		unique (stud_id, lesson_id),
	constraint attendance_students_stud_id_fk
		foreign key (stud_id) references students (stud_id)
			on update cascade on delete cascade,
	constraint attendance_timetable_id_fk
		foreign key (lesson_id) references timetable (id)
			on update cascade on delete cascade
);
