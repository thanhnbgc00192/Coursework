create database CourseMonitoringReport
use CourseMonitoringReport

drop table Assign
drop table Course
drop table Users
drop table Falculty

create table Users(
	u_id varchar(7) primary key,
	u_fullname nvarchar(50) not null,
	u_birthday date not null,
	u_address nvarchar(150) not null,
	u_avartar varchar(50) not null,
	u_email nvarchar(100) not null,
	u_phone varchar(30) not null,
	u_username varchar(30),
	u_password varchar(50),
	u_position varchar(10) foreign key references Position(p_acronym),
);
go

create table Position(
	p_acronym varchar(10) primary key,
	p_position nvarchar(100) not null,
);
go

create table Falculty(
	f_id int identity(3433,1) primary key,
	f_name nvarchar(50),
);
go
alter table Falculty
add f_PVC varchar(7)
alter table Falculty
add f_DLT varchar(7)

create table Course(
	c_code int primary key,
	c_name nvarchar(100) not null,
	c_avatar nvarchar(100),
	c_desc nvarchar(500) not null,
	c_falculty int not null foreign key references Falculty(f_id),
);
go

create table Assign(
	a_id int identity(1,1) primary key,
	a_course int not null foreign key references Course(c_code),
	a_CL varchar(7) not null foreign key references Users(u_id),
	a_CM varchar(7) not null foreign key references Users(u_id),
	a_year int not null,
);
go


drop table Notification
create table Notification(
	n_id int identity(1,1) primary key,
	n_report int foreign key references Report(r_id),
	n_CL varchar(7) foreign key references Users(u_id),
	n_CM varchar(7) foreign key references Users(u_id),
	n_title nvarchar(100),
	n_message nvarchar(300),
	n_status varchar(15),
	n_time datetime default GETDATE()
)
go

create table Class(
	cl_id int identity(1,1) primary key,
	cl_name varchar(50),
	cl_academicSession varchar(30),
)
go

drop table ClassDetail
create table ClassDetail(
	cd_id int identity(1,1) primary key,
	cd_class int foreign key references Class(cl_id),
	cd_student varchar(7) foreign key references Users(u_id),
	cd_teacher varchar(7) foreign key references Users(u_id),
	cd_course int foreign key references Course(c_code),
)
go


drop table Schedule
create table Schedule(
	s_id int identity(1,1) primary key,
	s_course int foreign key references Course(c_code),
	s_student varchar(7) foreign key references Users(u_id),
	s_cw1 float,
	s_cw2 float,
	s_cw3 float,
	s_exam float,
	s_overall float
)
go

drop table Report
create table Report(
	r_id int identity(1,1) primary key,
	r_class int foreign key references Class(cl_id),
	r_course int foreign key references Course(c_code),
	r_CL varchar(7) foreign key references Users(u_id),
	r_studentCount int,
	r_meanCW1 float,
	r_meanCW2 float,
	r_meanCW3 float,
	r_meanExam float,
	r_meanOverall float,
	r_medianCW1 float,
	r_medianCW2 float,
	r_medianCW3 float,
	r_medianExam float,
	r_medianOverall float,
	r_standardDeviationCW1 float,
	r_standardDeviationCW2 float,
	r_standardDeviationCW3 float,
	r_standardDeviationExam float,
	r_standardDeviationOverall float,
	r_cw1_0_39 float,
	r_cw1_40_69 float,
	r_cw1_70_100 float,
	r_cw2_0_39 float,
	r_cw2_40_69 float,
	r_cw2_70_100 float,
	r_cw3_0_39 float,
	r_cw3_40_69 float,
	r_cw3_70_100 float,
	r_exam_0_39 float,
	r_exam_40_69 float,
	r_exam_70_100 float,
	r_overall_0_39 float,
	r_overall_40_69 float,
	r_overall_70_100 float,
	r_generalComment nvarchar(1000),
	r_actionToBeTaken nvarchar(500),
	r_timeCreate datetime default GETDATE(),
	r_status varchar(20),
)
go

select c.c_code,c.c_name,c.c_desc,f.f_name,a.a_year from Assign a 
inner join Course c on a.a_course=c.c_code
inner join Falculty f on c.c_falculty=f.f_id
where a.a_CL='CL0001' or a.a_CM='CL0001'

select u.u_fullname from Users u 
inner join Assign a on u.u_id=a.a_CL or u.u_id=a.a_CM
inner join Course c on c.c_code=a.a_course
where c.c_code=333004 and u.u_position='CL'

