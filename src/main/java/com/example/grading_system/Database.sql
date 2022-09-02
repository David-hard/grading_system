CREATE SCHEMA `grading_system_db`;


CREATE TABLE `register_student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fName` VARCHAR(45) NOT NULL,
  `lName` VARCHAR(45) NOT NULL,
  `studentId` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `register_staff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fName` VARCHAR(45) NOT NULL,
  `lName` VARCHAR(45) NOT NULL,
  `staffId` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


INSERT INTO register_staff(fName, lName, staffId, username, password)
     VALUES('David', 'Kofi', 'KTU1234', 'admin', '1111');

CREATE TABLE `addmarks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` VARCHAR(45) NOT NULL,
  `subject` VARCHAR(45) NULL,
  `mark` VARCHAR(45) NULL,
  `grade` VARCHAR(45) NULL,
  `subject2` VARCHAR(45) NULL,
  `mark2` VARCHAR(45) NULL,
  `grade2` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


