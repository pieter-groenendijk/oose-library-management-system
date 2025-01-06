package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.MembershipType;
import java.util.Optional;

public interface IMembershipTypeRepository {
    Optional<MembershipType> retrieveMembershipTypeById(long id);
    MembershipType store(MembershipType membershipType);
    boolean doesMembershipTypeExistByDescription(String description);
    MembershipType update(MembershipType membershipType);
    Optional<MembershipType> deleteMembershipTypeById(long id);
}