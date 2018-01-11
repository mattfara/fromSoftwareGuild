
DROP DATABASE IF EXISTS address_book;


CREATE DATABASE address_book;

use address_book;

SET foreign_key_checks = 0;
drop table Address;
drop table Person;
drop table person_address;

CREATE TABLE `address_book`.`Address` (
  `addressId` INT NOT NULL AUTO_INCREMENT,
  `street_address` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zipcode` VARCHAR(45) NULL,
  PRIMARY KEY (`addressId`));

CREATE TABLE `address_book`.`Person` (
  `personId` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`personId`));


CREATE TABLE `address_book`.`person_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NULL,
  `address_id` INT NULL,
  CHECK (person_id != null or address_id != null),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `index2` (`person_id` ASC, `address_id` ASC),
  INDEX `fk_person_address_1_idx` (`address_id` ASC),
  CONSTRAINT `fk_person_address_1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address_book`.`Address` (`addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_address_2`
    FOREIGN KEY (`person_id`)
    REFERENCES `address_book`.`Person` (`personId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

select * from Person;

select * from Address;

select * from person_address;

use address_book;
insert into Person (`first_name`, `last_name`, `phone`, `email`) values ("Matt", "Farabaugh", "631-828-4805", "radar67675656@yahoo.com");
insert into Person (`first_name`, `last_name`, `phone`, `email`) values ("Linlu", "Ding", "234-900-9168", "dinglinlu@icloud.com");
insert into Address (`street_address`, `city`, `state`, `zipcode`) values ("531 Canal Road", "Mount Sinai", "NY", "11766");
insert into Address (`street_address`, `city`, `state`, `zipcode`) values ("504 Rentschler St", "Akron", "OH", "44311");

insert into person_address (`person_id`, `address_id`) values (1, 1);
insert into person_address (`person_id`, `address_id`) values (1, 2);
insert into person_address (`person_id`, `address_id`) values (2, 2);

#get addressesbyperson
select CONCAT(Person.first_name, Person.last_name) as `Person`, Concat(street_address,city, state) as `Address`
from Person
inner join person_address pa on pa.person_id = Person.personId
inner join Address on Address.addressId = pa.address_id
where pa.person_id = 2;

#get people by address
select CONCAT(Person.first_name, Person.last_name) as `Person`, Concat(street_address,city, state) as `Address`
from Address
inner join person_address pa on pa.address_id = Address.addressId
inner join Person on pa.person_id = Person.personId
where Address.addressId = 2;

#count addresses
use address_book;
select count(*) from person_address;

#find address by last name
select Person.last_name, Person.first_name ,Address.street_address, Address.city, Address.state, Address.zipcode
from Address
inner join person_address on person_address.address_id = Address.addressId
inner join Person on Person.personId = person_address.person_id
where Person.last_name = "Farabaugh";

use address_book;
select * from Address;

#make a half nulled person_address
insert into person_address (`person_id`, `address_id`) values (null, 2);


#make new person, then update person_address entry

insert into Person (first_name, last_name, phone, email) values ("Buddy", "f", "6319285229", "slap@happy.com");

#need to use bridge table id specific to the null of interest
update person_address set person_id = 3 where person_address.id=4;

select * from person_address;

insert into person_address (`person_id`, `address_id`) values (null, 2);

select person_address.id from person_address where person_id is NULL and address_id=2;

##How to update a bridge table

/*
1. 
*/