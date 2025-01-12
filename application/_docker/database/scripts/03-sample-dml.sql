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

-- Insert data into Reservation table
INSERT INTO "Reservation" ("reservationId", "reservationDate", "isActive", "membershipId", "productCopyId")
VALUES (1, '2024-12-09', FALSE, 1, 1),
       (2, '2024-12-10', FALSE, 2, 2),
       (3, '2024-12-11', FALSE, 3, 3),
       (4, '2024-12-12', FALSE, 4, 4),
       (5, '2024-12-13', FALSE, 5, 5),
       (6, '2024-12-14', FALSE, 1, 1),
       (7, '2024-12-15', TRUE, 2, 2),
       (8, '2024-12-16', TRUE, 3, 3),
       (9, '2024-12-17', TRUE, 4, 4),
       (10, '2024-12-18', TRUE, 5, 5);

-- Insert a product in to database
INSERT INTO "ProductTemplate" ("productId", "name", "genreId", "yearOfRelease", "description", "type", "ageClassification", "mediaType")
VALUES
    (1, '1984', 1 , 1949, 'A novel by George Orwell', 'BOOK', 18, 'PHYSICAL'),
    (2, 'The Great Gatsby', 2, 1925, 'A novel by F. Scott Fitzgerald', 'BOOK', 18, 'PHYSICAL'),
    (3, 'Moby-Dick', 3, 1851, 'A novel by Herman Melville', 'BOOK', 18, 'PHYSICAL'),
    (4, 'To Kill a Mockingbird', 2, 1960, 'A novel by Harper Lee', 'BOOK', 18, 'PHYSICAL'),
    (5, 'Pride and Prejudice', 5, 1813, 'A novel by Jane Austen', 'BOOK', 18, 'PHYSICAL');


INSERT INTO "PhysicalProductTemplate" ("productId", "location", "author")
VALUES
    (1, 'B2', 'George Orwell'),
    (2, 'A1', 'F. Scott Fitzgerald'),
    (3, 'C3', 'Herman Melville'),
    (4, 'D4', 'Harper Lee'),
    (5, 'E5', 'Jane Austen');

INSERT INTO "PhysicalReadProduct" ("productId", "ISBN", "author")
VALUES
    (1, 1234567891, 'George Orwell'),
    (2, 1234567890, 'F. Scott Fitzgerald'),
    (3, 1234567892, 'Herman Melville'),
    (4, 1234567893, 'Harper Lee'),
    (5, 1234567894, 'Jane Austen');


INSERT INTO "ProductCopy" ("productCopyId", "availabilityStatus", "productId")
VALUES
    (1, 'AVAILABLE', 1),
    (2, 'AVAILABLE', 2),
    (3, 'AVAILABLE', 3),
    (4, 'AVAILABLE', 4),
    (5, 'AVAILABLE', 5);

-- Insert data into Loan table
INSERT INTO "Loan" ("loanId", "startDate", "returnBy", "returnedOn", "extendedReturnBy", "loanStatus", "membershipId", "productCopyId")
VALUES (1, '2024-01-01', '2024-01-15', NULL, NULL, 'RETURNED', 1, 1),
       (2, '2024-02-01', '2024-02-14', '2024-02-10', NULL, 'RETURNED', 1, 2),
       (3, '2024-03-01', '2024-03-15', NULL, NULL, 'RETURNED', 1, 3);
