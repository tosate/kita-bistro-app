CREATE DATABASE IF NOT EXISTS bistro;

USE bistro;

CREATE TABLE IF NOT EXISTS child_attribute (
	id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	classification VARCHAR(255),
	name VARCHAR(255));

CREATE TABLE IF NOT EXISTS kindergarten_child (
	id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	breakfast BOOLEAN NOT NULL,
	first_name VARCHAR(255),
	kita_end TIMESTAMP,
	kita_start TIMESTAMP,
	last_name VARCHAR(255),
	lunch BOOLEAN NOT NULL,
	group_id BIGINT(19) NOT NULL);

CREATE INDEX IF NOT EXISTS idx_last_name ON kindergarten_child(last_name);
CREATE INDEX IF NOT EXISTS idx_last_first_name ON kindergarten_child(last_name, first_name);

CREATE TABLE IF NOT EXISTS kindergarten_child_attributes (
	kindergarten_child_id BIGINT(19) NOT NULL,
	attributes_id BIGINT(19) NOT NULL);

CREATE TABLE IF NOT EXISTS kindergarten_group (
	id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255));

CREATE TABLE IF NOT EXISTS meal (
	id BIGINT(19) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	eaten BOOLEAN NOT NULL,
	meal_date TIMESTAMP,
	notice VARCHAR(255),
	type INTEGER(10),
	child_id BIGINT(19));

CREATE INDEX IF NOT EXISTS idx_date ON meal (meal_date);
CREATE INDEX IF NOT EXISTS idx_date_type ON meal (meal_date, type);

