package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.MembershipType;
import java.util.Optional;
import java.util.List;
import com.github.pieter_groenendijk.model.LendingLimit;

public interface IMembershipTypeRepository {
    Optional<MembershipType> retrieveMembershipTypeById(long id);
    void store(MembershipType membershipType);
    boolean doesMembershipTypeExistByDescription(String description);
    void update(MembershipType membershipType);
    List<MembershipType> retrieveMembershipTypeList();
    Optional<LendingLimit> retrieveLendingLimitById(long id);
    void store(LendingLimit lendingLimit);
    void update(LendingLimit lendingLimit);
    List<LendingLimit> retrieveLendingLimitList(long id);
}