CREATE TABLE IF NOT EXISTS `user` (
    `id` varchar(12) PRIMARY KEY NOT NULL,
    `password` varchar(64) NOT NULL,
    `name` varchar(20) NOT NULL,
    `email` varchar(50),
    `telephone` varchar(11)
);