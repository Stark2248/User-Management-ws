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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

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

  @Test
  void getUser(){

    User u1=new User();
    u1.setId("Id1");
    u1.setFirstName("firstName");
    u1.setLastName("lastName");

    UserDto u2=new UserDto();
    u2.setId(u1.getId());
    u2.setFirstName(u1.getFirstName());
    u2.setLastName(u1.getLastName());

    Optional<User> u= Optional.of(u1);

    Mockito.when(repository.findById("Id1")).thenReturn(u);

    UserDto rValue= service.getUser("Id1");

    Assertions.assertNotNull(rValue);
    Assertions.assertEquals(u2.getId(),rValue.getId());
    Assertions.assertEquals(u2.getFirstName(),rValue.getFirstName());
    Assertions.assertEquals(u2.getLastName(),rValue.getLastName());

  }

  @Test
  void saveUser(){

    UserDto u=new UserDto();

    u.setFirstName("firstName");
    u.setLastName("lastName");



    Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(new User());

    UserDto result = service.saveUser(u);

    Assertions.assertNotNull(result.getId());


  }

  @Test
  public void updateUser(){

    UserDto u = new UserDto();
    u.setId("Id1");
    u.setFirstName("firstName");
    u.setLastName("lastName");

    User user=new User();
    user.setId(u.getId());
    user.setFirstName(u.getFirstName());
    user.setLastName(u.getLastName());

    Optional<User> optionalUser=Optional.of(user);

    Mockito.when(repository.findById("Id1")).thenReturn(optionalUser);

    Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(new User());

    boolean flag = service.updateUser(u);

    Assertions.assertTrue(flag);

  }

  @Test
  public void deleteUser(){
    UserDto u = new UserDto();
    u.setId("Id1");
    u.setFirstName("firstName");
    u.setLastName("lastName");

    User user=new User();
    user.setId(u.getId());
    user.setFirstName(u.getFirstName());
    user.setLastName(u.getLastName());

    Optional<User> optionalUser=Optional.of(user);

    Mockito.when(repository.findById("Id1")).thenReturn(optionalUser);

    doAnswer((i)-> {
      System.out.println(i+"is getting deleted.");
      return null;
    }).when(repository).deleteById(anyString());


    boolean flag = service.deleteUser(u);

    Assertions.assertTrue(flag);


  }




}