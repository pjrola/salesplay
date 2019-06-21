-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE instances (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  remote_id varchar(100) DEFAULT NULL,
  template_id bigint(20) NOT NULL,
  state varchar(100) NOT NULL,
  owner varchar(100) NOT NULL,
  url varchar(100) DEFAULT NULL,
  locked bit(1) DEFAULT FALSE,
  created_at datetime DEFAULT CURRENT_TIMESTAMP,
  last_started_time datetime DEFAULT NULL,
  last_modified_at datetime DEFAULT CURRENT_TIMESTAMP,
  last_modified_by varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_template (template_id),
  CONSTRAINT FK_INSTANCES_TEMPLATE FOREIGN KEY (template_id) REFERENCES templates (id),
  UNIQUE KEY UK_owner (owner)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- INSERT INTO instances(template_id ,remote_id,state, owner, url, locked,created_at, last_modified_at)
-- VALUES(1, 'i-05475f95534e9ed6f', 'running', 'prola', 'ec2-198-51-100-1.compute-1.amazonaws.com' ,false ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);