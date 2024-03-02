package com.silverlining.usermanagement.service;


import com.silverlining.usermanagement.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUser(int id) ;

    void saveUser(UserDto user);

    boolean updateUser(UserDto user);

    boolean deleteUser(UserDto user);
}
