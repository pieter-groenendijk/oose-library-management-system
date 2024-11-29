package com.databasetest.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "dateOfBirth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "gender", nullable = false)
    private char gender;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;
