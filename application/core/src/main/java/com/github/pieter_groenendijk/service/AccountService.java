package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.service.validator.EmailValidator;
import com.github.pieter_groenendijk.service.validator.GenderCheck;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class AccountService implements IAccountService {

    private static int uncollectedReservationCount = 0;
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

    public List<Membership> retrieveMembershipsByAccountId(long id) {
    List<Membership> memberships = membershipRepository.retrieveMembershipsByAccountId(id);
    if (memberships.isEmpty()) {
        throw new EntityNotFoundException("No memberships found for this accountId");
    } 
    return memberships;
}


    public Membership store(MembershipRequestDTO request){
        //Validate input
        Account account = accountRepository.retrieveAccountById(request.getAccountId())
        .orElseThrow(() -> new EntityNotFoundException("Account with ID " + request.getAccountId() + "not found."));
        MembershipType membershipType = membershipTypeRepository.retrieveMembershipTypeById(request.getMembershipTypeId())
        .orElseThrow(() -> new EntityNotFoundException("MembershipType with ID" + request.getMembershipTypeId() + " not found."));

        //Create membership
        Membership membership = new Membership();
        membership.setAccount(account);
        membership.setMembershipType(membershipType);
        membership.setStartDate(new Date());
        membership.setActive(true);
        membership.setBlocked(false);

        //Persist
        return membershipRepository.store(membership);
    }

    public static void incrementUncollectedReservationCount() {
      uncollectedReservationCount++;
    }
}