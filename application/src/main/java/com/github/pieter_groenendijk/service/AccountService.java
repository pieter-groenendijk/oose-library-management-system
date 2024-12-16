package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.service.validator.EmailValidator;
import com.github.pieter_groenendijk.service.validator.GenderCheck;
import java.time.LocalDate;
import java.time.ZoneId;

public class AccountService {

    private final IAccountRepository accountRepository;
    private final IMembershipTypeRepository membershipTypeRepository;
    private final IMembershipRepository membershipRepository;

    public AccountService(IAccountRepository accountRepository, IMembershipTypeRepository membershipTypeRepository, IMembershipRepository membershipRepository) {
        this.accountRepository = accountRepository;
        this.membershipTypeRepository = membershipTypeRepository;
        this.membershipRepository = membershipRepository;
    }

    public Account retrieveAccountById(long id) {
        return accountRepository.retrieveAccountById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account with ID " + id + " not found."));
    }

    public MembershipType retrieveMembershipTypeById(long id){
        return membershipTypeRepository.retrieveMembershipTypeById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membershiptype with ID " + id + " not found."));
    }

    public Account store(Account account) {
        if ( isAccountInputValid(account)) {
            return accountRepository.store(account);
        }
        throw new InputValidationException("Account input is not valid");
    }

    public MembershipType store(MembershipType membershipType){
        return membershipTypeRepository.store(membershipType);
    }

    private boolean isAccountInputValid(Account account) {
        if (!EmailValidator.isValidEmail(account.getEmail())) {
            throw new InputValidationException("Email " + account.getEmail() + " is not valid");
        } else if (!GenderCheck.exists(account.getGender())) {
            throw new InputValidationException("Unknown gender identifier: " + account.getGender());
        } else if (!account.getDateOfBirth().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .isBefore(LocalDate.now())) {
            throw new InputValidationException("Date of birth " + account.getDateOfBirth() + " is in the future");
        }
        return true;
    }

    public Membership retrieveMembershipById(long id){
        return membershipRepository.retrieveMembershipById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membership with ID " + id + " not found."));
    }

    public Membership store(Membership membership){
        return membershipRepository.store(membership);
    }
}