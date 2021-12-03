create  table if not exists hibernate_sequence
(
    next_val bigint
) engine = InnoDB;
insert into hibernate_sequence
values (1);
create table if not exists role
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
) engine = InnoDB;
create table if not exists user
(
    id       bigint      not null,
    age      integer     not null,
    email    varchar(64) not null,
    password varchar(255),
    username varchar(64) not null,
    primary key (id)
) engine = InnoDB;
create table if not exists user_roles
(
    user_id  bigint not null,
    roles_id bigint not null,
    primary key (user_id, roles_id)
) engine = InnoDB;
alter table user
    add constraint email_fk unique (email);
alter table user
    add constraint username_fk unique (username);
alter table user_roles
    add constraint user_roles_pk
        foreign key (roles_id)
            references role (id);
alter table user_roles
    add constraint user_pk
        foreign key (user_id)
            references user (id);