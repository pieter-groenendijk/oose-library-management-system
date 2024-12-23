-- Drop tables if they exist, cascading dependencies
--DROP TABLE IF EXISTS "Membership" CASCADE;
--DROP TABLE IF EXISTS "MembershipType" CASCADE;
--DROP TABLE IF EXISTS "Account" CASCADE;

-- Create Account table
CREATE TABLE "Account" (
    "accountId" BIGSERIAL PRIMARY KEY,
    "email" VARCHAR(50) NOT NULL UNIQUE,
    "firstName" VARCHAR(50) NOT NULL,
    "lastName" VARCHAR(50) NOT NULL,
    "dateOfBirth" DATE NOT NULL,
    "gender" CHAR(1) NOT NULL,
    "isActive" BOOLEAN NOT NULL
);

-- Create MembershipType table
CREATE TABLE "MembershipType" (
    "membershipTypeId" BIGSERIAL PRIMARY KEY,
    "description" VARCHAR(150),
    "digitalProducts" BOOLEAN NOT NULL,
    "physicalProducts" BOOLEAN NOT NULL,
    "maxLendings" INT NOT NULL
);

-- Create Membership table
CREATE TABLE "Membership" (
    "membershipId" BIGSERIAL PRIMARY KEY,
    "accountId" BIGSERIAL NOT NULL,
    "membershipTypeId" BIGINT NOT NULL,
    "isActive" BOOLEAN NOT NULL,
    "startDate" DATE NOT NULL,
    "endDate" DATE,
    "isBlocked" BOOLEAN DEFAULT FALSE,
    FOREIGN KEY ("accountId") REFERENCES "Account" ("accountId") ON DELETE CASCADE,
    FOREIGN KEY ("membershipTypeId") REFERENCES "MembershipType" ("membershipTypeId") ON DELETE CASCADE
);

CREATE TABLE "Lending" (
    "lendingId" BIGSERIAL PRIMARY KEY,
    "mustReturnBy" DATE NOT NULL
);

CREATE TABLE "NotificationTask" (
    "notificationTaskId" BIGSERIAL PRIMARY KEY,
    "account" BIGINT NOT NULL,
    "title" VARCHAR(100) NOT NULL,
    "message" TEXT NOT NULL,
    "scheduledAt" TIMESTAMP NOT NULL, -- TODO: Add index; is used quite a bit
    "sendStrategy" VARCHAR(20) NOT NULL,
    "status" VARCHAR(20) NOT NULL,
    FOREIGN KEY ("account") REFERENCES "Account"("accountId") ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE "LendingAssociatedNotificationTask" (
                                                     "lendingId" BIGSERIAL NOT NULL,
    "notificationTaskId" BIGINT NOT NULL,
    PRIMARY KEY ("lendingId", "notificationTaskId")
);
CREATE TABLE Loan
(
    "loanId" BIGSERIAL PRIMARY KEY,
    "startDate"     DATE   NOT NULL,
    "returnBy"      DATE,
    "returnedOn"    DATE,
    "loanStatus"    VARCHAR(50),
    "membershipId"  BIGSERIAL NOT NULL,
    "productCopyId" BIGSERIAL NOT NULL,
    FOREIGN KEY ("membershipId") REFERENCES "Membership" ("membershipId") ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY ("productCopyId") REFERENCES "ProductCopy" ("productCopyId") ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE "Reservation"
(
    "reservationId"   BIGSERIAL PRIMARY KEY,
    "membershipId"    BIGSERIAL  NOT NULL,
    "productCopyId"   BIGSERIAL  NOT NULL,
    "reservationDate" DATE    NOT NULL,
    "readyForPickUp"  BOOLEAN NOT NULL,
    FOREIGN KEY ("membershipId") REFERENCES "Membership" ("membershipId") ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY ("productCopyId") REFERENCES "ProductCopy" ("productCopyId") ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ProductTemplate (
                                 productId BIGSERIAL PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL,
                                 genre VARCHAR(50) NOT NULL,
                                 yearOfRelease INT NOT NULL,
                                 description VARCHAR(250),
                                 type VARCHAR(10) NOT NULL,
                                 ageClassification INT,
                                 mediaType VARCHAR(255) NOT NULL
);

CREATE TABLE PhysicalProductTemplate (
                                         productId BIGSERIAL PRIMARY KEY,
                                         location VARCHAR(100) NOT NULL,
                                         author VARCHAR(100) NOT NULL
);

CREATE TABLE PhysicalReadProduct (
                                     productId BIGSERIAL PRIMARY KEY,
                                     ISBN BIGINT,
                                     author VARCHAR(100) NOT NULL
);

CREATE TABLE "ProductCopy"
(
    "productCopyId"      BIGSERIAL PRIMARY KEY,
    "availabilityStatus" VARCHAR(100) NOT NULL,
    "isDamaged"          BOOLEAN      NOT NULL,
    "productId"          BIGSERIAL      NOT NULL,
    CONSTRAINT fk_physical_product_template FOREIGN KEY ("productId") REFERENCES PhysicalProductTemplate (productId)
);