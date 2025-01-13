CREATE TABLE "Account" (
    "accountId" BIGSERIAL PRIMARY KEY,
    "mollieCustomerId" VARCHAR(255) NULL, -- TODO: Can an account exist without being registered as a customer at mollie?
    "email" VARCHAR(50) NOT NULL UNIQUE,
    "firstName" VARCHAR(50) NOT NULL,
    "lastName" VARCHAR(50) NOT NULL,
    "dateOfBirth" DATE NOT NULL,
    "gender" CHAR(1) NOT NULL,
    "isActive" BOOLEAN NOT NULL,
    "uncollectedReservations" INT DEFAULT 0,
    "isDeleted" BOOLEAN NOT NULL
);

CREATE TABLE "MembershipType" (
    "membershipTypeId" BIGSERIAL PRIMARY KEY,
    "description" VARCHAR(150),
    "digitalProducts" BOOLEAN NOT NULL,
    "physicalProducts" BOOLEAN NOT NULL,
    "maxLendings" INT NOT NULL,
    "isDeleted" BOOLEAN NOT NULL
);

CREATE TABLE "Membership" (
    "membershipId" BIGSERIAL PRIMARY KEY,
    "accountId" BIGSERIAL NOT NULL,
    "membershipTypeId" BIGINT NOT NULL,
    "isActive" BOOLEAN NOT NULL,
    "startDate" DATE NOT NULL,
    "endDate" DATE,
    "isDeleted" BOOLEAN NOT NULL,
    "isBlocked" BOOLEAN DEFAULT FALSE,
    FOREIGN KEY ("accountId") REFERENCES "Account" ("accountId") ON DELETE CASCADE,
    FOREIGN KEY ("membershipTypeId") REFERENCES "MembershipType" ("membershipTypeId") ON DELETE CASCADE
);

CREATE TABLE "Genre" (
    "genreId" BIGSERIAL PRIMARY KEY,
    "description" VARCHAR(150)
);

CREATE TABLE "LendingLimit" (
    "lendingLimitId" BIGSERIAL PRIMARY KEY,
    "membershipTypeId" BIGINT NOT NULL,
    "genreId" BIGINT NOT NULL,
    "maxLendings" INT NOT NULL,
    FOREIGN KEY ("membershipTypeId") REFERENCES "MembershipType" ("membershipTypeId") ON DELETE CASCADE,
    FOREIGN KEY ("genreId") REFERENCES "Genre" ("genreId") ON DELETE CASCADE
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

CREATE TABLE "ProductTemplate" (
    "productId" BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "genreId" BIGINT NOT NULL,
    "yearOfRelease" INT NOT NULL,
    "description" VARCHAR(250),
    "type" VARCHAR(10) NOT NULL,
    "ageClassification" INT,
    "mediaType" VARCHAR(255) NOT NULL,
    FOREIGN KEY ("genreId") REFERENCES "Genre"("genreId") ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE "PhysicalProductTemplate" (
    "productId" BIGSERIAL PRIMARY KEY,
    "location" VARCHAR(100) NOT NULL,
    "author" VARCHAR(100) NOT NULL,
    FOREIGN KEY ("productId") REFERENCES "ProductTemplate" ("productId")
);

CREATE TABLE "DigitalProductTemplate"
(
    "productId" BIGSERIAL PRIMARY KEY,
    "language"  VARCHAR(100) NOT NULL,
    FOREIGN KEY ("productId") REFERENCES "ProductTemplate" ("productId")
);

CREATE TABLE "PhysicalProduct" (
    "productId" BIGSERIAL PRIMARY KEY,
    "ISBN" BIGINT,
    "author" VARCHAR(100) NOT NULL,
    FOREIGN KEY ("productId") REFERENCES "ProductTemplate" ("productId")
);
CREATE TABLE "DigitalProduct"
(
    "productId" BIGSERIAL PRIMARY KEY,
    "language"      BIGINT,
    FOREIGN KEY ("productId") REFERENCES "ProductTemplate" ("productId")
);


CREATE TABLE "ProductCopy"
(
    "productCopyId"      BIGSERIAL PRIMARY KEY,
    "availabilityStatus" VARCHAR(50) NOT NULL,
    "productId"          BIGSERIAL      NOT NULL,
    FOREIGN KEY ("productId") REFERENCES "PhysicalProduct" ("productId")
);

CREATE TABLE "Loan"
(
    "loanId" BIGSERIAL PRIMARY KEY,
    "startDate"     DATE   NOT NULL,
    "returnBy"      DATE,
    "returnedOn"    DATE,
    "extendedReturnBy" DATE,
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
    "productCopyId"   BIGSERIAL NOT NULL,
    "reservationDate" DATE    NOT NULL,
    "readyForPickup"  BOOLEAN NOT NULL,
    "reservationPickUpDate" DATE,
    "reservationStatus" VARCHAR(50) NOT NULL,
    FOREIGN KEY ("membershipId") REFERENCES "Membership" ("membershipId") ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY ("productCopyId") REFERENCES "ProductCopy" ("productCopyId") ON UPDATE CASCADE ON DELETE RESTRICT
);
CREATE TYPE loanStatus AS ENUM ('ACTIVE', 'EXTENDED', 'RETURNED', 'OVERDUE');
CREATE TYPE reservationStatus AS ENUM ('ACTIVE', 'LOANED', 'EXPIRED', 'CANCELLED');
-- region: Event related
CREATE TABLE "Event" (
    "eventId" BIGSERIAL NOT NULL,
    "type" VARCHAR(50) NOT NULL,
    "scheduledAt" TIMESTAMP NOT NULL,
    "associationType" VARCHAR(50) NOT NULL,
    "loan" BIGINT,
    "reservation" BIGINT,
    PRIMARY KEY ("eventId"),
    FOREIGN KEY ("loan") REFERENCES "Loan"("loanId"),
    FOREIGN KEY ("reservation") REFERENCES "Reservation"("reservationId")
);
-- endregion

