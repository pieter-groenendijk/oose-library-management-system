package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.model.IAccountRepository;
import com.github.pieter_groenendijk.model.IMembershipTypeRepository;

public interface IAccountService(IAccountRepository accountRepository, IMembershipTypeRepository membershipTypeRepository) 
{
	public Account retrieveAccountById(long id);
	public MembershipType retrieveMembershipTypeById(long id);
	public Account store(Account account);
	public MembershipType store(MembershipType membershipType);
	private boolean isAccountInputValid(Account account);
}