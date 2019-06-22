CREATE TABLE revinfo (
  id int(11) NOT NULL AUTO_INCREMENT,
  timestamp bigint(20) NOT NULL,
  user varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;