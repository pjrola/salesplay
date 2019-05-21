CREATE TABLE IF NOT EXISTS guide_translations (
  guide_id BIGINT unsigned NOT NULL,
  locale_id BIGINT unsigned NOT NULL,
  title VARCHAR(100) not null,
  subtitle VARCHAR(255) not null,
  overview TEXT NOT NULL,
  KEY guide_id (guide_id),
  UNIQUE KEY uk_guide_locale_key (locale_id , guide_id),
  CONSTRAINT translations_ibfk_guides FOREIGN KEY (guide_id) REFERENCES guides (id) ON DELETE CASCADE,
  FOREIGN KEY (locale_id) REFERENCES locales(id) ON DELETE CASCADE
) ENGINE=INNODB;

INSERT INTO guide_translations (guide_id, locale_id, title, subtitle, overview) VALUES
(1, 1,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(2, 1,"How to Select Fonts When Localizing Apps & Websites","Learn how to write a new hello world guide", "overview"),
(3, 1,"Designing Apps in Sketch for iOS Internationalization","Learn how to write a new hello world guide", "overview"),
(4, 1,"Libraries for Angular I18n","Learn how to write a new hello world guide", "overview");