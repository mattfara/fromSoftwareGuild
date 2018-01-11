#test input data to pull onto jsp

use SuperSightings;

INSERT INTO `Address` Values
(1, '123 Fake Street', 'Akron', 'OH', '44311'),
(2, '234 Fake Street', 'Akron', 'OH', '44311'),
(3, '345 Fake Street', 'Akron', 'OH', '44311'),
(4, '456 Fake Street', 'Akron', 'OH', '44311'),
(5, '567 Fake Street', 'Akron', 'OH', '44311');

insert into Location values(1, "The Software Craftsmanship Guild", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 1);
insert into Location values(2, "Somewhere else in Akron...", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 2);
insert into Location values(3, "St. Bernard's Church", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 3);
insert into Location values(4, "Akron-Summit Library", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 4);
insert into Location values(5, "Akron U", "A swell place", "20° 45' 0'' N", "2°10’26.5″E", 5);

INSERT INTO `Sighting` Values
(1, '20151129', 3, NULL),
(2, '20170515', 1, 'things got turnt'),
(3, '20170601', 5, 'foiled a bank robbery'),
(4, '20170720', 4, 'NULL'),
(5, '20170825', 2, 'death and destruction');

INSERT INTO `Power` VALUES
(1, 'Super Strength'),
(2, 'Heat Vision'),
(3, 'Animal Magnetism'),
(4, 'Hypnosight'),
(5, 'Immaculate Taste');


INSERT INTO `SuperPerson` VALUES
(1, "The Punisher", "Avenging his family, etc", 1), 
(2, "Wolverine", "He has claws", 1), 
(3, "Spiderman", "He shoots web from his hands", 1), 
(4, "Spawn", "He's from hell", null), 
(5, "Donald Trumpus", "He builds walls", 0); 

INSERT INTO `Organization` Values
(1, 'ISIS', 'Terrorist Group', 2169739182, 0, 1),
(2, 'X-Men', 'Mutant Group', 2330989182, 1, 4),
(3, 'The Illuminati', 'Secret Society', 2169739182, 0, 2),
(4, 'Men in Black', 'FBI Group', 2169739182, 0, 3),
(5, 'Al Quaeda', 'Political Group', 2169739182, 0, 5);


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

use SuperSightings;
#getAllSuperSightings
Select s.`Date`, sp.Name, s.Description, l.`Name` AS `Location name`
from SuperPerson_Sighting sps
inner join Sighting s on sps.SightingId = s.SightingId
inner join SuperPerson sp on sp.SuperPersonId = sps.SuperPersonId
inner join Location l on l.LocationId = s.LocationId
ORDER BY s.Date DESC LIMIT 0,10;

use SuperSightings;
#getAllSuperSightings
Select sps.*
from SuperPerson_Sighting sps
inner join Sighting s on sps.SightingId = s.SightingId

ORDER BY s.Date DESC LIMIT 0,10;

use SuperSightings;
Select s.* from Sighting s
inner join SuperPerson_Sighting sps on sps.sightingId = s.sightingId
inner join SuperPerson sp on sp.SuperPersonId = sps.SuperPersonId
where sp.SuperPersonId = 2 LIMIT 0,10;

use SuperSightings;
Select p.* from Power p
inner join SuperPerson_Power spp on spp.powerId = p.powerId
inner join SuperPerson sp on sp.SuperPersonId = spp.SuperPersonId
where sp.SuperPersonId = 1 ORDER BY p.`Name` LIMIT 0,10;

















