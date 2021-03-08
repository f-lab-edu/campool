CREATE TABLE IF NOT EXISTS `user` (
    `id` varchar(12) PRIMARY KEY,
    `password` varchar(64) NOT NULL,
    `name` varchar(20) NOT NULL,
    `email` varchar(50),
    `telephone` varchar(11)
);

CREATE TABLE IF NOT EXISTS `rental` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `status` varchar(50) NOT NULL,
    `user_id` varchar(12),
    `start_date` datetime NOT NULL,
    `end_date` datetime NOT NULL,
    `cost` int default 0,
    `create_time` timestamp,
    `location` point,
);

CREATE TABLE IF NOT EXISTS `gear` (
    `rental_id` bigint,
    `name` varchar(255) NOT NULL,
    `type` varchar(255) NOT NULL,
    `count` int default 1
);
