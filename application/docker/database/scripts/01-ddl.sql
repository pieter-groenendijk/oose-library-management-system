drop table if exists
    "Account"
cascade;

create table
    "Account"
(
    "accountId" bigserial not null,
    "email" varchar(320) not null,
    constraint "pkAccount" primary key ("accountId")
)
