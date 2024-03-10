package com.silverlining.usermanagement.controller;

import com.silverlining.usermanagement.dto.UserDto;
import com.silverlining.usermanagement.httpmodels.UserResponseModel;
import com.silverlining.usermanagement.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService service;

//    @InjectMocks
//    private UserController userController;

//    @Captor
//    private ArgumentCaptor<String> stringArgumentCaptor;

//    @BeforeAll
//    public static void setUp(){
//        UserControllerTest t=new UserControllerTest();
//        t.mockMvc = MockMvcBuilders.standaloneSetup(t.userController).build();
//    }



    @Test
    void getAllUsersSuccess() throws Exception {
        UserDto u1 = new UserDto();
        u1.setId("Id1");
        u1.setFirstName("firstName");
        u1.setLastName("lastName");

        UserDto u2 = new UserDto();
        u2.setId("Id2");
        u2.setFirstName("firstName2");
        u2.setLastName("lastName2");

        List<UserDto> list=new ArrayList<>();

        list.add(u1);
        list.add(u2);

        UserResponseModel ur1=new UserResponseModel(u1.getId(),u1.getFirstName(),u1.getLastName());
        UserResponseModel ur2=new UserResponseModel(u2.getId(), u2.getFirstName(), u2.getLastName());

        List<UserResponseModel> listresponse=new ArrayList<>();
        listresponse.add(ur1);
        listresponse.add(ur2);

        ResponseEntity<List<UserResponseModel>> expectedResponse=ResponseEntity.status(HttpStatus.OK).body(listresponse);

        Mockito.when(service.getAllUsers()).thenReturn(list);

        mockMvc.perform(get("/users/all")).andExpect(status().isOk()).andExpect((ResultMatcher) expectedResponse);





    }

    @Test
    void getUserById() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}