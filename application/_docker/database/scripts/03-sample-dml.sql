-- Insert data into MembershipType table
INSERT INTO "MembershipType" ("description", "digitalProducts", "physicalProducts", "maxLendings") VALUES
('Basic Membership', TRUE, FALSE, 2),
('Premium Membership', TRUE, TRUE, 5),
('Digital Only', TRUE, FALSE, 3),
('Physical Only', FALSE, TRUE, 4),
('VIP Membership', TRUE, TRUE, 10);

INSERT INTO "Account" ("email", "firstName", "lastName", "dateOfBirth", "gender", "isActive", "uncollectedReservations") VALUES
('john.doe@example.com', 'John', 'Doe', '1990-01-01', 'M', TRUE, 0),
('jane.smith@example.com', 'Jane', 'Smith', '1985-03-15', 'F', TRUE, 0),
('sam.wilson@example.com', 'Sam', 'Wilson', '1995-07-22', 'M', FALSE, 0),
('lisa.brown@example.com', 'Lisa', 'Brown', '1982-10-05', 'F', TRUE, 0),
('paul.jones@example.com', 'Paul', 'Jones', '2000-06-12', 'M', TRUE, 0),
('emily.davis@example.com', 'Emily', 'Davis', '1993-08-30', 'F', TRUE, 1),
('david.miller@example.com', 'David', 'Miller', '1998-02-17', 'M', FALSE, 1),
('susan.clark@example.com', 'Susan', 'Clark', '1978-12-23', 'F', TRUE, 1),
('mike.roberts@example.com', 'Mike', 'Roberts', '1991-09-14', 'M', TRUE,2),
('anna.jackson@example.com', 'Anna', 'Jackson', '1987-11-11', 'F', TRUE, 2);

-- Insert data into Membership table
INSERT INTO "Membership" ("accountId", "membershipTypeId", "isActive", "startDate", "endDate") VALUES
(1, 1, TRUE, '2023-01-01', '2023-12-31'),
(2, 2, TRUE, '2022-06-01', '2023-05-31'),
(3, 3, FALSE, '2022-09-01', '2023-08-31'),
(4, 5, TRUE, '2023-03-01', '2024-02-29'),
(5, 4, TRUE, '2023-07-01', NULL),
(6, 2, TRUE, '2023-01-15', '2024-01-14'),
(7, 1, FALSE, '2022-05-01', '2023-04-30'),
(8, 5, TRUE, '2023-10-01', NULL),
(9, 3, TRUE, '2023-11-01', '2024-10-31'),
(10, 4, TRUE, '2023-02-01', '2024-01-31'),
(10, 1, TRUE, '2023-03-01', '2024-02-29'),
(9, 2, FALSE, '2022-01-01', '2023-12-31'),
(5, 5, TRUE, '2023-08-01', '2024-07-31'),
(4, 3, TRUE, '2023-04-01', '2024-03-31'),
(2, 4, FALSE, '2022-11-01', '2023-10-31'),
(7, 2, TRUE, '2023-09-01', NULL),
(8, 1, TRUE, '2022-12-01', '2023-11-30'),
(1, 5, TRUE, '2023-06-01', '2024-05-31'),
(6, 3, TRUE, '2023-03-15', '2024-03-14'),
(3, 4, FALSE, '2022-08-01', '2023-07-31');

INSERT INTO "Genre" ("description")
VALUES ('Thriller'),
       ('Chickflick'),
       ('Fantasy'),
       ('History'),
       ('Biography');

INSERT INTO "LendingLimit" ("membershipTypeId", "genreId", "maxLendings")
VALUES (1, 1, 3),
       (1, 2, 5),
       (2, 3, 6),
       (2, 4, 7),
       (4, 1, 12),
       (4, 5, 10),
       (5, 2, 15),
       (5, 3, 15);
-- region: Fines
INSERT INTO
    "FineType" ("title", "amountInCents")
VALUES
    ('day-overdue-lending', 20),
    ('uncollected-reservation-pattern', 1000)


-- endregion


-- Inserting Products
INSERT INTO "ProductTemplate" ("name", "genre", "yearOfRelease", "description", "ageClassification", "mediaType")
VALUES
    ('1984', 1, 1949, 'A novel by George Orwell', 18, 'BOOK'),
    ('The Great Gatsby', 2, 1925, 'A novel by F. Scott Fitzgerald', 18, 'BOOK'),
    ('Moby-Dick', 3, 1851, 'A novel by Herman Melville',  18, 'BOOK'),
    ('To Kill a Mockingbird', 4, 1960, 'A novel by Harper Lee',  18, 'BOOK'),
    ('Pride and Prejudice', 2, 1813, 'A novel by Jane Austen', 18, 'BOOK'),
    ('Java Programming for Beginners', 1, 2022, 'A beginner-friendly guide to learning Java programming.', 12, 'EBOOK'),
    ('Mastering Spring Framework', 1, 2021, 'A comprehensive guide to Spring Framework for advanced developers.', 16, 'EBOOK'),
    ('The Art of Cooking', 1, 2019, 'A cookbook with gourmet recipes and techniques from around the world.', 18, 'EBOOK');


INSERT INTO "PhysicalProductTemplate" ("location", "author")
VALUES
    ('B2', 'George Orwell'),
    ('A1', 'F. Scott Fitzgerald'),
    ('C3', 'Herman Melville'),
    ('D4', 'Harper Lee'),
    ( 'E5', 'Jane Austen');

INSERT INTO "PhysicalProduct" ("ISBN", "author")
VALUES
    (1234567891, 'George Orwell'),
    (21234567890, 'F. Scott Fitzgerald'),
    (1234567892, 'Herman Melville'),
    (1234567893, 'Harper Lee'),
    (1234567894, 'Jane Austen');


INSERT INTO "ProductCopy" ("availabilityStatus")
VALUES
    ('AVAILABLE'),
    ('AVAILABLE'),
    ('AVAILABLE'),
    ('AVAILABLE'),
    ('AVAILABLE');

INSERT INTO "DigitalProductTemplate" ("language")
VALUES
    ('English'),
    ('English'),
    ('English');


INSERT INTO DigitalProduct (author, edition)
VALUES
    ('John Doe', 1),
    ('Jane Smith', 2),
    ('Gordon Ramsay', 1);
-- endregion Products
-- Insert data into Loan table
INSERT INTO "Loan" ("startDate", "returnBy", "returnedOn", "extendedReturnBy", "loanStatus", "membershipId", "productCopyId")
VALUES ('2024-01-01', '2024-01-15', NULL, NULL, 'RETURNED', 1, 1),
       ('2024-02-01', '2024-02-14', '2024-02-10', NULL, 'RETURNED', 1, 2),
       ('2024-03-01', '2024-03-15', NULL, NULL, 'RETURNED', 1, 3);

-- Insert data into Reservation table
INSERT INTO "Reservation" ("reservationDate", "isActive", "membershipId", "productCopyId")
VALUES ('2024-12-09', FALSE, 1, 1),
       ('2024-12-10', FALSE, 2, 2),
       ('2024-12-11', FALSE, 3, 3),
       ('2024-12-12', FALSE, 4, 4),
       ('2024-12-13', FALSE, 5, 5),
       ('2024-12-14', FALSE, 1, 1),
       ('2024-12-15', TRUE, 2, 2),
       ('2024-12-16', TRUE, 3, 3),
       ('2024-12-17', TRUE, 4, 4),
       ('2024-12-18', TRUE, 5, 5);
