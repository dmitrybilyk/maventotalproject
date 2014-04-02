create schema userdb;

CREATE  TABLE `userdb`.`users` (
  `userid` INT NOT NULL AUTO_INCREMENT ,
  `firstname` VARCHAR(45) NULL ,
  `lastname` VARCHAR(45) NULL ,
  `dob` DATE NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );