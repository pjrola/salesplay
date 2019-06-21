-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE templates (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  cloud_provider_id bigint(20) NOT NULL,
  name varchar(100) NOT NULL,
  version varchar(100) NOT NULL,
  description varchar(255) NOT NULL,
  enabled bit(1) DEFAULT FALSE,
  is_default bit(1) DEFAULT FALSE,
  last_modified_by varchar(100) DEFAULT NULL,
  created_at datetime DEFAULT CURRENT_TIMESTAMP,
  last_modified_at datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY FK_template_cloud_provider (cloud_provider_id),
  CONSTRAINT FK_TEMPLATE_CLOUD_PROVIDER FOREIGN KEY (cloud_provider_id) REFERENCES cloud_providers (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO templates(cloud_provider_id ,name, version, description, enabled, is_default ,created_at, last_modified_at)
VALUES(2, 'ThingWorx Foundation', '8.3', 'test template',true, true,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO templates(cloud_provider_id ,name, version, description, enabled, is_default ,created_at, last_modified_at)
VALUES(2, 'ThingWorx Foundation', '8.4', 'test template',true, true,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);