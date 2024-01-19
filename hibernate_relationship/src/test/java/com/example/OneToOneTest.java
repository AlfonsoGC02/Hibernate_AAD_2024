package com.example;

import com.example.model.Address;
import com.example.model.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToOneTest {

    @Test
    void oneToOne() {
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var author1 = session.find(Author.class, 1L);
        System.out.println(author1);
        System.out.println(author1.getAddress());

        var author2 = session.find(Author.class, 2L);
        System.out.println(author2.getAddress());

    }
    void insertData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Author author1 = new Author("author1", "author1@email.com", LocalDate.of(1990, 1, 1));
        Author author2 = new Author("author2", "author2@email.com", LocalDate.of(1990, 1, 1));

        Address address1 = new Address("street1", "city1", "country1");
        Address address2 = new Address("street2", "city2", "country2");

        author1.setAddress(address1);
        author2.setAddress(address2);

        session.persist(address1);
        session.persist(address2);

        session.persist(author1);
        session.persist(author2);

        session.getTransaction().commit();

    }
}
