--liquibase formatted sql

--changeset lbibera:00000_initial_db
--comment: initial database for ping entity
create table ping_entity
(
    id bigserial not null constraint ping_entity_pk primary key,
    date_created timestamp,
    name varchar(255)
);