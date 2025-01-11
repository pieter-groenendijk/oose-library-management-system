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
import com.github.pieter_groenendijk.model.DTO.MembershipTypeRequestDTO;
import com.github.pieter_groenendijk.model.DTO.AccountRequestDTO;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;
    private final IMembershipTypeRepository membershipTypeRepository;
    private final IMembershipRepository membershipRepository;

    public AccountService(IAccountRepository accountRepository, IMembershipTypeRepository membershipTypeRepository, IMembershipRepository membershipRepository) {
        this.accountRepository = accountRepository;
        this.membershipTypeRepository = membershipTypeRepository;
        this.membershipRepository = membershipRepository;
    }

    //AccountFunctionality

    public Account retrieveAccountById(long id) {
        return accountRepository.retrieveAccountById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account with ID " + id + " not found."));
    }

    public void store(AccountRequestDTO request){
        boolean emailAlreadyExists = accountRepository.doesAccountExistByEmail(request.getEmail());
        if (emailAlreadyExists) {
            throw new InputValidationException("E-mail already exists!");
        }

        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setDateOfBirth(request.getDateOfBirth());
        account.setGender(request.getGender());
        account.setActive(true);
        account.setUncollectedReservations(0);

        if ( isAccountInputValid(account)) {
            accountRepository.store(account);
        } else {
            throw new InputValidationException("Account input is not valid");
        }
    }

    public void update(long id, AccountRequestDTO account) {
        Account retrievedAccount =  retrieveAccountById(id);
        if (retrievedAccount == null) {
            throw new EntityNotFoundException("Account with ID " + id + " not found.");
        }

        if (!retrievedAccount.getEmail().equals(account.getEmail())) {
            boolean emailAlreadyExists = accountRepository.doesAccountExistByEmail(account.getEmail());
            if (emailAlreadyExists) {
                throw new InputValidationException("E-mail already exists!");
            }
        }

        retrievedAccount.setEmail(account.getEmail());
        retrievedAccount.setFirstName(account.getFirstName());
        retrievedAccount.setLastName(account.getLastName());
        retrievedAccount.setDateOfBirth(account.getDateOfBirth());
        retrievedAccount.setGender(account.getGender());

        if (!isAccountInputValid(retrievedAccount)){
            throw new InputValidationException("Account input is not valid");
        } else {
            accountRepository.update(retrievedAccount);
        }
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

    public void setIsActive(long id, boolean newValue) {
        Account retrievedAccount =  retrieveAccountById(id);
        if (retrievedAccount == null) {
            throw new EntityNotFoundException("Account with ID " + id + " not found.");
        }
        if (newValue == retrievedAccount.isActive()){
            throw new InputValidationException("This account is already (in)active");
        } else {
            retrievedAccount.setActive(newValue);
            accountRepository.update(retrievedAccount);
        }
    }

    //MembershipTypeFunctionality

    public MembershipType retrieveMembershipTypeById(long id){
        return membershipTypeRepository.retrieveMembershipTypeById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membershiptype with ID " + id + " not found."));
    }

    public void store(MembershipTypeRequestDTO request){
        if (request.getMaxLendings() <= 0) {
            throw new InputValidationException("MaxLendings should be at least 1!");
        }
        boolean descriptionAlreadyExists = membershipTypeRepository.doesMembershipTypeExistByDescription(request.getDescription());
        if (descriptionAlreadyExists) {
            throw new InputValidationException("MembershipType with this description already exists!");
        }

        MembershipType membershipType = new MembershipType();
        membershipType.setDescription(request.getDescription());
        membershipType.setDigitalProducts(request.isDigitalProducts());
        membershipType.setPhysicalProducts(request.isPhysicalProducts());
        membershipType.setMaxLendings(request.getMaxLendings());

        membershipTypeRepository.store(membershipType);
    }

    public void update(long id, MembershipTypeRequestDTO request) {
        if (request.getMaxLendings() <= 0) {
            throw new InputValidationException("MaxLendings should be at least 1!");
        }
        MembershipType retrievedMembershipType =  retrieveMembershipTypeById(id);
        if (retrievedMembershipType == null) {
            throw new EntityNotFoundException("MembershipType with ID " + id + " not found.");
        }

          if (!retrievedMembershipType.getDescription().equals(request.getDescription())){
            boolean descriptionAlreadyExists = membershipTypeRepository.doesMembershipTypeExistByDescription(request.getDescription());
            if (descriptionAlreadyExists) {
                throw new InputValidationException("Description already exists!");
            }
        }

        retrievedMembershipType.setDescription(request.getDescription());
        retrievedMembershipType.setDigitalProducts(request.isDigitalProducts());
        retrievedMembershipType.setPhysicalProducts(request.isPhysicalProducts());
        retrievedMembershipType.setMaxLendings(request.getMaxLendings());
        membershipTypeRepository.update(retrievedMembershipType);
    }

    public List<MembershipType> retrieveMembershipTypeList() {
        List<MembershipType> membershipTypes = membershipTypeRepository.retrieveMembershipTypeList();
        return membershipTypes;
    }

    //MembershipFunctionality

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

    public void store(MembershipRequestDTO request){

        Account account = accountRepository.retrieveAccountById(request.getAccountId())
        .orElseThrow(() -> new EntityNotFoundException("Account with ID " + request.getAccountId() + "not found."));
        MembershipType membershipType = membershipTypeRepository.retrieveMembershipTypeById(request.getMembershipTypeId())
        .orElseThrow(() -> new EntityNotFoundException("MembershipType with ID" + request.getMembershipTypeId() + " not found."));

        Membership membership = new Membership();
        membership.setAccount(account);
        membership.setMembershipType(membershipType);
        membership.setStartDate(new Date());
        membership.setActive(true);
        membership.setBlocked(false);

        membershipRepository.store(membership);
    }

    public void update(long id, MembershipRequestDTO request){
        Membership retrievedMembership = retrieveMembershipById(id);
        if (retrievedMembership.getAccount().getAccountId() != request.getAccountId()) {
            throw new InputValidationException("Cannot move a membership between accounts!");
        }
        MembershipType retrievedMembershipType = retrieveMembershipTypeById(request.getMembershipTypeId());
        if (retrievedMembership.getMembershipType().getMembershipTypeId() == retrievedMembershipType.getMembershipTypeId()) {
            throw new InputValidationException("Membership already has this account type");
        }
        retrievedMembership.setMembershipType(retrievedMembershipType);
        membershipRepository.update(retrievedMembership);
    }
}