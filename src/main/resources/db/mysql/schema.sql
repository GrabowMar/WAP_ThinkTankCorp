CREATE TABLE IF NOT EXISTS categories (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS users (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email VARCHAR(255),
  description VARCHAR(255),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS questions (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  date DATE,
  category_id INT(4) UNSIGNED NOT NULL,
  user_id INT(4) UNSIGNED,
  INDEX(title),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (category_id) REFERENCES categories(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS answers (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  answer_id INT(4) UNSIGNED,
  answer_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (question_id) REFERENCES questions(id)
) engine=InnoDB;
