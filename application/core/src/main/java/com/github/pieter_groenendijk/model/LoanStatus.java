package com.github.pieter_groenendijk.model;

import java.time.LocalDate;
import java.util.Date;

public enum LoanStatus {
    ACTIVE {

            @Override
            public boolean isOverdue(LocalDate currentDate, Loan loan) {
                return currentDate.isAfter(loan.getReturnBy());
            }
    },
    EXTENDED {
        @Override
        public boolean isOverdue(LocalDate currentDate, Loan loan) {
            return currentDate.isAfter(loan.getExtendedReturnBy());
        }
    },
    RETURNED {
        @Override
        public boolean isOverdue(LocalDate currentDate, Loan loan) {
            return false;
        }
    },
    OVERDUE {
        @Override
        public boolean isOverdue(LocalDate currentDate, Loan loan) {
            return true;
        }
    };

    public abstract boolean isOverdue(LocalDate currentDate, Loan loan);
}

