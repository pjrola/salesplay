CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VERIFY_USER` (`account_id`),
  CONSTRAINT `FK_VERIFY_USER` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;