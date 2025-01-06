package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import java.util.List;
import com.github.pieter_groenendijk.model.DTO.AccountRequestDTO;

public interface IAccountService {

    Account retrieveAccountById(long id);
    
    MembershipType retrieveMembershipTypeById(long id);
    
    Account store(AccountRequestDTO account);

    Account update(long id, AccountRequestDTO account);

    Account deleteAccount(long id);
    
    MembershipType store(MembershipType membershipType);
    
    Membership retrieveMembershipById(long id);
    
    Membership store(MembershipRequestDTO request);

    List<Membership> retrieveMembershipsByAccountId (long id);

    void toggleIsActive(long id, boolean newValue);

}
