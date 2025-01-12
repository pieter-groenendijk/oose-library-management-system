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
    "isActive"
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
    "isActive",
    "startDate",
    "endDate",
    "isBlocked"
FROM "Membership";

-- View for LoanHistory
CREATE VIEW "vw_LoanHistory" AS
    SELECT
    "l.loanId",
    "l.membershipId",
    "l.returnDate",
    "p.productCopyId",
    "pp.author",
    "pp.isbn",
    "t.mediaType",
    "t.name",
    "t.yearOfRelease"
    FROM "Loan l"
    JOIN "ProductCopy p" ON "l.productCopyId" = "p.productCopyId"
    JOIN "PhysicalProduct pp" ON "productId" = "pp.productId"
    JOIN "ProductTemplate t" ON "p.productId" = "t.productId"
    WHERE "l.loanStatus" = 'RETURNED';