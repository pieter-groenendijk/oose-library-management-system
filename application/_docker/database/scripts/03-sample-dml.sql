-- Insert data into MembershipType table
INSERT INTO "MembershipType" ("description", "digitalProducts", "physicalProducts", "maxLendings", "isDeleted") VALUES
('Basic Membership', TRUE, FALSE, 2, FALSE),
('Premium Membership', TRUE, TRUE, 5, FALSE),
('Digital Only', TRUE, FALSE, 3, FALSE),
('Physical Only', FALSE, TRUE, 4, FALSE),
('VIP Membership', TRUE, TRUE, 10, FALSE);

INSERT INTO "Account" ("email", "firstName", "lastName", "dateOfBirth", "gender", "isActive", "uncollectedReservations", "isDeleted") VALUES
('john.doe@example.com', 'John', 'Doe', '1990-01-01', 'M', TRUE, 0, FALSE),
('jane.smith@example.com', 'Jane', 'Smith', '1985-03-15', 'F', TRUE, 0, FALSE),
('sam.wilson@example.com', 'Sam', 'Wilson', '1995-07-22', 'M', FALSE, 0, FALSE),
('lisa.brown@example.com', 'Lisa', 'Brown', '1982-10-05', 'F', TRUE, 0, FALSE),
('paul.jones@example.com', 'Paul', 'Jones', '2000-06-12', 'M', TRUE, 0, FALSE),
('emily.davis@example.com', 'Emily', 'Davis', '1993-08-30', 'F', TRUE, 1, FALSE),
('david.miller@example.com', 'David', 'Miller', '1998-02-17', 'M', FALSE, 1, FALSE),
('susan.clark@example.com', 'Susan', 'Clark', '1978-12-23', 'F', TRUE, 1, FALSE),
('mike.roberts@example.com', 'Mike', 'Roberts', '1991-09-14', 'M', TRUE,2, FALSE),
('anna.jackson@example.com', 'Anna', 'Jackson', '1987-11-11', 'F', TRUE, 2, FALSE);

-- Insert data into Membership table
INSERT INTO "Membership" ("accountId", "membershipTypeId", "isActive", "startDate", "endDate", "isDeleted") VALUES
(1, 1, TRUE, '2023-01-01', '2023-12-31', FALSE),
(2, 2, TRUE, '2022-06-01', '2023-05-31', FALSE),
(3, 3, FALSE, '2022-09-01', '2023-08-31', FALSE),
(4, 5, TRUE, '2023-03-01', '2024-02-29', FALSE),
(5, 4, TRUE, '2023-07-01', NULL, FALSE),
(6, 2, TRUE, '2023-01-15', '2024-01-14', FALSE),
(7, 1, FALSE, '2022-05-01', '2023-04-30', FALSE),
(8, 5, TRUE, '2023-10-01', NULL, FALSE),
(9, 3, TRUE, '2023-11-01', '2024-10-31', FALSE),
(10, 4, TRUE, '2023-02-01', '2024-01-31', FALSE),
(10, 1, TRUE, '2023-03-01', '2024-02-29', FALSE),
(9, 2, FALSE, '2022-01-01', '2023-12-31', FALSE),
(5, 5, TRUE, '2023-08-01', '2024-07-31', FALSE),
(4, 3, TRUE, '2023-04-01', '2024-03-31', FALSE),
(2, 4, FALSE, '2022-11-01', '2023-10-31', FALSE),
(7, 2, TRUE, '2023-09-01', NULL, FALSE),
(8, 1, TRUE, '2022-12-01', '2023-11-30', FALSE),
(1, 5, TRUE, '2023-06-01', '2024-05-31', FALSE),
(6, 3, TRUE, '2023-03-15', '2024-03-14', FALSE),
(3, 4, FALSE, '2022-08-01', '2023-07-31', FALSE);

INSERT INTO "Genre" ("description") VALUES
('Thriller'),
('Chickflick'),
('Fantasy'),
('History'),
('Biography');

INSERT INTO "LendingLimit" ("membershipTypeId", "genreId", "maxLendings") VALUES
(1, 1, 3),
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
    ('uncollected-reservation-pattern', 1000);


-- endregion

-- Insert a product in to database
INSERT INTO "ProductTemplate" ("name", "genreId", "yearOfRelease", "description", "type", "ageClassification", "mediaType")
VALUES
    ('1984', 1, 1949, 'A novel by George Orwell', 'BOOK', 18, 'BOOK'),
    ('The Great Gatsby', 2, 1925, 'A novel by F. Scott Fitzgerald', 'BOOK', 18, 'BOOK'),
    ('Moby-Dick', 3, 1851, 'A novel by Herman Melville', 'BOOK', 18, 'BOOK'),
    ('To Kill a Mockingbird', 4, 1960, 'A novel by Harper Lee', 'BOOK', 18, 'BOOK'),
    ('Pride and Prejudice', 2, 1813, 'A novel by Jane Austen', 'BOOK', 18, 'BOOK');



INSERT INTO "PhysicalProductTemplate" ("location", "author")
VALUES
    ('B2', 'George Orwell'),
    ('A1', 'F. Scott Fitzgerald'),
    ('C3', 'Herman Melville'),
    ('D4', 'Harper Lee'),
    ('E5', 'Jane Austen');

INSERT INTO "PhysicalProduct" ("ISBN", "author")
VALUES
    (1234567891, 'George Orwell'),
    (1234567890, 'F. Scott Fitzgerald'),
    (1234567892, 'Herman Melville'),
    (1234567893, 'Harper Lee'),
    (1234567894, 'Jane Austen');


INSERT INTO "ProductCopy" ("productId", "availabilityStatus")
VALUES
    (1, 'AVAILABLE'),
    (2, 'AVAILABLE'),
    (3, 'AVAILABLE'),
    (4, 'AVAILABLE'),
    (5, 'AVAILABLE');


-- Insert data into Reservation table
INSERT INTO "Reservation" ("reservationDate", "readyForPickup", "reservationPickUpDate", "productCopyId","membershipId", "reservationStatus")
VALUES ('2024-12-09', 'FALSE','2024-12-16',  1, 1, 'LOANED'),
       ('2024-12-10', 'FALSE','2024-12-17', 1, 2, 'LOANED'),
       ('2024-12-11', 'FALSE','2024-12-18', 2, 3, 'LOANED'),
       ('2024-12-12','FALSE', '2024-12-19', 3, 4, 'LOANED'),
       ('2024-12-13','FALSE','2024-12-20', 4, 5, 'LOANED'),
       ('2024-12-14', 'FALSE','2024-12-21', 5, 6, 'LOANED'),
       ('2024-12-15', 'FALSE','2024-12-22', 1, 1, 'LOANED'),
       ('2024-12-16','FALSE', '2024-12-23', 2, 2, 'LOANED'),
       ('2024-12-17','FALSE', '2024-12-24', 3, 3, 'LOANED'),
       ('2024-12-18', 'FALSE','2024-12-25', 4, 4, 'LOANED');


-- Insert data into Loan table
INSERT INTO "Loan" ("startDate", "returnBy", "returnedOn", "extendedReturnBy", "loanStatus", "membershipId", "productCopyId")
VALUES ('2024-01-01', '2024-01-15', '2024-01-12' , NULL, 'RETURNED', 1, 1),
       ('2024-02-01', '2024-02-14', '2024-02-10', NULL, 'RETURNED', 1, 2),
       ('2024-02-01', '2024-1-15', '2024-01-12', NULL, 'RETURNED', 1, 3);

