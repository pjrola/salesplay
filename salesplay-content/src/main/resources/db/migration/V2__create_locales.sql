CREATE TABLE locales (
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  name VARCHAR (100) NOT NULL,
  code VARCHAR(10) NOT NULL,
  is_default BIT NOT NULL DEFAULT 0,
  is_enabled BIT NOT NULL DEFAULT 0,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  last_modified_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
  constraint uk_code unique(code),
  primary key (id)
);

INSERT INTO locales (name, code, is_default, is_enabled) VALUES
('English', 'en', 1, 1),
('Japanese', 'ja', 0, 0),
('Chinese', 'zh_CN', 0, 0),
('Spanish', 'es', 0, 0),
('Hindi', 'hi', 0, 0),
('Arabic', 'ar', 0, 0),
('Russian', 'ru', 0, 0),
('French', 'fr', 0, 0);