CREATE USER 'newuser'@'localhost' IDENTIFIED BY '';

CREATE DATABASE minitwitter;

GRANT ALL PRIVILEGES ON minitwitter.* to 'newuser'@'localhost';

USE minitwitter;
CREATE TABLE users (
	userId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	userName VARCHAR(100),
	loggedin INT NOT NULL DEFAULT 0
);

CREATE TABLE tweets (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	userId INT NOT NULL,
	content VARCHAR(1000),
	date DATE
);

CREATE TABLE followers (
	userId INT,
	followedById INT,
	PRIMARY KEY (userId, followedById)
);

INSERT INTO users (userId, userName) VALUES (1, "user1");
INSERT INTO users (userId, userName) VALUES (2, "user2");
INSERT INTO users (userId, userName) VALUES (3, "user3");
INSERT INTO users (userId, userName) VALUES (4, "user4");
INSERT INTO users (userId, userName) VALUES (5, "user5");

INSERT INTO followers (userId, followedById) VALUES (1,2);
INSERT INTO followers (userId, followedById) VALUES (2,3);
INSERT INTO followers (userId, followedById) VALUES (3,4);
INSERT INTO followers (userId, followedById) VALUES (4,5);
INSERT INTO followers (userId, followedById) VALUES (5,1);

INSERT INTO tweets (id, userId, content, date) VALUES (1, 1, "tweet1", sysdate());
INSERT INTO tweets (id, userId, content, date) VALUES (2, 2, "tweet2", sysdate());
INSERT INTO tweets (id, userId, content, date) VALUES (3, 3, "tweet3", sysdate());
INSERT INTO tweets (id, userId, content, date) VALUES (4, 4, "tweet4", sysdate());
INSERT INTO tweets (id, userId, content, date) VALUES (5, 5, "tweet5", sysdate());