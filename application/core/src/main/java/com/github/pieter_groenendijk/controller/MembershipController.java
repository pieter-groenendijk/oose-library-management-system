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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

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
    public ResponseEntity<?> retrieveMembershipById(@PathVariable("id") long id) {
        Membership membership = accountService.retrieveMembershipById(id);
        return ResponseEntity.ok(membership);
    }

    @Operation(summary = "Retrieve membershipList", description = "Retrieve memberships by Account Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Memberships found"),
        @ApiResponse(responseCode = "404", description = "No memberships found for the given Account Id")
    })
    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> retrieveMembershipsByAccountId(@PathVariable("accountId") long accountId) {
        List<Membership> memberships = accountService.retrieveMembershipsByAccountId(accountId);
        if (memberships.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(memberships);
        }
    }

    @Operation(summary = "Create a membership", description = "Add a new membership to the database")
    @PostMapping
    public ResponseEntity<?> createMembership(@RequestBody MembershipRequestDTO request) throws Exception {
        accountService.store(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a membership", description = "Update a membership in the database")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMembership(@PathVariable("id") long id, @RequestBody MembershipRequestDTO request){
        accountService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Softdelete a membership", description = "Softdelete an membership in the database")
    @PutMapping("/softdelete/{id}")
    public ResponseEntity<?> softDeleteMembership(@PathVariable("id") long id) {
        accountService.softDeleteMembership(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
