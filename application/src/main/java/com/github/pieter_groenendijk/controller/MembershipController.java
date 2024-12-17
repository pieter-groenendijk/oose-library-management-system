package com.github.pieter_groenendijk.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.service.IAccountService;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.repository.MembershipTypeRepository;
import com.github.pieter_groenendijk.repository.MembershipRepository;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.model.Membership;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;

@RestController
@RequestMapping("/membership")
public class MembershipController{

	private IAccountService accountService;
	private SessionFactory sessionFactory = new SessionFactoryFactory().create();

	public MembershipController()
	{
		IAccountRepository accountRepository = new AccountRepository(sessionFactory);
        IMembershipTypeRepository membershipTypeRepository = new MembershipTypeRepository(sessionFactory);
        IMembershipRepository membershipRepository = new MembershipRepository(sessionFactory);
        accountService = new AccountService(accountRepository, membershipTypeRepository, membershipRepository);
	}

	@Operation(summary = "Retrieve a membership", description = "Retrieve a membership by Id")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Membership found"),
        @ApiResponse(responseCode = "404", description = "Membership not found")
    })
    @GetMapping("/{id}")
    public Membership retrieveMembershipById(@PathVariable("id") long id) {
        Membership membership = accountService.retrieveMembershipById(id);
        return membership;
    }

    @Operation(summary = "Create a membership", description = "Add a new membership to the database")
    @PostMapping
    public Membership createMembership(@RequestBody MembershipDTO request) {
        return accountService.store(request);
    }
}
