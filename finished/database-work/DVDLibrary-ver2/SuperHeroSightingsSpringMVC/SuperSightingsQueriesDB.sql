-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SuperSighting-queries
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SuperSighting-queries` ;

-- -----------------------------------------------------
-- Schema SuperSighting-queries
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SuperSighting-queries` DEFAULT CHARACTER SET utf8 ;
USE `SuperSighting-queries` ;



-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`Address` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`Address` (
  `AddressId` INT NOT NULL AUTO_INCREMENT,
  `Street` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `State` VARCHAR(45) NOT NULL,
  `Zipcode` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`AddressId`))
ENGINE = InnoDB;

INSERT INTO `Address` Values
(1, '123 Fake Street', 'Akron', 'OH', '44311'),
(2, '234 Fake Street', 'Akron', 'OH', '44311'),
(3, '345 Fake Street', 'Akron', 'OH', '44311'),
(4, '456 Fake Street', 'Akron', 'OH', '44311'),
(5, '567 Fake Street', 'Akron', 'OH', '44311');

-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`Location` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`Location` (
  `LocationId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` TEXT NULL,
  `Latitude` VARCHAR(45) NOT NULL,
  `Longitude` VARCHAR(45) NOT NULL,
  `AddressId` INT NOT NULL,
  PRIMARY KEY (`LocationId`),
  INDEX `fk_Location_Address1_idx` (`AddressId` ASC),
  CONSTRAINT `fk_Location_Address`
    FOREIGN KEY (`AddressId`)
    REFERENCES `SuperSighting-queries`.`Address` (`AddressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into Location values(1, "The Software Craftsmanship Guild", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 1);
insert into Location values(2, "Somewhere else in Akron...", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 2);
insert into Location values(3, "St. Bernard's Church", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 3);
insert into Location values(4, "Akron-Summit Library", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 4);
insert into Location values(5, "Akron U", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 5);
-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`Sighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`Sighting` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`Sighting` (
  `SightingId` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NOT NULL,
  `LocationId` INT NOT NULL,
  `Description` TEXT NULL,
  PRIMARY KEY (`SightingId`),
  INDEX `fk_Sighting_Location1_idx` (`LocationId` ASC),
  CONSTRAINT `fk_Sighting_Location1`
    FOREIGN KEY (`LocationId`)
    REFERENCES `SuperSighting-queries`.`Location` (`LocationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `Sighting` Values
(1, '20151129', '3', NULL),
(2, '20170515', '1', 'things got turnt'),
(3, '20170601', '5', 'foiled a bank robbery'),
(4, '20170720', '4', 'NULL'),
(5, '20170825', '2', 'death and destruction');

select * from Sighting;
-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`SuperPerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`SuperPerson` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`SuperPerson` (
  `SuperPersonId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `Description` TEXT NULL,
  `isGood` TINYINT(1) NULL,
  PRIMARY KEY (`SuperPersonId`))
ENGINE = InnoDB;

INSERT INTO `SuperPerson` VALUES
(1, "The Punisher", "Avenging his family, etc", 1), 
(2, "Wolverine", "He has claws", 1), 
(3, "Spiderman", "He shoots web from his hands", 1), 
(4, "Spawn", "He's from hell", null), 
(5, "Donald Trumpus", "He builds walls", 0); 

-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`Organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`Organization` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`Organization` (
  `OrganizationId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `Description` TEXT NULL,
  `Phone` VARCHAR(10) NOT NULL,
  `isGood` TINYINT(1) NULL,
  `LocationId` INT NOT NULL,
  PRIMARY KEY (`OrganizationId`),
  INDEX `fk_Organization_Location1_idx` (`LocationId` ASC),
  CONSTRAINT `fk_Organization_Location1`
    FOREIGN KEY (`LocationId`)
    REFERENCES `SuperSighting-queries`.`Location` (`LocationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `Organization` Values
(1, 'ISIS', 'Terrorist Group', 2169739182, 0, 1),
(2, 'X-Men', 'Mutant Group', 2330989182, 1, 4),
(3, 'The Illuminati', 'Secret Society', 2169739182, 0, 2),
(4, 'Men in Black', 'FBI Group', 2169739182, 0, 3),
(5, 'Al Quaeda', 'Political Group', 2169739182, 0, 5);

-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`Power`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`Power` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`Power` (
  `PowerId` INT NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`PowerId`))
ENGINE = InnoDB;

INSERT INTO `Power` VALUES
(1, 'Super Strength'),
(2, 'Heat Vision'),
(3, 'Animal Magnetism'),
(4, 'Hypnosight'),
(5, 'Immaculate Taste');

-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`SuperPerson_Organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`SuperPerson_Organization` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`SuperPerson_Organization` (
  `SuperPerson_OrganizationId` INT NOT NULL AUTO_INCREMENT,
  `SuperPersonId` INT NOT NULL,
  `OrganizationId` INT NOT NULL,
  PRIMARY KEY (`SuperPerson_OrganizationId`),
  INDEX `fk_SuperPerson_Organization_SuperPerson1_idx` (`SuperPersonId` ASC),
  INDEX `fk_SuperPerson_Organization_Organization1_idx` (`OrganizationId` ASC),
  CONSTRAINT `fk_SuperPerson_Organization_SuperPerson1`
    FOREIGN KEY (`SuperPersonId`)
    REFERENCES `SuperSighting-queries`.`SuperPerson` (`SuperPersonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SuperPerson_Organization_Organization1`
    FOREIGN KEY (`OrganizationId`)
    REFERENCES `SuperSighting-queries`.`Organization` (`OrganizationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `SuperPerson_Organization` VALUES
(1, 2, 5),
(2, 3, 1),
(3, 5, 1),
(4, 4, 2),
(5, 3, 3),
(6, 5, 4),
(7, 1, 4),
(8, 2, 1),
(9, 1, 2),
(10, 5, 5);

-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`SuperPerson_Power`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`SuperPerson_Power` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`SuperPerson_Power` (
  `SuperPerson_PowerId` INT NOT NULL AUTO_INCREMENT,
  `SuperPersonId` INT NOT NULL,
  `PowerId` INT NOT NULL,
  PRIMARY KEY (`SuperPerson_PowerId`),
  INDEX `fk_SuperPerson_Power_SuperPerson1_idx` (`SuperPersonId` ASC),
  INDEX `fk_SuperPerson_Power_Power1_idx` (`PowerId` ASC),
  CONSTRAINT `fk_SuperPerson_Power_SuperPerson1`
    FOREIGN KEY (`SuperPersonId`)
    REFERENCES `SuperSighting-queries`.`SuperPerson` (`SuperPersonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SuperPerson_Power_Power1`
    FOREIGN KEY (`PowerId`)
    REFERENCES `SuperSighting-queries`.`Power` (`PowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `SuperPerson_Power` VALUES
(1, 2, 5),
(2, 3, 1),
(3, 5, 1),
(4, 4, 2),
(5, 3, 3),
(6, 5, 4),
(7, 1, 4),
(8, 2, 1),
(9, 1, 2),
(10, 5, 5);

-- -----------------------------------------------------
-- Table `SuperSighting-queries`.`SuperPerson_Sighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSighting-queries`.`SuperPerson_Sighting` ;

CREATE TABLE IF NOT EXISTS `SuperSighting-queries`.`SuperPerson_Sighting` (
  `SuperPerson_SightingId` INT NOT NULL,
  `SuperPersonId` INT NOT NULL,
  `SightingId` INT NOT NULL,
  PRIMARY KEY (`SuperPerson_SightingId`),
  INDEX `fk_SuperPerson_Sighting_SuperPerson1_idx` (`SuperPersonId` ASC),
  INDEX `fk_SuperPerson_Sighting_Sighting1_idx` (`SightingId` ASC),
  CONSTRAINT `fk_SuperPerson_Sighting_SuperPerson1`
    FOREIGN KEY (`SuperPersonId`)
    REFERENCES `SuperSighting-queries`.`SuperPerson` (`SuperPersonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SuperPerson_Sighting_Sighting1`
    FOREIGN KEY (`SightingId`)
    REFERENCES `SuperSighting-queries`.`Sighting` (`SightingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `SuperPerson_Sighting` VALUES
(1, 2, 5),
(2, 3, 1),
(3, 5, 1),
(4, 4, 2),
(5, 3, 3),
(6, 5, 4),
(7, 1, 4),
(8, 2, 1),
(9, 1, 2),
(10, 5, 5);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

DELETE FROM Sighting WHERE SightingId = 1;