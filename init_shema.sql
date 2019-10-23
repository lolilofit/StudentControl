create table `groups`
(
	id int auto_increment,
	num int not null
)
comment 'Table of groups';

create unique index groups_id_uindex
	on `groups` (id);

create unique index groups_num_uindex
	on `groups` (num);

alter table `groups`
	add constraint groups_pk
		primary key (id);

create table students
(
	stud_id int null,
	name varchar(50) not null,
	`group` int not null,
	constraint students_pk
		primary key (stud_id),
	constraint students_groups_id_fk
		foreign key (`group`) references `groups` (id)
			on update cascade on delete cascade
)
comment 'Table of students';

create table teachers
(
	teach_id int auto_increment,
	name varchar(50) not null,
	constraint teachers_pk
		primary key (teach_id)
)
comment 'Table of teachers';

create table subjects
(
	subj_id int auto_increment,
	subj_name varchar(30) not null,
	constraint subjects_pk
		primary key (subj_id)
)
comment 'Table of subjects';

create unique index subjects_subj_name_uindex
	on subjects (subj_name);

create table activity 
(
	activity_id int auto_increment,
	subject_id int not null,
	teacher_id int not null,
	group_id int not null,
	constraint activity_pk
		primary key (activity_id)
)	
	
alter table ooad.activity add unique (subject_id, teacher_id, group_id);

alter table activity
	add constraint activity_groups_id_fk
		foreign key (group_id) references `groups` (id)
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
	id int auto_increment,
	activity_id int not null,
	start_time time not null,
	end_time time not null,
	day varchar(20) not null,
	class int not null,
	constraint timetable_pk
		primary key (id),
	constraint timetable_activity_activity_id_fk
		foreign key (activity_id) references activity (activity_id)
			on update cascade on delete cascade
)
comment 'Table describes timetable of the university';

create table attendance
(
	stud_id int not null,
	lesson_id int not null,
	status bool not null,
	constraint attendance_pk
		unique (stud_id, lesson_id),
	constraint attendance_students_stud_id_fk
		foreign key (stud_id) references students (stud_id)
			on update cascade on delete cascade,
	constraint attendance_timetable_id_fk
		foreign key (lesson_id) references timetable (id)
			on update cascade on delete cascade
);

		