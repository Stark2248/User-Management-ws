package com.silverlining.usermanagement.repository;

import com.silverlining.usermanagement.model.User;
import com.silverlining.usermanagement.model.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(String id);

    void deleteById(String id);
}
