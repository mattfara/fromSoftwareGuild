Use `SuperSighting-test`;
#1 getAllSuperPersonsBySightingLocation



SELECT * from `SuperPerson`
inner join `SuperPerson_Sighting` on `SuperPerson`.`SuperPersonId` = `SuperPerson_Sighting`.`SuperPersonId`
inner join `Sighting` on `SuperPerson_Sighting`.`SightingId` = `Sighting`.`SightingId`
inner join `Location` on `Sighting`.`LocationId` = `Location`.`LocationId`
WHERE `Location`.`LocationId` = 1 ORDER BY SuperPerson.`Name`;


#2 getAllSuperPersonsByOrganization

SELECT SuperPerson.*  
FROM `SuperPerson`
INNER JOIN `SuperPerson_Organization` 
ON `SuperPerson`.`superpersonId` = `SuperPerson_Organization`.`superpersonId`
INNER JOIN `Organization` 
ON `SuperPerson_Organization`.`OrganizationId` = `Organization`.`OrganizationId`
WHERE `Organization`.`organizationId` = 1 ORDER BY SuperPerson.`Name`;

#3 getAllSuperPersonsBySighting

SELECT * from `SuperPerson`
inner join `SuperPerson_Sighting` on `SuperPerson`.`SuperPersonId` = `SuperPerson_Sighting`.`SuperPersonId`
inner join `Sighting` on `SuperPerson_Sighting`.`SightingId` = `Sighting`.`SightingId`
where `Sighting`.`sightingId` = 1 ORDER BY SuperPerson.SuperPersonId;

#4 getAllSightingsByDate

SELECT * FROM `Sighting`
WHERE `date`=20170515;

#5 getAllLocationsBySuperPersonId

SELECT `Location`.*
FROM `Location`
INNER JOIN `Sighting`
ON `Location`.`LocationId` = `Sighting`.`LocationId`
INNER JOIN `SuperPerson_Sighting`
ON `SuperPerson_Sighting`.`SightingId` = `Sighting`.`SightingId`
INNER JOIN `SuperPerson`
ON `SuperPerson_Sighting`.`SuperpersonId` = `SuperPerson`.`SuperPersonId`
WHERE `SuperPerson`.`SuperPersonId` = 5 ORDER BY Location.name;

#6 getAllOrganizationsBySuperPerson

SELECT * FROM `Organization`

inner join `SuperPerson_Organization` on `Organization`.`organizationId` = `SuperPerson_Organization`.`organizationId`

inner join `SuperPerson` on `SuperPerson_Organization`.`superpersonId` = `SuperPerson`.`superpersonId`

where `SuperPerson`.`superpersonid` = 1;