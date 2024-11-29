-- Drop views if they exist
DROP VIEW IF EXISTS "vw_Account";
DROP VIEW IF EXISTS "vw_MembershipType";
DROP VIEW IF EXISTS "vw_Membership";
GO;

-- View for Account table
CREATE VIEW vw_Account AS 
SELECT 
    "accountId",
    "email",
    "firstName",
    "lastName",
    "dateOfBirth",
    "gender",
    "isActive"
FROM "Account";
GO;

-- View for MembershipType table
CREATE VIEW "vw_MembershipType" AS
SELECT 
    "membershipTypeId",
    "digitalProducts",
    "physicalProducts",
    "maxLendings"
FROM "MembershipType";
GO;

-- View for Membership table
CREATE VIEW "vw_Membership" AS
SELECT 
    "membershipId",
    "description",
    "accountId",
    "membershipTypeId",
    "isActive",
    "startDate",
    "endDate"
FROM "Membership";
GO;
