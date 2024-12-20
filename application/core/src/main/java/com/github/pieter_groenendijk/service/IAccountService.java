package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import java.util.List;

public interface IAccountService {

    Account retrieveAccountById(long id);
    
    MembershipType retrieveMembershipTypeById(long id);
    
    Account store(Account account);
    
    MembershipType store(MembershipType membershipType);
    
    Membership retrieveMembershipById(long id);
    
    Membership store(MembershipRequestDTO request);

    List<Membership> retrieveMembershipsByAccountId (long id);
}
