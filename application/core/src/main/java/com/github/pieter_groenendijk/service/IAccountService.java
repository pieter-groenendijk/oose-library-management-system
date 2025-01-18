package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.DTO.MembershipTypeRequestDTO;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.LendingLimit;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;

import java.util.List;
import com.github.pieter_groenendijk.model.DTO.AccountRequestDTO;

public interface IAccountService {

    Account retrieveAccountById(long id) throws Exception;
    
    MembershipType retrieveMembershipTypeById(long id);
    
    void store(Account account) throws Exception;

    void store(AccountRequestDTO account) throws Exception;

    void update(Account account) throws Exception;

    void update(long id, AccountRequestDTO account) throws Exception;

    void store(MembershipTypeRequestDTO membershipType);
    
    Membership retrieveMembershipById(long id);
    
    void store(MembershipRequestDTO request) throws Exception;

    List<Membership> retrieveMembershipsByAccountId (long id);

    void setIsBlocked(long id, boolean newValue) throws Exception;

    void update(long id, MembershipTypeRequestDTO request);

    List<MembershipType> retrieveMembershipTypeList();

    void update(long id, MembershipRequestDTO request);

    void softDeleteAccount(long id) throws Exception;

    void softDeleteMembership(long id);

    void softDeleteMembershipType(long id);

    LendingLimit retrieveLendingLimitById(long id);

    void store(LendingLimit lendingLimit);

    void update(long id, LendingLimit lendingLimit);

    List<LendingLimit> retrieveLendingLimitList(long id);

    void softDeleteLendingLimit(long id);
}
