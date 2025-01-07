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
    ('uncollected-reservation-pattern', 1000)


-- endregion

-- Insert data into Loan table
INSERT INTO "Loan" ("loanId", "startDate", "returnBy", "returnedOn", "loanStatus", "membershipId", "productCopyId")
VALUES (1, '2024-01-01', '2024-01-15', NULL, 'Borrowed', 1, 1),
       (2, '2024-02-01', '2024-02-14', '2024-02-10', 'Returned', 1, 2),
       (3, '2024-03-01', '2024-03-15', NULL, 'Overdue', 1, 3);

-- Insert data into Reservation table
INSERT INTO "Reservation" ("reservationId", "reservationDate", "isActive", "membershipId", "productCopyId")
VALUES (1, '2024-12-09', FALSE, 1, 1),
       (2, '2024-12-10', FALSE, 2, 2),
       (3, '2024-12-11', FALSE, 3, 3),
       (4, '2024-12-12', FALSE, 4, 4),
       (5, '2024-12-13', FALSE, 5, 5),
       (6, '2024-12-14', FALSE, 6, 6),
       (7, '2024-12-15', TRUE, 7, 7),
       (8, '2024-12-16', TRUE, 8, 8),
       (9, '2024-12-17', TRUE, 1, 9),
       (10, '2024-12-18', TRUE, 1, 10);

-- Insert a product in to database
INSERT INTO "ProductTemplate" ("productId", "name", "genre", "yearOfRelease", "description", "type", "ageClassification", "mediaType")
VALUES (2, 'The Great Gatsby', 'Classic', 1925, 'A novel by F. Scott Fitzgerald', 'BOOK', 18, 'PHYSICAL');

INSERT INTO "PhysicalProductTemplate" ("productId", "location", "author")
VALUES
    (2, 'A1', 'F. Scott Fitzgerald');

INSERT INTO "PhysicalReadProduct" ("productId", "ISBN", "author")
VALUES
    (2, 1234567890, 'F. Scott Fitzgerald');

INSERT INTO "ProductCopy" ("productId", "availabilityStatus", "isDamaged")
VALUES
    (2, 'Available', false);
