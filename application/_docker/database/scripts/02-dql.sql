-- Drop views if they exist
--DROP VIEW IF EXISTS "vw_Account";
--DROP VIEW IF EXISTS "vw_MembershipType";
--DROP VIEW IF EXISTS "vw_Membership";
--DROP VIEW IF EXISTS "vw_LoanHistory";

-- View for Account table
CREATE VIEW "vw_Account" AS 
SELECT 
    "accountId",
    "email",
    "firstName",
    "lastName",
    "dateOfBirth",
    "gender",
    "isBlocked"
FROM "Account";


-- View for MembershipType table
CREATE VIEW "vw_MembershipType" AS
SELECT 
    "membershipTypeId",
	"description",
    "digitalProducts",
    "physicalProducts",
    "maxLendings"
FROM "MembershipType";


-- View for Membership table
CREATE VIEW "vw_Membership" AS
SELECT 
    "membershipId",
    "accountId",
    "membershipTypeId",
    "isBlocked",
    "startDate",
    "endDate"
FROM "Membership";

CREATE VIEW
    "vw_FineBalance"
AS
(
    SELECT
        "account",
        SUM("amountInCents") as "balanceInCents"
    FROM
        "Fine"
    WHERE
        "paidBy" is null
    GROUP BY
        "account"
);

CREATE VIEW "vw_Loans_Per_Membership" AS
SELECT
    l."membershipId",
    m."accountId",
    COUNT(l."loanId") AS "loanCount"
FROM "Loan" l
JOIN "Membership" m ON l."membershipId" = m."membershipId"
GROUP BY l."membershipId", m."accountId";

CREATE VIEW "vw_Loans_Per_Genre_Per_Membership" AS
SELECT
    l."membershipId",
    m."accountId",
    g."genreId",
	g."description",
    COUNT(l."loanId") AS "loanCount"
FROM "Loan" l
JOIN "Membership" m ON l."membershipId" = m."membershipId"
JOIN "ProductCopy" p ON l."productCopyId" = p."productCopyId"
JOIN "ProductTemplate" pt ON p."productId" = pt."productId"
JOIN "Genre" g ON pt."genreId" = g."genreId"
GROUP BY l."membershipId", m."accountId", g."genreId", g."description";
