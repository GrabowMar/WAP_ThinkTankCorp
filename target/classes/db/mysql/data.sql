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
                                                           ('How to lose weight fast?', '2023-06-05', 5, 5),
                                                           ('What are the rules of basketball?', '2023-06-06', 1, 2),
                                                           ('What is the process of photosynthesis?', '2023-06-07', 2, 1),
                                                           ('Who wrote the famous novel "Pride and Prejudice"?', '2023-06-08', 3, 3),
                                                           ('What are the features of the latest iPhone model?', '2023-06-09', 4, 4),
                                                           ('What are some healthy breakfast options?', '2023-06-10', 5, 5),
                                                           ('How to improve my golf swing?', '2023-06-11', 1, 2),
                                                           ('What is the formula for calculating kinetic energy?', '2023-06-12', 2, 1),
                                                           ('Who painted the Mona Lisa?', '2023-06-13', 3, 3),
                                                           ('What are the benefits of virtual reality technology?', '2023-06-14', 4, 4),
                                                           ('What are some tips for managing stress?', '2023-06-15', 5, 5),
                                                           ('What are the basic rules of tennis?', '2023-06-16', 1, 2),
                                                           ('What is the structure of an atom?', '2023-06-17', 2, 1),
                                                           ('Who composed the symphony "Symphonie fantastique"?', '2023-06-18', 3, 3),
                                                           ('What are the upcoming advancements in artificial intelligence?', '2023-06-19', 4, 4),
                                                           ('What are some natural remedies for common cold?', '2023-06-20', 5, 5),
                                                           ('How to improve my basketball shooting skills?', '2023-06-21', 1, 2),
                                                           ('What is the concept of natural selection?', '2023-06-22', 2, 1),
                                                           ('Who is the author of the play "Romeo and Juliet"?', '2023-06-23', 3, 3),
                                                           ('What are the latest developments in renewable energy?', '2023-06-24', 4, 4),
                                                           ('What are some ways to improve sleep quality?', '2023-06-25', 5, 5);

-- Insert some answers
INSERT INTO answers (question_id, answer_date, description) VALUES
                                                              (1, '2023-06-06', 'You need to learn the basic rules and skills of soccer.'),
                                                              (1, '2023-06-07', 'Practice passing, shooting, and dribbling to improve your soccer game.'),
                                                              (2, '2023-06-08', 'It is a scientific concept that explains the relationship between space and time.'),
                                                              (3, '2023-06-09', 'That is a subjective question, but some might say Leonardo da Vinci or Vincent van Gogh.'),
                                                              (3, '2023-06-10', 'Other notable painters include Pablo Picasso and Michelangelo.'),
                                                              (4, '2023-06-11', 'There are many new gadgets in the market, but one of them is the smartwatch.'),
                                                              (5, '2023-06-12', 'You need to eat healthy and exercise regularly.'),
                                                              (6, '2023-06-13', 'The rules of basketball include dribbling, shooting, and passing.'),
                                                              (7, '2023-06-14', 'Photosynthesis is the process by which plants convert sunlight into energy.'),
                                                              (8, '2023-06-15', 'The famous novel "Pride and Prejudice" was written by Jane Austen.'),
                                                              (9, '2023-06-16', 'The latest iPhone model features an improved camera and faster processor.'),
                                                              (10, '2023-06-17', 'Some healthy breakfast options include oatmeal, yogurt, and fruit smoothies.'),
                                                              (11, '2023-06-18', 'Improving your golf swing requires practice and proper technique.'),
                                                              (12, '2023-06-19', 'The formula for calculating kinetic energy is KE = 1/2 * mass * velocity^2.'),
                                                              (13, '2023-06-20', 'The Mona Lisa was painted by Leonardo da Vinci.'),
                                                              (14, '2023-06-21', 'Virtual reality technology offers immersive experiences and has applications in various fields.'),
                                                              (15, '2023-06-22', 'Some tips for managing stress include practicing relaxation techniques and maintaining a balanced lifestyle.'),
                                                              (16, '2023-06-23', 'The basic rules of tennis include serving the ball over the net and hitting it within the boundaries.'),
                                                              (17, '2023-06-24', 'An atom consists of a nucleus containing protons and neutrons, surrounded by electrons.'),
                                                              (18, '2023-06-25', 'The symphony "Symphonie fantastique" was composed by Hector Berlioz.'),
                                                              (19, '2023-06-26', 'Artificial intelligence is advancing in areas such as machine learning and natural language processing.'),
                                                              (20, '2023-06-27', 'Some natural remedies for common cold include drinking warm fluids and getting plenty of rest.'),
                                                              (21, '2023-06-28', 'To improve your basketball shooting skills, practice shooting from various positions and focus on technique.'),
                                                              (22, '2023-06-29', 'Natural selection is the process by which certain traits are favored for survival and reproduction in a population.'),
                                                              (23, '2023-06-30', 'The play "Romeo and Juliet" was written by William Shakespeare.'),
                                                              (24, '2023-07-01', 'Recent developments in renewable energy include advancements in solar and wind power technologies.'),
                                                              (25, '2023-07-02', 'To improve sleep quality, establish a regular sleep schedule and create a relaxing bedtime routine.');
