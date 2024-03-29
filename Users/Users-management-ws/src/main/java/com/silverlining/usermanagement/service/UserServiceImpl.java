package com.silverlining.usermanagement.service;

import com.silverlining.usermanagement.dto.UserDto;
import com.silverlining.usermanagement.model.User;
import com.silverlining.usermanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    //Getting all user from database
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        List<UserDto> userDtos=new ArrayList<>();
        UserDto dto;
        for(User u: users){
            dto= new UserDto();
            dto.setId(u.getId());
            dto.setFirstName(u.getFirstName());
            dto.setLastName(u.getLastName());
            userDtos.add(dto);
        }
        return userDtos;
    }

    @Override
    public UserDto getUser(String id) {

        Optional<User> user;

        user=userRepository.findById(id);

        if(user.isPresent()){
            UserDto u=new UserDto();
            u.setId(user.get().getId());
            u.setFirstName(user.get().getFirstName());
            u.setLastName(user.get().getLastName());

            return u;

        }


        return null;
    }

    @Override
    public UserDto saveUser(UserDto user) {
        User u=new User();

        String id= UUID.randomUUID().toString();
        if(user.getId().isEmpty()){
            u.setId(id);
            user.setId(id);
        }else{
            u.setId(user.getId());
        }
        u.setLastName(user.getLastName());
        u.setFirstName(user.getFirstName());
        userRepository.save(u);
        return user;
    }

    @Override
    public boolean updateUser(UserDto userdto){
        Optional<User> userReturn=userRepository.findById(userdto.getId());
        if(userReturn.isPresent()){
            User user= new User();
            user.setId(userdto.getId());
            user.setFirstName(userdto.getFirstName());
            user.setLastName(userdto.getLastName());
            userRepository.save(user);
            return true;
        }else

            return false;

    }

    @Override
    @Transactional
    public boolean deleteUser(UserDto user){

        Optional<User> userReturn=userRepository.findById(user.getId());

        if(userReturn.isPresent()){
            userRepository.deleteById(userReturn.get().getId());
            return true;
        }

        return false;
    }
}
