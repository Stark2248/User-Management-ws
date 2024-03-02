package com.silverlining.usermanagement.controller;

import com.silverlining.usermanagement.dto.UserDto;
import com.silverlining.usermanagement.httpmodels.UserRequestModel;
import com.silverlining.usermanagement.httpmodels.UserResponseModel;
import com.silverlining.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/all")
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {
        List<UserDto> list = userService.getAllUsers();
        if (list.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        List<UserResponseModel> responseList = new ArrayList<>();
        for (UserDto dto : list) {
            UserResponseModel u = new UserResponseModel(dto.getId(), dto.getFirstName(), dto.getLastName());

            responseList.add(u);

        }

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseModel> getUserById(@PathVariable("id") String id) {
        UserDto dto = userService.getUser(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        UserResponseModel responseBody = new UserResponseModel(dto.getId(), dto.getFirstName(), dto.getLastName());

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/User")
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) {
        UserDto userDto = new UserDto();
        userDto.setId(userRequestModel.getId());
        userDto.setFirstName(userRequestModel.getFirstName());
        userDto.setLastName(userRequestModel.getLastName());
        UserDto response = userService.saveUser(userDto);
        UserResponseModel responseBody = new UserResponseModel(response.getId(), response.getFirstName(), response.getLastName());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody UserRequestModel requestBody) {
        UserDto dto = userService.getUser(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        dto.setFirstName(requestBody.getFirstName());
        dto.setLastName(requestBody.getLastName());
        boolean flag = userService.updateUser(dto);

        if (flag)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseModel> deleteUser(@PathVariable("id") String id) {
        UserDto userDto = userService.getUser(id);
        boolean flag = false;
        if (userDto != null) {
            flag = userService.deleteUser(userDto);
        }
        UserResponseModel responseBody = new UserResponseModel(userDto.getId(), userDto.getFirstName(), userDto.getLastName());
        if (flag) return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}
