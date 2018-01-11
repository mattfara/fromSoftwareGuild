DROP DATABASE IF EXISTS BessieBlogTest;
CREATE DATABASE IF NOT EXISTS BessieBlogTest;

USE BessieBlogTest;

CREATE TABLE IF NOT EXISTS Category (
	Id INT NOT NULL auto_increment,
    `Name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS Role (
	Id INT NOT NULL auto_increment,
    `Name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (Id)
);

/*Added Password and Enabled fields for use with security/login features of web app*/
CREATE TABLE IF NOT EXISTS `User` (
	Id INT NOT NULL auto_increment,
    `Name` VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    RoleId INT NOT NULL,
    `Password` VARCHAR(100) NOT NULL,
    Enabled TINYINT(1) NOT NULL, 
    PRIMARY KEY (Id),
    CONSTRAINT fk_User_RoleId
	FOREIGN KEY (RoleId) REFERENCES Role (Id)
);

/*Added table for use with security/login features of web app*/
CREATE TABLE IF NOT EXISTS Authorities (
	`Name` VARCHAR(50) NOT NULL,
    Authority VARCHAR(20) NOT NULL,
    KEY `Name` (`User`)
);

ALTER TABLE Authorities
ADD CONSTRAINT authorities_ibfk_1 FOREIGN KEY(`Name`) REFERENCES `User` (`Name`);

CREATE TABLE IF NOT EXISTS Hashtag (
	Id INT NOT NULL auto_increment,
    `Name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (Id)
);

ALTER TABLE `Hashtag` 
ADD UNIQUE INDEX `Name_UNIQUE` (`Name` ASC);

CREATE TABLE IF NOT EXISTS Blog (
    Id INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    CreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ExpirationDate TIMESTAMP NULL DEFAULT NULL,
    PublicationDate TIMESTAMP NULL DEFAULT NULL,
    UserId INT NOT NULL,
    CategoryId INT NOT NULL,
    Body TEXT NOT NULL,
    IsApproved BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (Id),
    CONSTRAINT fk_Blog_UserId
	FOREIGN KEY (UserId) REFERENCES `User` (Id),
    CONSTRAINT fk_Blog_CategoryId
	FOREIGN KEY (CategoryId) REFERENCES Category (Id)
);

CREATE TABLE IF NOT EXISTS Blog_Hashtag (
	Id INT NOT NULL auto_increment,
    BlogId INT NOT NULL,
    HashtagId INT NOT NULL,
    PRIMARY KEY (Id),
	CONSTRAINT fk_Blog_Hashtag_BlogId
	FOREIGN KEY (BlogId) REFERENCES Blog (Id),
    CONSTRAINT fk_Blog_Hashtag_HashtagId
	FOREIGN KEY (HashtagId) REFERENCES Hashtag (Id)
);

ALTER TABLE `Blog_Hashtag` 
ADD UNIQUE INDEX `fk_Blog_Hashtag_UNIQUE` (`BlogId` ASC, `HashtagId` ASC);

CREATE TABLE IF NOT EXISTS StaticPage (
    Id INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    CreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ExpirationDate TIMESTAMP NULL DEFAULT NULL,
    PublicationDate TIMESTAMP NULL DEFAULT NULL,
    UserId INT NOT NULL,
    NavOrder INT NOT NULL,
    IsApproved BOOLEAN DEFAULT FALSE,
    Body TEXT NOT NULL,
    Slug VARCHAR(150) NOT NULL,
    PRIMARY KEY (Id),
    CONSTRAINT fk_StaticPage_UserId
	FOREIGN KEY (UserId) REFERENCES `User` (Id)
);

CREATE TABLE IF NOT EXISTS Image(
	Id INT NOT NULL auto_increment,
    Caption VARCHAR(60) NOT NULL,
    Image LONGBLOB,
    AddedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (Id)
);
