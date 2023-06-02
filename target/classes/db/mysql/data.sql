-- Insert some categories
INSERT INTO categories (name) VALUES
                                ('Sports'),
                                ('Science'),
                                ('Arts'),
                                ('Technology'),
                                ('Health');

-- Insert some users
INSERT INTO users (first_name, last_name, email, description, telephone) VALUES
    ('Alice', 'Smith', 'alice.smith@example.com', 'I love to learn new things.', '123-456-7890'),
    ('Bob', 'Jones', 'bob.jones@example.com', 'I am a sports enthusiast.', '234-567-8901'),
    ('Charlie', 'Brown', 'charlie.brown@example.com', 'I am a fan of arts and music.', '345-678-9012'),
    ('David', 'Green', 'david.green@example.com', 'I am a technology geek.', '456-789-0123'),
    ('Eve', 'White', 'eve.white@example.com', 'I am a health and wellness advocate.', '567-890-1234');

-- Insert some questions
INSERT INTO questions (name, date, category_id, user_id) VALUES
    ('How to play soccer?', '2023-06-01', 1, 2),
    ('What is the theory of relativity?', '2023-06-02', 2, 1),
    ('Who is the best painter of all time?', '2023-06-03', 3, 3),
    ('What is the latest gadget in the market?', '2023-06-04', 4, 4),
    ('How to lose weight fast?', '2023-06-05', 5, 5);

-- Insert some answers
INSERT INTO answers (question_id, answer_date, description) VALUES
    (1, '2023-06-06', 'You need to learn the basic rules and skills of soccer.'),
    (2, '2023-06-07', 'It is a scientific concept that explains the relationship between space and time.'),
    (3, '2023-06-08', 'That is a subjective question, but some might say Leonardo da Vinci or Vincent van Gogh.'),
    (4, '2023-06-09', 'There are many new gadgets in the market, but one of them is the smartwatch.'),
    (5, '2023-06-10', 'You need to eat healthy and exercise regularly.');
