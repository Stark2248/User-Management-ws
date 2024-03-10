package com.silverlining.usermanagement.repository;

import com.silverlining.usermanagement.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        User u1 = new User();
        User u2 = new User();

        u1.setId("Id1");
        u1.setFirstName("firstName1");
        u1.setLastName("lastName1");

        u2.setId("Id2");
        u2.setFirstName("firstName2");
        u2.setLastName("lastName2");

        userRepository.save(u1);
        userRepository.save(u2);

    }

    @Test
    void findById() {

        Optional<User> id2 = userRepository.findById("Id2");

        Assertions.assertTrue(id2.isPresent());
        Assertions.assertEquals("firstName2",id2.get().getFirstName());

    }

    @Test
    void deleteById() {

        userRepository.deleteById("Id1");

        Optional<User> id1 = userRepository.findById("Id1");

        Assertions.assertFalse(id1.isPresent());
    }
}