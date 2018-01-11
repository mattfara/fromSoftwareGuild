DROP DATABASE IF EXISTS contact_list;

CREATE DATABASE contact_list;

USE contact_list;

CREATE TABLE IF NOT EXISTS `contacts` (
 `contact_id` int(11) NOT NULL AUTO_INCREMENT,
 `first_name` varchar(50) NOT NULL,
 `last_name` varchar(50) NOT NULL,
 `company` varchar(50) NOT NULL,
 `phone` varchar(10) DEFAULT NULL,
 `email` varchar(50) NOT NULL,
 PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

CREATE TABLE IF NOT EXISTS `users` (
 `user_id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(20) NOT NULL,
 `password` varchar(100) NOT NULL,
 `enabled` tinyint(1) NOT NULL,
 PRIMARY KEY (`user_id`),
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
--
-- Dumping data for table `users`
--
INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'admin', 'password', 1),
(2, 'user', 'password', 1);
--
-- Table structure for table `authorities`
--
CREATE TABLE IF NOT EXISTS `authorities` (
 `username` varchar(20) NOT NULL,
 `authority` varchar(20) NOT NULL,
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');
--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
 
 UPDATE `contact_list`.`users`
SET
`password` = '$2a$10$fSoLLwBthDUjLjD58b1AU.qCgGYtq/WX/5hjJHkYIv5EArTnsoRUm'
WHERE `user_id` = 1;
SELECT * FROM contact_list.users;

UPDATE `contact_list`.`users`
SET
`password` = '$2a$10$fSoLLwBthDUjLjD58b1AU.qCgGYtq/WX/5hjJHkYIv5EArTnsoRUm'
WHERE `user_id` = 2;
SELECT * FROM contact_list.users;