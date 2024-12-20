package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Membership;
import java.util.Optional;
import java.util.List;

public interface IMembershipRepository {
    Optional<Membership> retrieveMembershipById(long id);
    Membership store(Membership membership);
    List<Membership> retrieveMembershipsByAccountId(long id);
}