#
# TABLE STRUCTURE FOR: Agents
#

DROP TABLE IF EXISTS Agents;

CREATE TABLE `Agents` (
  `AgentID` int(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Last_Name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Contact_InfoID` int(11) NOT NULL,
  PRIMARY KEY (`AgentID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (1, 'Diamond', 'Veum', 1);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (2, 'Kariane', 'Pfannerstill', 1);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (3, 'Maia', 'Bauch', 1);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (4, 'Henry', 'Raynor', 0);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (5, 'Winifred', 'Rau', 1);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (6, 'Alaina', 'Metz', 1);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (7, 'Judson', 'Pacocha', 514);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (8, 'Carlo', 'Gerhold', 0);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (9, 'Allison', 'Medhurst', 535039);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (10, 'Philip', 'Schimmel', 0);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (11, 'Vivianne', 'Herzog', 857);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (12, 'Donato', 'Mertz', 2147483647);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (13, 'Andre', 'Haley', 1);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (14, 'Saul', 'Jenkins', 992906);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (15, 'Retta', 'Luettgen', 120788);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (16, 'Chad', 'Spencer', 508049);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (17, 'Stephany', 'Blanda', 0);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (18, 'Carmen', 'Hickle', 902325);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (19, 'Leonardo', 'Mertz', 1);
INSERT INTO Agents (`AgentID`, `First_Name`, `Last_Name`, `Contact_InfoID`) VALUES (20, 'Prudence', 'DuBuque', 408);


