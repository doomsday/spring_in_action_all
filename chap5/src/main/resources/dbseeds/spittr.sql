CREATE TABLE authorities
(
  username  CHAR(50) NOT NULL,
  authority CHAR(50) NOT NULL,
  CONSTRAINT authorities_username_uindex
  UNIQUE (username)
)
  ENGINE = InnoDB;

CREATE TABLE group_authorities
(
  authority CHAR(50) NOT NULL,
  group_id  INT      NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX fk_group_authorities_group
  ON group_authorities (group_id);

CREATE TABLE group_members
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  username CHAR(50) NOT NULL,
  group_id INT      NOT NULL,
  CONSTRAINT group_members_id_uindex
  UNIQUE (id),
  CONSTRAINT group_members_username_uindex
  UNIQUE (username)
)
  ENGINE = InnoDB;

CREATE INDEX fk_group_members_group
  ON group_members (group_id);

CREATE TABLE groups
(
  id         INT AUTO_INCREMENT
    PRIMARY KEY,
  group_name CHAR(50) NOT NULL,
  CONSTRAINT groups_id_uindex
  UNIQUE (id),
  CONSTRAINT groups_group_name_uindex
  UNIQUE (group_name)
)
  ENGINE = InnoDB;

ALTER TABLE group_authorities
  ADD CONSTRAINT fk_group_authorities_group
FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE group_members
  ADD CONSTRAINT fk_group_members_group
FOREIGN KEY (group_id) REFERENCES groups (id);

CREATE TABLE spittle
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  message   TEXT           NOT NULL,
  time      DATETIME       NOT NULL,
  latitude  DECIMAL(10, 8) NULL,
  longitude DECIMAL(11, 8) NULL,
  CONSTRAINT spittle_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE users
(
  username   CHAR(50)               NOT NULL
    PRIMARY KEY,
  password   CHAR(60)               NOT NULL,
  enabled    TINYINT(1) DEFAULT '1' NOT NULL,
  firstname  CHAR(50)               NOT NULL,
  lastname   CHAR(50)               NOT NULL,
  email      CHAR(50)               NOT NULL,
  photo_uuid CHAR(36)               NOT NULL,
  CONSTRAINT users_username_uindex
  UNIQUE (username),
  CONSTRAINT users_email_uindex
  UNIQUE (email)
)
  ENGINE = InnoDB;

ALTER TABLE authorities
  ADD CONSTRAINT fk_authorities_users
FOREIGN KEY (username) REFERENCES users (username);

