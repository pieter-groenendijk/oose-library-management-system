package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.MembershipType;
import java.util.Optional;
import java.util.List;

public interface IMembershipTypeRepository {
    Optional<MembershipType> retrieveMembershipTypeById(long id);
    MembershipType store(MembershipType membershipType);
    boolean doesMembershipTypeExistByDescription(String description);
    MembershipType update(MembershipType membershipType);
    List<MembershipType> retrieveMembershipTypeList();
}