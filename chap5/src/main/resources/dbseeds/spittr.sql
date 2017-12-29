create table authorities
(
	username char(50) not null,
	authority char(50) not null,
	constraint authorities_username_uindex
		unique (username)
)
engine=InnoDB
;

create table group_authorities
(
	authority char(50) not null,
	group_id int not null
)
engine=InnoDB
;

create index fk_group_authorities_group
	on group_authorities (group_id)
;

create table group_members
(
	id int auto_increment
		primary key,
	username char(50) not null,
	group_id int not null,
	constraint group_members_id_uindex
		unique (id),
	constraint group_members_username_uindex
		unique (username)
)
engine=InnoDB
;

create index fk_group_members_group
	on group_members (group_id)
;

create table groups
(
	id int auto_increment
		primary key,
	group_name char(50) not null,
	constraint groups_id_uindex
		unique (id),
	constraint groups_group_name_uindex
		unique (group_name)
)
engine=InnoDB
;

alter table group_authorities
	add constraint fk_group_authorities_group
		foreign key (group_id) references groups (id)
;

alter table group_members
	add constraint fk_group_members_group
		foreign key (group_id) references groups (id)
;

create table spittle
(
	id int auto_increment
		primary key,
	message text not null,
	time datetime not null,
	latitude decimal(10,8) null,
	longitude decimal(11,8) null,
	constraint spittle_id_uindex
		unique (id)
)
engine=InnoDB
;

create table users
(
	username char(50) not null
		primary key,
	password char(60) not null,
	enabled tinyint(1) default '1' not null,
	firstname char(50) not null,
	lastname char(50) not null,
	email char(50) not null,
	photo_uuid char(36) not null,
	constraint users_username_uindex
		unique (username),
	constraint users_email_uindex
		unique (email)
)
engine=InnoDB
;

alter table authorities
	add constraint fk_authorities_users
		foreign key (username) references users (username)
;

