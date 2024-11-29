package com.databasetest.test;

import org.hibernate.Session;
import com.databasetest.model.Account;
import com.databasetest.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Date;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

        HibernateUtil.shutdown();
        System.out.println("Data inserted successfully!");
    }
}
