package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.LendingLimit;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;
import com.github.pieter_groenendijk.model.DTO.MembershipTypeRequestDTO;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import java.util.List;
import com.github.pieter_groenendijk.model.DTO.AccountRequestDTO;

public interface IAccountService {

    Account retrieveAccountById(long id);
    
    MembershipType retrieveMembershipTypeById(long id);
    
    void store(AccountRequestDTO account);

    void update(long id, AccountRequestDTO account);
    
    void store(MembershipTypeRequestDTO membershipType);
    
    Membership retrieveMembershipById(long id);
    
    void store(MembershipRequestDTO request);

    List<Membership> retrieveMembershipsByAccountId (long id);

    void setIsActive(long id, boolean newValue);

    void update(long id, MembershipTypeRequestDTO request);

    List<MembershipType> retrieveMembershipTypeList();

    void update(long id, MembershipRequestDTO request);

    void softDeleteAccount(long id);

    void softDeleteMembership(long id);

    void softDeleteMembershipType(long id);

    LendingLimit retrieveLendingLimitById(long id);

    void store(LendingLimit lendingLimit);

    void update(long id, LendingLimit lendingLimit);

    List<LendingLimit> retrieveLendingLimitList(long id);

    void softDeleteLendingLimit(long id);
}