drop procedure insertCourse
CREATE PROCEDURE insertReport 
	@r_class int,@r_course int,@r_CL varchar(7),@r_studentCount int,@r_meanCW1 float,
	@r_meanCW2 float,@r_meanCW3 float,@r_meanExam float,@r_meanOverall float,
	@r_medianCW1 float,@r_medianCW2 float,@r_medianCW3 float,@r_medianExam float,@r_medianOverall float,
	@r_standardDeviationCW1 float,@r_standardDeviationCW2 float,@r_standardDeviationCW3 float,
	@r_standardDeviationExam float,@r_standardDeviationOverall float,@r_cw1_0_39 float,
	@r_cw1_40_69 float,@r_cw1_70_100 float,@r_cw2_0_39 float,@r_cw2_40_69 float,
	@r_cw2_70_100 float,@r_cw3_0_39 float,@r_cw3_40_69 float,@r_cw3_70_100 float,
	@r_exam_0_39 float,@r_exam_40_69 float,@r_exam_70_100 float,
	@r_overall_0_39 float,@r_overall_40_69 float,@r_overall_70_100 float,
	@r_generalComment nvarchar(1000),@r_actionToBeTaken nvarchar(500),@r_status varchar(20)
AS 
INSERT INTO Report(
	r_class,r_course,r_CL,r_studentCount,r_meanCW1,r_meanCW2,r_meanCW3,r_meanExam,r_meanOverall,
	r_medianCW1,r_medianCW2,r_medianCW3,r_medianExam,r_medianOverall,r_standardDeviationCW1,
	r_standardDeviationCW2,r_standardDeviationCW3,r_standardDeviationExam,r_standardDeviationOverall,
	r_cw1_0_39,r_cw1_40_69,r_cw1_70_100,r_cw2_0_39,r_cw2_40_69,r_cw2_70_100,r_cw3_0_39,r_cw3_40_69,
	r_cw3_70_100,r_exam_0_39,r_exam_40_69,r_exam_70_100,r_overall_0_39,r_overall_40_69,r_overall_70_100,
	r_generalComment,r_actionToBeTaken,r_status
	) values(
	@r_class,@r_course,@r_CL,@r_studentCount,
	@r_meanCW1,@r_meanCW2,@r_meanCW3,@r_meanExam,@r_meanOverall,@r_medianCW1,
	@r_medianCW2,@r_medianCW3,@r_medianExam,@r_medianOverall,@r_standardDeviationCW1,
	@r_standardDeviationCW2,@r_standardDeviationCW3,@r_standardDeviationExam,@r_standardDeviationOverall,
	@r_cw1_0_39,@r_cw1_40_69,@r_cw1_70_100,
	@r_cw2_0_39,@r_cw2_40_69,@r_cw2_70_100,@r_cw3_0_39,@r_cw3_40_69,@r_cw3_70_100,
	@r_exam_0_39,@r_exam_40_69,@r_exam_70_100,@r_overall_0_39,@r_overall_40_69,
	@r_overall_70_100,@r_generalComment,@r_actionToBeTaken,@r_status)
go

CREATE PROCEDURE insertCourse
	@c_name nvarchar(100),
	@c_avatar nvarchar(100),
	@c_desc nvarchar(500),
	@c_falculty int
AS 
INSERT INTO Course values(NEXT VALUE FOR seq_course,@c_name,@c_avatar,@c_desc,@c_falculty)
go

CREATE PROCEDURE editCourse
	@c_code int,  
	@c_name nvarchar(100),
	@c_avatar nvarchar(100),
	@c_desc nvarchar(500),
	@c_falculty int
AS 
UPDATE Course SET c_name=@c_name,c_avatar=@c_avatar,c_desc=@c_desc,c_falculty=@c_falculty
WHERE c_code=@c_code
GO

DROP SEQUENCE seq_course
CREATE SEQUENCE seq_course
START WITH 333000
INCREMENT BY 1

select * from Class
select * from Users
select * from Position
select * from Course
select * from Falculty
select * from Assign
select * from Notification
select * from Report
select * from Class
select * from ClassDetail
select * from Schedule
select * from Report



insert into Schedule values(333007,'ST0001',39,56,80,55,59),
(333007,'ST0002',61,42,73,60,58),
(333007,'ST0003',25,49,52,41,43)

insert into Position values('AD','Administrator')
insert into Position values('PVC','Pro-Vice Chancellor')
insert into Position values('DLT','Director of Learning and Quality')
insert into Position values('CL','Course Leader')
insert into Position values('CM','Course Moderator')
insert into Position values('ST','Student')
insert into Position values('TC','Teacher')

insert into Class values('GH10001','Spring 2016'),('GH10002','Spring 2016'),('GH10003','Summer 2016'),('GH10004','Spring 2015')

