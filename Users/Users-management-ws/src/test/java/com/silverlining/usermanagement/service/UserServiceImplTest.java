package com.silverlining.usermanagement.service;

import com.silverlining.usermanagement.dto.UserDto;
import com.silverlining.usermanagement.model.User;
import com.silverlining.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  UserRepository repository;

  @InjectMocks
  UserServiceImpl service;

  @Test
  void getAllUsers() {

    User u1 = new User();
    u1.setId("1");
    u1.setFirstName("firstTestUser");
    u1.setLastName("ls");

    User u2 = new User();
    u1.setId("2");
    u1.setFirstName("secondTestUser");
    u1.setLastName("ls");

    Mockito.when(repository.findAll()).thenReturn(List.of(u1, u2));
    List<UserDto> allUsers = service.getAllUsers();
    Assertions.assertNotNull(allUsers);
    Assertions.assertEquals(2, allUsers.size());
  }
}