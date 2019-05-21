CREATE TABLE IF NOT EXISTS guides (
  id BIGINT unsigned NOT NULL AUTO_INCREMENT,
  editorial_status VARCHAR(30) NOT NULL,
  visibility VARCHAR(100) NOT NULL,
  slug VARCHAR(100) NOT NULL,
  image VARCHAR(255) NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  last_modified_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uk_slug (slug),
  PRIMARY KEY (id)
) ENGINE=INNODB;

INSERT INTO guides (editorial_status, slug, visibility, image) VALUES
("review", "database-stored-messages-for-i18n-in-spring-boot", "public", "https://images.unsplash.com/photo-1544207916-df3b3a131e35?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80"),
("draft", "how-to-select-fonts-when-localizing-apps-websites", "public", "https://images.unsplash.com/photo-1544207916-df3b3a131e35?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80"),
("published", "designing-apps-in-sketch-for-ios-internationalization", "private", "https://images.unsplash.com/photo-1544207916-df3b3a131e35?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80"),
("rejected", "best-libraries-for-angular", "public", "https://images.unsplash.com/photo-1544207916-df3b3a131e35?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80");

