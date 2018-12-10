create table Authentication
(
  id                 int auto_increment
    primary key,
  email              varchar(256) not null,
  hashedPassword     varchar(256) not null,
  refreshToken       varchar(256) null,
  role               varchar(256) not null,
  resetPasswordToken varchar(256) null,
  constraint Authentication_email_uindex
    unique (email),
  constraint Authentication_resetPasswordToken_uindex
    unique (resetPasswordToken)
);

create table User
(
  id     int auto_increment
    primary key,
  authId int not null,
  constraint USER_AUTH___fk
    foreign key (authId) references Authentication (id)
);

create table Card
(
  id        int auto_increment,
  `key`     varchar(256) not null,
  userId    int          null,
  email     varchar(256) not null,
  phone     varchar(256) not null,
  firstName varchar(256) not null,
  lastName  varchar(256) not null,
  constraint Card_key_uindex
    unique (`key`),
  constraint table_name_id_uindex
    unique (id),
  constraint Card_User_id_fk
    foreign key (userId) references User (id)
);

alter table Card
  add primary key (id);

