package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Membership;
import java.util.Optional;

public interface IMembershipRepository {
    Optional<Membership> retrieveMembershipById(long id);
    Membership store(Membership membership);
}