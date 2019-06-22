CREATE TABLE instances_aud (
  id bigint(20) NOT NULL,
  rev int(11) NOT NULL,
  revtype tinyint(4) DEFAULT NULL,
  last_modified_by varchar(255) DEFAULT NULL,
  last_started_time datetime DEFAULT NULL,
  locked bit(1) DEFAULT NULL,
  owner varchar(255) DEFAULT NULL,
  remote_id varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  url varchar(255) DEFAULT NULL,
  template_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id,rev),
  KEY FKlkh9bg79q9t745a7ahtml1j7 (rev),
  CONSTRAINT FKlkh9bg79q9t745a7ahtml1j7 FOREIGN KEY (rev) REFERENCES revinfo (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;