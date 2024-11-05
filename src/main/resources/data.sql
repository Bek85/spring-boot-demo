-- CREATE TABLE IF NOT EXISTS person (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     age INT NOT NULL,
--     email VARCHAR(255) NOT NULL,
--     gender VARCHAR(255) NOT NULL,
--     name VARCHAR(255) NOT NULL,
--     password VARCHAR(255) NOT NULL
-- );
--
-- CREATE TABLE IF NOT EXISTS publication (
--     id INTEGER PRIMARY KEY AUTO_INCREMENT,
--     title VARCHAR(255) NOT NULL,
--     author VARCHAR(255) NOT NULL,
--     publication_year INTEGER NOT NULL CHECK (publication_year >= 1000),
--     isbn VARCHAR(255) NOT NULL UNIQUE,
--     publisher_code VARCHAR(255)
-- );

-- CREATE TABLE IF NOT EXISTS person_read_publications (
--     person_id INT,
--     publication_id INT,
--     PRIMARY KEY (person_id, publication_id),
--     FOREIGN KEY (person_id) REFERENCES person(id),
--     FOREIGN KEY (publication_id) REFERENCES publication(id)
-- );

INSERT INTO person (age, email, gender, name, password) VALUES
(25, 'john.doe@gmail.com', 'MALE', 'John', 'password123'),
(30, 'alice.smith@gmail.com', 'FEMALE', 'Alice', 'alice_pass123'),
(28, 'mike.brown@gmail.com', 'MALE', 'Mike', 'mike_brown_456'),
(22, 'sara.white@gmail.com', 'FEMALE', 'Sara', 'sara_pass789'),
(35, 'david.jones@gmail.com', 'MALE', 'David', 'dave_secure567'),
(26, 'emma.taylor@gmail.com', 'FEMALE', 'Emma', 'emma@12345'),
(33, 'ryan.wilson@gmail.com', 'MALE', 'Ryan', 'ryan_pw890'),
(29, 'lily.evans@gmail.com', 'FEMALE', 'Lily', 'lily_101010'),
(31, 'chris.james@gmail.com', 'MALE', 'Chris', 'chris_password'),
(27, 'maria.hall@gmail.com', 'FEMALE', 'Maria', 'maria789'),
(32, 'jason.king@gmail.com', 'MALE', 'Jason', 'jason@secure'),
(23, 'nina.walker@gmail.com', 'FEMALE', 'Nina', 'nina456'),
(34, 'adam.baker@gmail.com', 'MALE', 'Adam', 'adam_999'),
(24, 'sophia.davis@gmail.com', 'FEMALE', 'Sophia', 'sophia_123'),
(29, 'benjamin.martin@gmail.com', 'MALE', 'Benjamin', 'ben_secure'),
(26, 'grace.lee@gmail.com', 'FEMALE', 'Grace', 'gracepassword'),
(28, 'oliver.johnson@gmail.com', 'MALE', 'Oliver', 'oliver_852'),
(31, 'mia.moore@gmail.com', 'FEMALE', 'Mia', 'mia_pass_456'),
(30, 'jake.taylor@gmail.com', 'MALE', 'Jake', 'jake_pass123'),
(25, 'chloe.jackson@gmail.com', 'FEMALE', 'Chloe', 'chloe_password');