insert into ClassDetail values(1,'ST0001','TC0002',333007),
(1,'ST0002','TC0002',333007),
(1,'ST0003','TC0002',333007),
(2,'ST0001','TC0002',333058),
(2,'ST0002','TC0002',333058),
(2,'ST0003','TC0002',333058),
(3,'ST0001','TC0002',333008),
(3,'ST0002','TC0002',333008),
(3,'ST0003','TC0002',333008)


insert into Falculty values('Software technology')
insert into Falculty values('Business administration')
insert into Falculty values('Graphic design')
delete Course where c_code='333002'
delete Users where u_id='AD0001'

insert into Users values('AD0001','Joel Jr.','1991/1/1','New York','avartar.jpg','admin@gmail.com','0909090909','admins','e10adc3949ba59abbe56e057f20f883e','AD')
insert into Users values('CL0001','Mariana','1991/1/1','New York','avartar.jpg','cl@gmail.com','0909090909','courseleader','e10adc3949ba59abbe56e057f20f883e','CL')
insert into Users values('CL0002','Mariana2','1991/1/1','New York','avartar.jpg','cl@gmail.com','0909090909','courseleader2','e10adc3949ba59abbe56e057f20f883e','CL')
insert into Users values('CM0001','Luli','1991/1/1','New York','avartar.jpg','cl@gmail.com','0909090909','coursemonderator','e10adc3949ba59abbe56e057f20f883e','CM')
insert into Users values('CM0002','Luli','1991/1/1','New York','avartar.jpg','cl@gmail.com','0909090909','coursemonderator2','e10adc3949ba59abbe56e057f20f883e','CM')
insert into Users values('ST0001','ThanhNB','1995/1/1','Hanoi','avartar.jpg','student@gmail.com','0909090909','student','e10adc3949ba59abbe56e057f20f883e','ST')
insert into Users values('ST0002','TuanBB','1995/1/1','Hanoi','avartar.jpg','student2@gmail.com','0909090909','student2','e10adc3949ba59abbe56e057f20f883e','ST')
insert into Users values('ST0003','QuyetTT','1995/1/1','Hanoi','avartar.jpg','student@gmail.com','0909090909','student3','e10adc3949ba59abbe56e057f20f883e','ST')
insert into Users values('TC0002','DuongPT','1977/1/1','HoChiMinh','avartar.jpg','teacher@gmail.com','0909090909','teacher','e10adc3949ba59abbe56e057f20f883e','TC')
insert into Users values('PVC0003','John','1978/1/1','Hanoi','avartar.jpg','pvc@gmail.com','0909090909','provice','e10adc3949ba59abbe56e057f20f883e','PVC')
insert into Users values('DLT0002','Smith','1984/1/1','HoChiMinh','avartar.jpg','dlt@gmail.com','0909090909','director','e10adc3949ba59abbe56e057f20f883e','DLT')

insert into Assign values(333004,'CL0001','CM0001',2016)
insert into Assign values(333004,'CL0002','CM0001',2016)
insert into Assign values(333004,'CL0001','CM0002',2016)
insert into Assign values(333004,'CL0001','CM0002',2016)
insert into Assign values(333004,'CL0002','CM0001',2016)
insert into Assign values(333005,'CL0002','CM0001',2016)
insert into Assign values(333006,'CL0002','CM0001',2016)
insert into Assign values(333007,'CL0002','CM0001',2016)
insert into Assign values(333005,'CL0002','CM0001',2016)

insert into Notification(n_report,n_CL,n_CM,n_title,n_message,n_status) values(2,'CL0001','CM0001','Approve for report','I want to create this report to do something...','unavailable'),
(3,'CL0002','CM0001','Approve for report','I want to create this report to do something...','unavailable'),
(4,'CL0001','CM0001','Approve for report','I want to create this report to do something...','unavailable'),
(1,'CL0001','CM0002','Approve for report','I want to create this report to do something...','unavailable')

insert into Notification(n_report,n_CL,n_CM,n_title,n_message,n_status) values(2,'CL0001','CM0001','Approve for report','I want to create this report to do something...','unavailable'),
(3,'CL0002','CM0001','Approve for report','I want to create this report to do something...','available'),
(4,'CL0001','CM0001','Approve for report','I want to create this report to do something...','unavailable'),
(1,'CL0001','CM0002','Approve for report','I want to create this report to do something...','suspended')
delete Assign where a_id=4
select * from Assign

select * from Notification

select r.r_content,n.n_title,n.n_message,u.u_id,n.n_status,n.n_time from Notification n
inner join Report r on n.n_report=r.r_id
inner join Users u on n.n_CL=u.u_id
where u.u_position='CL' and n.n_CM='CM0001'

select * from Notification where n_CM='CM0001'
select COUNT(*) AS 'rowcount' from Notification where n_CM='CM0003'
select COUNT(*) AS 'countStudent' from Users where u_position='ST'