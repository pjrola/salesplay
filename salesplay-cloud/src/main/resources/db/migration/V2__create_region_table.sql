-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE regions (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  cloud_provider_id bigint(20) NOT NULL,
  name varchar(100) DEFAULT NULL,
  region_code varchar(100) NOT NULL,
  end_point varchar(100) NOT NULL,
  enabled bit(1) DEFAULT FALSE,
  is_default bit(1) DEFAULT FALSE,
  created_at datetime DEFAULT CURRENT_TIMESTAMP,
  last_modified_by varchar(100) DEFAULT NULL,
  last_modified_at datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY FK_cloud_provider (cloud_provider_id),
  UNIQUE KEY UK_region_end_point (end_point),
  CONSTRAINT FK_CLOUD_PROVIDER FOREIGN KEY (cloud_provider_id) REFERENCES cloud_providers (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO regions(cloud_provider_id ,name, region_code, end_point ,enabled, is_default ,created_at, last_modified_at)
VALUES(2, 'US East (N. Virginia)','us-east-1', 'rds.us-east-2.amazonaws.com',true, true,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);