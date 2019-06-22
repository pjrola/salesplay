CREATE TABLE templates_aud (
  id bigint(20) NOT NULL,
  rev int(11) NOT NULL,
  revtype tinyint(4) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  enabled bit(1) DEFAULT NULL,
  is_default bit(1) DEFAULT NULL,
  last_modified_by varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  version varchar(255) DEFAULT NULL,
  PRIMARY KEY (id,rev),
  KEY FKjbtcijd5u1tn7jlpfxrrmg5j9 (rev),
  CONSTRAINT FKjbtcijd5u1tn7jlpfxrrmg5j9 FOREIGN KEY (rev) REFERENCES revinfo (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;