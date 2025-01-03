package com.github.pieter_groenendijk.model;

import java.util.Date;

public enum LoanStatus {
    ACTIVE {
        @Override
        public boolean isOverdue(Date currentDate, Loan loan) {
            return currentDate.after(loan.getReturnBy());
        }
    },
    EXTENDED {
        @Override
        public boolean isOverdue(Date currentDate, Loan loan) {
            return currentDate.after(loan.getExtendedReturnBy());
        }
    },
    RETURNED {
        @Override
        public boolean isOverdue(Date currentDate, Loan loan) {
            return false;
        }
    },
    OVERDUE {
        @Override
        public boolean isOverdue(Date currentDate, Loan loan) {
            return true;
        }
    };

    public abstract boolean isOverdue(Date currentDate, Loan loan);
}

