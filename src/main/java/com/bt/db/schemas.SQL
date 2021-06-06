-- auto-generated definition
create table admin
(
    id       int auto_increment
        primary key,
    role     int          null,
    username varchar(256) null,
    password varchar(256) null,
    constraint admin_username_uindex
        unique (username),
    constraint role__FK
        foreign key (role) references roles (id)
            on delete cascade
);

-- auto-generated definition
create table manager
(
    id         int auto_increment
        primary key,
    role       int default 2 null,
    username   varchar(256)  not null,
    password   varchar(256)  null,
    first_name varchar(256)  null,
    last_name  varchar(256)  null,
    avatar_url varchar(512)  null,
    constraint manager_username_uindex
        unique (username),
    constraint manager_role___FK
        foreign key (role) references roles (id)
            on delete cascade
)
    comment 'The manager is responsible for maintaining organization(s)';

-- auto-generated definition
create table `order`
(
    id           int auto_increment
        primary key,
    user_id      int                  null,
    party_id     int                  null,
    rating       int                  null,
    has_discount tinyint(1) default 0 null,
    constraint party_order___FK
        foreign key (party_id) references party (id)
            on delete cascade,
    constraint user_order___FK
        foreign key (user_id) references user (id)
            on delete cascade
);

-- auto-generated definition
create table organization
(
    id            int auto_increment
        primary key,
    name          varchar(256)    not null,
    abbreviation  varchar(10)     not null,
    description   text            null,
    thumbnail_url varchar(512)    not null,
    rating        float default 0 not null,
    manager_id    int             null,
    constraint manager_id___FK
        foreign key (manager_id) references manager (id)
            on delete cascade
)
    comment 'Organization is responsible for creating events';

-- auto-generated definition
create table party
(
    id                  int auto_increment
        primary key,
    name                varchar(256)                                                                                                                                                                           null,
    description         text                                                                                                                                                                                   null,
    thumbnail_url       varchar(512) default 'https://images.unsplash.com/photo-1602631985686-1bb0e6a8696e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80;' null,
    num_of_participants smallint     default 0                                                                                                                                                                 not null,
    max_participants    smallint                                                                                                                                                                               not null,
    has_free_spots      tinyint(1)   default 1                                                                                                                                                                 null,
    price               float        default 0                                                                                                                                                                 not null,
    organization_id     int                                                                                                                                                                                    null,
    constraint organization_id__FK
        foreign key (organization_id) references organization (id)
            on delete cascade
);

-- auto-generated definition
create table roles
(
    id   int auto_increment
        primary key,
    name varchar(40) null
);


-- auto-generated definition
create table user
(
    id         int auto_increment
        primary key,
    role       int default 3 null,
    username   varchar(256)  null,
    password   varchar(256)  not null,
    first_name varchar(256)  null,
    last_name  varchar(256)  null,
    avatar_url varchar(512)  null,
    constraint user_username_uindex
        unique (username),
    constraint user_role___FK
        foreign key (role) references roles (id)
            on delete cascade
);
