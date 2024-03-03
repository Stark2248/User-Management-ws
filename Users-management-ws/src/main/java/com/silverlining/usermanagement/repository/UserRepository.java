package com.silverlining.usermanagement.repository;

import com.silverlining.usermanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(String id);

    void deleteById(String id);
}
