package com.github.pieter_groenendijk;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.UUID;

public class HibernateTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new SessionFactoryFactory().create();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        String newEmail = UUID.randomUUID().toString();

        // Example: Creating and saving a new account
        Account account = new Account();
        account.setEmail(newEmail+"@example.com");
        account.setFirstName("John");
        account.setLastName("Doe");
        account.setDateOfBirth(new Date());
        account.setGender('M');
        account.setActive(true);

        session.save(account);
        session.getTransaction().commit();
        session.close();

        sessionFactory.close();
        System.out.println("Data inserted successfully!");
    }
}
