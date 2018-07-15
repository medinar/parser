-- [1]
CREATE DATABASE `report`;

-- [2]
USE report;

-- [3]
CREATE TABLE `access_log` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `log_date` timestamp,
  `ip_address` varchar(32),
  `request` varchar(32),
  `status` varchar(8),
  `user_agent` varchar(256),
  PRIMARY KEY (`oid`),
  KEY `LOG_DATE_IDX` (`log_date`)
);

-- [4]
CREATE TABLE `blocked_ip` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(32),
  `reason` varchar(256),
  PRIMARY KEY (`oid`)
);