package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.service.IAccountService;
import com.github.pieter_groenendijk.repository.MembershipTypeRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.LendingLimit;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.MembershipRepository;
import com.github.pieter_groenendijk.model.DTO.MembershipTypeRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/membershipType") 
public class MembershipTypeController {

    private IAccountService accountService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private MembershipTypeController()
    {
        IAccountRepository accountRepository = new AccountRepository(sessionFactory);
        IMembershipTypeRepository membershipTypeRepository = new MembershipTypeRepository(sessionFactory);
        IMembershipRepository membershipRepository = new MembershipRepository(sessionFactory);
        accountService = new AccountService(accountRepository, membershipTypeRepository, membershipRepository);
    }

    @Operation(summary = "Retrieve a membershipType", description = "Retrieve a membershipType by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "MembershipType found"),
        @ApiResponse(responseCode = "404", description = "MembershipType not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveMembershipTypeById(@PathVariable("id") long id) {
        MembershipType membershipType = accountService.retrieveMembershipTypeById(id);
        return ResponseEntity.ok(membershipType);
    }

    @Operation(summary = "Create a membershipType", description = "Add a new membershipType to the database")
    @PostMapping
    public ResponseEntity<?> createMembershipType(@RequestBody MembershipTypeRequestDTO membershipType) {
        accountService.store(membershipType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a membershipType", description = "Change a membershipType ")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMembershipType(@PathVariable("id") long id, @RequestBody MembershipTypeRequestDTO membershipType) {
        accountService.update(id, membershipType);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Retrieve a list of memberships", description = "Retrieve a list of memberships")
    @GetMapping("/getAll")
    public ResponseEntity<List<MembershipType>> retrieveMembershipTypeList() {
        List<MembershipType> membershipTypes = accountService.retrieveMembershipTypeList();
        if (membershipTypes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(membershipTypes);
        }
    }

    @Operation(summary = "Softdelete a membershipType", description = "Softdelete a membershipType in the database")
    @PutMapping("/softdelete/{id}")
    public ResponseEntity<?> softDeleteMembershipType(@PathVariable("id") long id) {
        accountService.softDeleteMembershipType(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //LendingLimit

    @Operation(summary = "Retrieve a LendingLimit", description = "Retrieve a LendingLimit by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LendingLimit found"),
            @ApiResponse(responseCode = "404", description = "LendingLimit not found")
    })
    @GetMapping("/lendingLimit/{id}")
    public ResponseEntity<?> retrieveLendingLimitById(@PathVariable("id") long id ) {
        LendingLimit lendingLimit = accountService.retrieveLendingLimitById(id);
        return ResponseEntity.ok(lendingLimit);
    }

    @Operation(summary = "Create a lendingLimit", description = "Add a new lendingLimit to the database")
    @PostMapping("/lendingLimit")
    public ResponseEntity<?> createLendingLimit(@RequestBody LendingLimit lendingLimit) {
        accountService.store(lendingLimit);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a lendingLimit", description = "Change a lendingLimit ")
    @PutMapping("/lendingLimit/{id}")
    public ResponseEntity<?> updateLendingLimit(@PathVariable("id") long id, @RequestBody LendingLimit lendingLimit) {
        accountService.update(id, lendingLimit);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Retrieve a list of lendingLimits", description = "Retrieve a list of lendinglimits for a membershipType")
    @GetMapping("/lendingLimit/getAll/{id}")
    public ResponseEntity<List<LendingLimit>> retrieveGenreList(@PathVariable("id") long membershipTypeId) {
        List<LendingLimit> lendingLimitList = accountService.retrieveLendingLimitList(membershipTypeId);
        if (lendingLimitList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(lendingLimitList);
        }
    }

    @Operation(summary = "Softdelete a lendingLimit", description = "Softdelete a lendingLimit in the database")
    @PutMapping("/lendingLimit/softdelete/{id}")
    public ResponseEntity<?> softDeleteLendingLimit(@PathVariable("id") long id) {
        accountService.softDeleteLendingLimit(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}