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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO guide_translations (guide_id, locale_id, title, subtitle, overview) VALUES
(1, 1,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(1, 2,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(1, 3,"アプリとWebサイトをローカライズするときにフォントを選択する方法","新しいHello Worldガイドの書き方を学ぶ", "概観"),
(1, 4,"在Sketch for iOS国际化中设计应用程序","学习如何编写新的hello world指南", "概观"),
(1, 5,"Bibliotecas para Angular I18N","Aprende a escribir una nueva guía de hello world.", "visión general"),

(2, 1,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(2, 2,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(2, 3,"アプリとWebサイトをローカライズするときにフォントを選択する方法","新しいHello Worldガイドの書き方を学ぶ", "概観"),
(2, 4,"在Sketch for iOS国际化中设计应用程序","学习如何编写新的hello world指南", "概观"),
(2, 5,"Bibliotecas para Angular I18N","Aprende a escribir una nueva guía de hello world.", "visión general"),

(3, 1,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(3, 2,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(3, 3,"アプリとWebサイトをローカライズするときにフォントを選択する方法","新しいHello Worldガイドの書き方を学ぶ", "概観"),
(3, 4,"在Sketch for iOS国际化中设计应用程序","学习如何编写新的hello world指南", "概观"),
(3, 5,"Bibliotecas para Angular I18N","Aprende a escribir una nueva guía de hello world.", "visión general"),

(4, 1,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(4, 2,"Database-Stored Messages for I18n in Spring Boot","Learn how to write a new hello world guide", "overview"),
(4, 3,"アプリとWebサイトをローカライズするときにフォントを選択する方法","新しいHello Worldガイドの書き方を学ぶ", "概観"),
(4, 4,"在Sketch for iOS国际化中设计应用程序","学习如何编写新的hello world指南", "概观"),
(4, 5,"Bibliotecas para Angular I18N","Aprende a escribir una nueva guía de hello world.", "visión general");