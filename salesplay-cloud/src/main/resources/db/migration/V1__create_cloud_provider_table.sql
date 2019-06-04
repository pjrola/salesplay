-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE `cloud_providers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(100) NOT NULL,
  `unique_id` varchar(100) NOT NULL,
  `enabled` bit(1) DEFAULT FALSE,
  `last_modified_by` varchar(100) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cloud_provider` (`brand`,`unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO cloud_providers(brand ,unique_id ,enabled ,created_at, last_modified_at)
VALUES('Google','googleCloud',true ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO cloud_providers(brand ,unique_id ,enabled ,created_at, last_modified_at)
VALUES('Amazon','amazonWebServices',true ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO cloud_providers(brand ,unique_id ,enabled ,created_at, last_modified_at)
VALUES('Microsoft','azureCloud',true ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);