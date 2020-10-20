--uncomment first line to create schema, switch active schema to rockomax, re-comment line, run script.
--create schema rockomax;

create user rockomax_worker with password 'kerbodyne-sucks';

grant all privileges on schema rockomax to rockomax_worker;

create table crs_reimb_status(
	reimb_status_id serial primary key,
	reimb_status text

);

--drop table crs_reimb_status ;
create table crs_reimb_type(
	reimb_type_id serial primary key,
	reimb_type text 
);
--drop table crs_reimb_type ;
create table crs_user_roles(
	crs_user_role_id serial primary key,
	user_role text
);

--drop table crs_user_roles ;

create table crs_users(
	crs_users_id serial primary key,
	crs_username text unique,
	crs_password text,
	user_first_name text,
	user_last_name text,
	user_email text,
	user_role_id int references crs_user_roles(crs_user_role_id)
	
);

--drop table crs_users ;
create table crs_reimb( -- CRS Reimbursement
	reimb_id serial primary key,
	reimb_project int references rockomax_projects(project_id),
	reimb_ammount numeric,
	reimb_submitted timestamp,
	reimb_resolved timestamp,
	reimb_description text,
	reimb_receipt bytea,
	reimb_author int references crs_users(crs_users_id),
	reimb_resolver int references crs_users(crs_users_id),
	reimb_status_id int references crs_reimb_status(reimb_status_id),
	reimb_type_id int references crs_reimb_type(reimb_type_id)
	

);
--drop table crs_reimb;

create table rockomax_projects(
	project_id serial primary key,
	project_name text unique,
	project_lv_costs numeric,
	project_pl_costs numeric,
	project_description text,
	project_client int references crs_users(crs_users_id)
	
);

create table rockomax_projects_status(
	project_status_id serial primary key,
	project_status text
);

--drop table rockomax_projects_status;

alter table crs_reimb add reimb_project_id int references rockomax_projects(project_id);

alter table rockomax_projects add project_status int references rockomax_projects_status(project_status_id);

alter table rockomax_projects add craft_file bytea;
alter table rockomax_projects add payload_image bytea;
alter table rockomax_projects add launcher_image bytea;

create view projects_view as
	select
		project_id,
		project_name,
		crs.crs_username,
		crs.user_first_name,
		crs.user_last_name,
		crs.user_email,
		project_lv_costs,
		project_pl_costs,
		project_description,
		s.project_status,
		craft_file,
		payload_image,
		launcher_image
	from 
		rockomax_projects as r
	inner join crs_users as crs
		on r.project_client = crs.crs_users_id
	inner join rockomax_projects_status as s
		on r.project_status = s.project_status_id;
select all from projects_view ;
--drop view projects_view;
create view reimb_view_pending as
	select
		reimb_id,
		p.project_name,
		reimb_ammount,	
		reimb_submitted,
		reimb_description,
		reimb_receipt,
		u.crs_username as client,--client
		u.user_first_name as c_first_name,
		u.user_last_name as c_last_name,
		s.reimb_status,
		t.reimb_type		
	from 
		crs_reimb as r
	inner join rockomax_projects as ro
		on r.reimb_project = ro.project_id
	inner join crs_users as u
		on r.reimb_author = u.crs_users_id 
	inner join crs_reimb_status as s
		on r.reimb_status_id = s.reimb_status_id
	inner join crs_reimb_type  as t
		on r.reimb_type_id = t.reimb_type_id
	inner join rockomax_projects as p
		on r.reimb_project = p.project_id;
--drop view reimb_view_pending;
create view reimb_view_resolved as
	select
		reimb_id,
		p.project_name,
		reimb_ammount,	
		reimb_submitted,
		reimb_resolved 
		reimb_description,
		reimb_receipt,
		u.crs_username as client,--client
		u.user_first_name as c_first_name,
		u.user_last_name as c_last_name,
		ut.user_first_name as fm_first_name,
		ut.user_last_name  as fm_last_name,
		s.reimb_status,
		t.reimb_type		
	from 
		crs_reimb as r
	inner join rockomax_projects as ro
		on r.reimb_project = ro.project_id
	inner join crs_users as u
		on r.reimb_author = u.crs_users_id 
	inner join crs_users as ut
		on r.reimb_resolver = u.crs_users_id 
	inner join crs_reimb_status as s
		on r.reimb_status_id = s.reimb_status_id
	inner join crs_reimb_type  as t
		on r.reimb_type_id = t.reimb_type_id
	inner join rockomax_projects as p
		on r.reimb_project = p.project_id;



insert into rockomax_projects_status (project_status) values ('In-Development'),('Finished'),('Failed');

select * from rockomax_projects_status;
insert into crs_reimb_status (reimb_status) values ('Pending'),('Approved'),('Denied');
insert into crs_reimb_type (reimb_type) values ('Payload'),('Infrastructure'),('Loss of Life'),('Misc. Damages');
insert into crs_user_roles (user_role) values ('Finance Manager'),('Client');
