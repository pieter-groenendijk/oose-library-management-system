CREATE TABLE "Account" (
    "accountId" BIGSERIAL PRIMARY KEY,
    "mollieCustomerId" VARCHAR(255) NULL, -- TODO: Can an account exist without being registered as a customer at mollie?
    "email" VARCHAR(50) NOT NULL UNIQUE,
    "firstName" VARCHAR(50) NOT NULL,
    "lastName" VARCHAR(50) NOT NULL,
    "dateOfBirth" DATE NOT NULL,
    "gender" CHAR(1) NOT NULL,
    "isActive" BOOLEAN NOT NULL
);

CREATE TABLE "MembershipType" (
    "membershipTypeId" BIGSERIAL PRIMARY KEY,
    "description" VARCHAR(150),
    "digitalProducts" BOOLEAN NOT NULL,
    "physicalProducts" BOOLEAN NOT NULL,
    "maxLendings" INT NOT NULL
);

CREATE TABLE "Membership" (
    "membershipId" BIGSERIAL PRIMARY KEY,
    "accountId" BIGINT NOT NULL,
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
    "lendingId" BIGINT NOT NULL,
    "notificationTaskId" BIGINT NOT NULL,
    PRIMARY KEY ("lendingId", "notificationTaskId")
);

CREATE TABLE "PaymentStatus" (
    "paymentStatusId" SMALLSERIAL NOT NULL,
    "title" VARCHAR(50) NOT NULL,
    PRIMARY KEY ("paymentStatusId"),
    UNIQUE ("title")
);

CREATE TABLE "Payment" (
    "paymentId" BIGSERIAL NOT NULL,
    "molliePaymentId" VARCHAR(255) NOT NULL,
    "amountInCents" BIGINT NOT NULL,
    "status" SMALLINT NOT NULL,
    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "paidAt" TIMESTAMP,
    PRIMARY KEY ("paymentId"),
    UNIQUE ("molliePaymentId")
);

-- region: Fine Related
CREATE TABLE "FineType" (
    "fineTypeId" BIGSERIAL NOT NULL,
    "title" VARCHAR(50) NOT NULL,
    "amountInCents" BIGINT NOT NULL,
    PRIMARY KEY ("fineTypeId"),
    UNIQUE ("title")
);

CREATE TABLE "Fine" (
    "fineId" BIGSERIAL NOT NULL,
    "fineType" SMALLINT NOT NULL,
    "account" BIGINT NOT NULL,
    "amountInCents" BIGINT NOT NULL,
    "declaredOn" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "paidBy" BIGINT,
    PRIMARY KEY ("fineId"),
    FOREIGN KEY ("fineType") REFERENCES "FineType"("fineTypeId") ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY ("account") REFERENCES "Account"("accountId") ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY ("paidBy") REFERENCES "Payment"("paymentId") ON UPDATE CASCADE ON DELETE RESTRICT
)
-- endregion

