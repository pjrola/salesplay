CREATE TABLE `account_roles` (
  `account_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FK6r8nxkn3hctohyllteivfr5hy` (`role_id`),
  CONSTRAINT `FK61h48dsir3h82pxbq3cwgp0ce` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FK6r8nxkn3hctohyllteivfr5hy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;