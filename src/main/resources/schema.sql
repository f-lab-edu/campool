CREATE TABLE IF NOT EXISTS `user` (
    `id` varchar(12) PRIMARY KEY,
    `password` varchar(64) NOT NULL,
    `name` varchar(20) NOT NULL,
    `email` varchar(50),
    `telephone` varchar(11)
);

CREATE TABLE IF NOT EXISTS `admin` (
    `id` varchar(12) PRIMARY KEY,
    `password` varchar(64) NOT NULL,
    `name` varchar(20) NOT NULL,
    `email` varchar(50),
    `telephone` varchar(11)
);

CREATE TABLE IF NOT EXISTS `rental` (
    `id` bigint UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `status` varchar(50) NOT NULL,
    `user_id` varchar(12),
    `start_date` datetime NOT NULL,
    `end_date` datetime NOT NULL,
    `cost` int default 0,
    `create_time` timestamp,
    `location` point NOT NULL SRID 0,
    SPATIAL INDEX(location)
);

CREATE TABLE IF NOT EXISTS `gear` (
    `rental_id` bigint UNSIGNED,
    `name` varchar(255) NOT NULL,
    `type` bigint NOT NULL,
    `count` int default 1,
    INDEX(rental_id)
);

CREATE TABLE IF NOT EXISTS `type` (
    `id` bigint UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) UNIQUE KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS `booking` (
    `id` bigint UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `rental_id` bigint UNSIGNED NOT NULL,
    `user_id` varchar(12) NOT NULL,
    `status` varchar(50) NOT NULL,
    `start_date` datetime NOT NULL,
    `end_date` datetime NOT NULL,
    `rental_period` int NOT NULL,
    `cost` int default 0,
    `amount` int default 0,
    `create_time` timestamp
);
