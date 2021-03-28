--liquibase formatted sql

--changeset lbibera:00000_initial_db
--comment: initial database for ping entity
create table ping_entity
(
    id bigserial not null constraint ping_entity_pk primary key,
    date_created timestamp,
    name varchar(255)
);

--changeset lbibera:00001_user_profile_init
--comment: initial database for the user profile storage
create table user_profile
(
    id varchar(500) not null constraint user_profile_pk primary key,
    full_name varchar(1000)
);