INSERT INTO publication (title, author, publication_year, isbn, publisher_code) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 1925, '9780743273565', 'Penguin Random House'),
('To Kill a Mockingbird', 'Harper Lee', 1960, '9780446310789', 'J.B. Lippincott & Co.'),
('1984', 'George Orwell', 1949, '9780451524934', 'Signet Classic'),
('Pride and Prejudice', 'Jane Austen', 1813, '9780141439518', 'Penguin Classics'),
('To the Lighthouse', 'Virginia Woolf', 1927, '9780743273565', 'Penguin Random House'),
('The Catcher in the Rye', 'J.D. Salinger', 1951, '9780316769488', 'Little, Brown and Company'),
('Brave New World', 'Aldous Huxley', 1932, '9780060850524', 'Harper Perennial Modern Classics'),
('Wuthering Heights', 'Emily Brontë', 1847, '9780141439518', 'Penguin Classics'),
('Great Expectations', 'Charles Dickens', 1861, '9780141439518', 'Penguin Classics'),
('Moby Dick', 'Herman Melville', 1851, '9780142001350', 'Penguin Classics'),
('War and Peace', 'Leo Tolstoy', 1869, '9780141439518', 'Penguin Classics'),
('The Picture of Dorian Gray', 'Oscar Wilde', 1890, '9780141439518', 'Penguin Classics'),
('The Adventures of Sherlock Holmes', 'Arthur Conan Doyle', 1892, '9780141439518', 'Penguin Classics'),
('To the Lighthouse', 'Virginia Woolf', 1927, '9780743273565', 'Penguin Random House'),
('The Old Man and the Sea', 'Ernest Hemingway', 1952, '9780141439518', 'Penguin Classics'),
('Ulysses', 'James Joyce', 1922, '9780141439518', 'Penguin Classics'),
('In Search of Lost Time', 'Marcel Proust', 1913, '9780141439518', 'Penguin Classics'),
('Don Quixote', 'Miguel de Cervantes', 1605, '9780141439518', 'Penguin Classics'),
('One Hundred Years of Solitude', 'Gabriel García Márquez', 1967, '9780141439518', 'Penguin Classics'),
('The Divine Comedy', 'Dante Alighieri', 1315, '9780141439518', 'Penguin Classics'),
('Madame Bovary', 'Gustave Flaubert', 1857, '9780141439518', 'Penguin Classics'),
('The Brothers Karamazov', 'Fyodor Dostoevsky', 1880, '9780141439518', 'Penguin Classics'),
('Lolita', 'Vladimir Nabokov', 1955, '9780141439518', 'Penguin Classics'),
('The Sound and the Fury', 'William Faulkner', 1929, '9780141439518', 'Penguin Classics'),
('The Old Man and the Sea', 'Ernest Hemingway', 1952, '9780141439518', 'Penguin Classics'),
('Ulysses', 'James Joyce', 1922, '9780141439518', 'Penguin Classics');

INSERT INTO person_read_publications (person_id, publication_id) VALUES
(1, 1),  -- John read "The Great Gatsby"
(1, 2),  -- John read "To Kill a Mockingbird"
(2, 1),  -- Alice read "The Great Gatsby"
(3, 1),  -- Mike read "The Great Gatsby"
(2, 3),  -- Alice read "1984"
(4, 2),  -- Sara read "To Kill a Mockingbird"
(5, 1),  -- David read "The Great Gatsby"
(3, 4);  -- Mike read "Pride and Prejudice"


INSERT INTO student (id ,first_name, last_name, email, age) VALUES
(1, 'John', 'Doe', 'john.doe@gmail.com', 25),
(2, 'Jane', 'Smith', 'jane.smith@gmail.com', 30),
(3, 'Alex', 'Brown', 'alex.brown@gmail.com', 28),
(4, 'Emily', 'Davis', 'emily.davis@gmail.com', 22),
(5, 'Michael', 'Wilson', 'michael.wilson@gmail.com', 35),
(6, 'Olivia', 'Moore', 'olivia.moore@gmail.com', 26),
(7, 'Daniel', 'Taylor', 'daniel.taylor@gmail.com', 33),
(8  , 'Sophia', 'Martin', 'sophia.martin@gmail.com', 29),
(9, 'James', 'Lee', 'james.lee@gmail.com', 31),
(10, 'Ava', 'Hall', 'ava.hall@gmail.com', 27),
(11, 'William', 'King', 'william.king@gmail.com', 32),
(12, 'Isabella', 'Walker', 'isabella.walker@gmail.com', 23),
(13, 'Ethan', 'Baker', 'ethan.baker@gmail.com', 24),
(14, 'Mia', 'Lewis', 'mia.lewis@gmail.com', 29),
(15, 'Benjamin', 'Martin', 'benjamin.martin@gmail.com', 26),
(16, 'Aria', 'Moore', 'aria.moore@gmail.com', 31);
