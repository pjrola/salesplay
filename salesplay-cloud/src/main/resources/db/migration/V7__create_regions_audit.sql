CREATE TABLE regions_aud (
  id bigint(20) NOT NULL,
  rev int(11) NOT NULL,
  revtype tinyint(4) DEFAULT NULL,
  enabled bit(1) DEFAULT NULL,
  end_point varchar(255) DEFAULT NULL,
  is_default bit(1) DEFAULT NULL,
  last_modified_by varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  region_code varchar(255) DEFAULT NULL,
  cloud_provider_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id,rev),
  KEY FKrf8hwikwt6a79io97ceqpnndp (rev),
  CONSTRAINT FKrf8hwikwt6a79io97ceqpnndp FOREIGN KEY (rev) REFERENCES revinfo (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;