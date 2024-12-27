-- Insert data into MembershipType table
INSERT INTO "MembershipType" ("description", "digitalProducts", "physicalProducts", "maxLendings") VALUES
('Basic Membership', TRUE, FALSE, 2),
('Premium Membership', TRUE, TRUE, 5),
('Digital Only', TRUE, FALSE, 3),
('Physical Only', FALSE, TRUE, 4),
('VIP Membership', TRUE, TRUE, 10);

-- Insert data into Account table
INSERT INTO "Account" ("email", "firstName", "lastName", "dateOfBirth", "gender", "isActive") VALUES
('john.doe@example.com', 'John', 'Doe', '1990-01-01', 'M', TRUE),
('jane.smith@example.com', 'Jane', 'Smith', '1985-03-15', 'F', TRUE),
('sam.wilson@example.com', 'Sam', 'Wilson', '1995-07-22', 'M', FALSE),
('lisa.brown@example.com', 'Lisa', 'Brown', '1982-10-05', 'F', TRUE),
('paul.jones@example.com', 'Paul', 'Jones', '2000-06-12', 'M', TRUE),
('emily.davis@example.com', 'Emily', 'Davis', '1993-08-30', 'F', TRUE),
('david.miller@example.com', 'David', 'Miller', '1998-02-17', 'M', FALSE),
('susan.clark@example.com', 'Susan', 'Clark', '1978-12-23', 'F', TRUE),
('mike.roberts@example.com', 'Mike', 'Roberts', '1991-09-14', 'M', TRUE),
('anna.jackson@example.com', 'Anna', 'Jackson', '1987-11-11', 'F', TRUE);

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