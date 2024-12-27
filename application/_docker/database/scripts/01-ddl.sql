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
    UNIQUE ("molliePaymentId"),
    CHECK ("amountInCents" >= 0)
);

-- region: Fine Related
CREATE TABLE "FineType" (
    "fineTypeId" BIGSERIAL NOT NULL,
    "title" VARCHAR(50) NOT NULL,
    "amountInCents" BIGINT NOT NULL,
    PRIMARY KEY ("fineTypeId"),
    UNIQUE ("title"),
    CHECK ("amountInCents" >= 0)
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
    FOREIGN KEY ("paidBy") REFERENCES "Payment"("paymentId") ON UPDATE CASCADE ON DELETE RESTRICT,
    CHECK ("amountInCents" >= 0)
);
-- endregion


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
    "productId" BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "genre" VARCHAR(50) NOT NULL,
    "yearOfRelease" INT NOT NULL,
    "description" VARCHAR(250),
    "type" VARCHAR(10) NOT NULL,
    "ageClassification" INT,
    "mediaType" VARCHAR(255) NOT NULL
);

CREATE TABLE PhysicalProductTemplate (
    "productId" BIGSERIAL PRIMARY KEY,
    "location" VARCHAR(100) NOT NULL,
    "author" VARCHAR(100) NOT NULL,
    FOREIGN KEY ("productId") REFERENCES ProductTemplate ("productId")
);

CREATE TABLE PhysicalReadProduct (
    "productId" BIGSERIAL PRIMARY KEY,
    "ISBN" BIGINT,
    "author" VARCHAR(100) NOT NULL,
    FOREIGN KEY ("productId") REFERENCES ProductTemplate ("productId")
);

CREATE TABLE "ProductCopy"
(
    "productCopyId"      BIGSERIAL PRIMARY KEY,
    "availabilityStatus" VARCHAR(100) NOT NULL,
    "isDamaged"          BOOLEAN      NOT NULL,
    "productId"          BIGSERIAL      NOT NULL,
    CONSTRAINT fk_physical_product_template FOREIGN KEY ("productId") REFERENCES PhysicalProductTemplate ("productId")
);