-- Insert data into MembershipType table
INSERT INTO "MembershipType" ("membershipTypeId", "description", "digitalProducts", "physicalProducts", "maxLendings") VALUES
(1, 'Basic Membership', TRUE, FALSE, 2),
(2, 'Premium Membership', TRUE, TRUE, 5),
(3, 'Digital Only', TRUE, FALSE, 3),
(4, 'Physical Only', FALSE, TRUE, 4),
(5, 'VIP Membership', TRUE, TRUE, 10);

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

-- region: Fines
INSERT INTO
    "FineType" ("title", "amountInCents")
VALUES
    ('day-overdue-lending', 20),
    ('uncollected-reservation-pattern', 1000);


-- endregion
-- Insert a product in to database
INSERT INTO "ProductTemplate" ("productId", "name", "genre", "yearOfRelease", "description", "type", "ageClassification", "mediaType")
VALUES
    (1, '1984', 'Thriller', 1949, 'A novel by George Orwell', 'BOOK', 18, 'PHYSICAL'),
    (2, 'The Great Gatsby', 'Classic', 1925, 'A novel by F. Scott Fitzgerald', 'BOOK', 18, 'PHYSICAL'),
    (3, 'Moby-Dick', 'Classic', 1851, 'A novel by Herman Melville', 'BOOK', 18, 'PHYSICAL'),
    (4, 'To Kill a Mockingbird', 'Drama', 1960, 'A novel by Harper Lee', 'BOOK', 18, 'PHYSICAL'),
    (5, 'Pride and Prejudice', 'Romance', 1813, 'A novel by Jane Austen', 'BOOK', 18, 'PHYSICAL');


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
VALUES (1, '2024-01-01', '2024-01-15', '2024-01-05', NULL, 'RETURNED', 1, 1),
       (2, '2024-02-01', '2024-02-14', '2024-02-10', NULL, 'ACTIVE', 1, 2),
       (3, '2024-03-01', '2024-03-15', NULL, NULL, 'ACTIVE', 1, 3);

-- Insert data into Reservation table
INSERT INTO "Reservation" ("reservationId", "reservationDate", "readyForPickup", "reservationPickUpDate", "productCopyId","membershipId", "reservationStatus")
VALUES (1, '2024-12-09', 'FALSE','2024-12-16',  1, 1, 'LOANED'),
       (2, '2024-12-10', 'FALSE','2024-12-17', 1, 2, 'LOANED'),
       (3, '2024-12-11', 'FALSE','2024-12-18', 2, 3, 'LOANED'),
       (4, '2024-12-12','FALSE', '2024-12-19', 3, 4, 'LOANED'),
       (5, '2024-12-13','FALSE','2024-12-20', 4, 5, 'LOANED'),
       (6, '2024-12-14', 'FALSE','2024-12-21', 5, 6, 'LOANED'),
       (7, '2024-12-15', 'FALSE','2024-12-22', 1, 1, 'LOANED'),
       (8, '2024-12-16','FALSE', '2024-12-23', 2, 2, 'LOANED'),
       (9, '2024-12-17','FALSE', '2024-12-24', 3, 3, 'LOANED'),
       (10, '2024-12-18', 'FALSE','2024-12-25', 4, 4, 'LOANED');

