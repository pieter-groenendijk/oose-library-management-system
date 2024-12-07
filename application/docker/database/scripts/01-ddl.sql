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
    "accountId" BIGINT NOT NULL,
    "membershipTypeId" BIGINT NOT NULL,
    "isActive" BOOLEAN NOT NULL,
    "startDate" DATE NOT NULL,
    "endDate" DATE,
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
    FOREIGN KEY ("account") REFERENCES "Account"("accountId") ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE "LendingAssociatedNotificationTask" (
    "lendingId" BIGINT NOT NULL,
    "notificationTaskId" BIGINT NOT NULL,
    PRIMARY KEY ("lendingId", "notificationTaskId")
);