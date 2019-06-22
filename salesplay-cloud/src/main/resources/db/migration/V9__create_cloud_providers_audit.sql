CREATE TABLE cloud_providers_aud (
  id bigint(20) NOT NULL,
  rev int(11) NOT NULL,
  revtype tinyint(4) DEFAULT NULL,
  brand varchar(255) DEFAULT NULL,
  enabled bit(1) DEFAULT NULL,
  last_modified_by varchar(255) DEFAULT NULL,
  unique_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id,rev),
  KEY FKay767f98nqmsgqw0hh07ys4cu (rev),
  CONSTRAINT FKay767f98nqmsgqw0hh07ys4cu FOREIGN KEY (rev) REFERENCES revinfo (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;