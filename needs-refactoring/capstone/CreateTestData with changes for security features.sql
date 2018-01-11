USE BessieBlog;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE `Blog_Hashtag`;
TRUNCATE TABLE `Blog`;
TRUNCATE TABLE `StaticPage`;
TRUNCATE TABLE `Hashtag`;
TRUNCATE TABLE `Category`;
TRUNCATE TABLE `User`;
TRUNCATE TABLE `Role`;
TRUNCATE TABLE `Image`;
TRUNCATE TABLE `Authority`;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `Category` (`Name`) VALUES ('Food');
INSERT INTO `Category` (`Name`) VALUES ('Travel');
INSERT INTO `Category` (`Name`) VALUES ('Finance');
INSERT INTO `Category` (`Name`) VALUES ('Politics');
INSERT INTO `Category` (`Name`) VALUES ('Entertainment');

INSERT INTO `Hashtag` (`Name`) VALUES ('#idontknowwhathashtagsare');
INSERT INTO `Hashtag` (`Name`) VALUES ('#theleisurelife');
INSERT INTO `Hashtag` (`Name`) VALUES ('#zombieapocalypse');
INSERT INTO `Hashtag` (`Name`) VALUES ('#ilovetoblog');
INSERT INTO `Hashtag` (`Name`) VALUES ('#nachosrock');

INSERT INTO `Role` (`Name`) VALUES ('Admin');
INSERT INTO `Role` (`Name`) VALUES ('Visitor');
INSERT INTO `Role` (`Name`) VALUES ('Contributor');

/*Added Password and Enabled values for security/login features of web app*/
INSERT INTO `User` (`Name`, `Email`, `RoleId`, `Password`, `Enabled`) VALUES ('Bessie', 'bessie@gmail.com', '1', `password`, '1');
INSERT INTO `User` (`Name`, `Email`, `RoleId`, `Password`, `Enabled`) VALUES ('Gina', 'g@gmail.com', '2', `password`, '1');
INSERT INTO `User` (`Name`, `Email`, `RoleId`, `Password`, `Enabled`) VALUES ('Nick', 'n@gmail.com', '3', `password`, '1');
INSERT INTO `User` (`Name`, `Email`, `RoleId`, `Password`, `Enabled`) VALUES ('Ken', 'k@gmail.com', '2', `password`, '1');
INSERT INTO `User` (`Name`, `Email`, `RoleId`, `Password`, `Enabled`) VALUES ('Matt', 'm@gmail.com', '3', `password`, '1');

/*Added for security/login features of web app*/
INSERT INTO Authorities (`Name`, Authority) VALUES ('Bessie', 'ROLE_ADMIN');
INSERT INTO Authorities (`Name`, Authority) VALUES ('Bessie', 'ROLE_USER');
INSERT INTO Authorities (`Name`, Authority) VALUES ('Nick', 'ROLE_USER');
INSERT INTO Authorities (`Name`, Authority) VALUES ('Matt', 'ROLE_USER');

INSERT INTO `Image` (`Caption`) VALUES ('Nachos');
INSERT INTO `Image` (`Caption`) VALUES ('Great Escape');
INSERT INTO `Image` (`Caption`) VALUES ('Flowers');
INSERT INTO `Image` (`Caption`) VALUES ('Beach Life');
INSERT INTO `Image` (`Caption`) VALUES ('Bunnies');

INSERT INTO `Blog` (`Title`,  `ExpirationDate`, `PublicationDate`,`UserId`, `CategoryId`, `Body`, `IsApproved`) VALUES ('Blog1', '2018-10-22 10:10:10', '2015-10-22 10:10:10','1', '1', 'ajfkjaf;;asl;ffa;kjd;l', '1');
INSERT INTO `Blog` (`Title`,  `ExpirationDate`, `PublicationDate`, `UserId`, `CategoryId`, `Body`) VALUES ('Blog2', '2018-10-22 10:10:10',       null,  '3', '2', 'akfjkdfjaskjf;afa');
INSERT INTO `Blog` (`Title`,  `ExpirationDate`, `PublicationDate`,`UserId`, `CategoryId`, `Body`) VALUES ('Blog3',       null,           '2015-10-22 10:10:10', '3', '3', 'aljfkldajfdj;fjaaf');
INSERT INTO `Blog` (`Title`,  `ExpirationDate`, `PublicationDate`,`UserId`, `CategoryId`, `Body`) VALUES ('Blog4', '2015-10-22 10:10:10',       null,        '5', '4', 'ajfd;kdl;a');
INSERT INTO `Blog` (`Title`,  `ExpirationDate`, `PublicationDate`,`UserId`, `CategoryId`, `Body`) VALUES ('Blog5', null, null,'5', '5', 'adlahfadk');
INSERT INTO `Blog` (`Title`, `Expiration`, `PublicationDate`, `UserId`, `CategoryId`, `Body`) VALUES ('TEST 1', null, null, 5, 5, 'This is some text. There are #hashtags in this #text. #hashtags');

INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('1', '1');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('2', '2');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('3', '3');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('4', '4');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('5', '5');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('1', '5');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('2', '4');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('3', '2');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('4', '3');
INSERT INTO `Blog_Hashtag` (`BlogId`, `HashtagId`) VALUES ('5', '1');

INSERT INTO `StaticPage` (`Title`, `CreationDate`, `ExpirationDate`, `PublicationDate`, `UserId`, `NavOrder`, `IsApproved`, `Body`, `Slug`) VALUES ('Page1', '2014-10-22 10:10:10', '2018-10-22 10:10:10', '2015-10-22 10:10:10', '1', '1', '1', 'alhdadljdklf', 'aldhlahd'); #active
INSERT INTO `StaticPage` (`Title`, `CreationDate`, `ExpirationDate`, `PublicationDate`, `UserId`, `NavOrder`, `IsApproved`, `Body`, `Slug`) VALUES ('Page2', '2014-10-22 10:10:10', '2018-10-22 10:10:10',       null,            '3', '2', '1', 'alfdjlafha', 'ahdlfah'); #pending
INSERT INTO `StaticPage` (`Title`, `CreationDate`, `ExpirationDate`, `PublicationDate`, `UserId`, `NavOrder`, `IsApproved`, `Body`, `Slug`) VALUES ('Page3', '2014-10-22 10:10:10',        null,           '2015-10-22 10:10:10', '3', '3', '1', 'alkfladkfhl', 'ahflad'); #active
INSERT INTO `StaticPage` (`Title`, `CreationDate`, `ExpirationDate`, `PublicationDate`, `UserId`, `NavOrder`, `IsApproved`, `Body`, `Slug`) VALUES ('Page4', '2014-10-22 10:10:10', '2015-10-22 10:10:10',       null,            '5', '4', '0', 'ahflfhlad', 'adhlfahd'); #expired
INSERT INTO `StaticPage` (`Title`, `CreationDate`, `ExpirationDate`, `PublicationDate`, `UserId`, `NavOrder`, `IsApproved`, `Body`, `Slug`) VALUES ('Page5', '2014-10-22 10:10:10',        null,                 null,            '5', '5', '0', 'aldjfhaldfkla', 'adlfjf'); #pending