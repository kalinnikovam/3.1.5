package com.example.firstspringbootsecurityapp.service;

import com.example.firstspringbootsecurityapp.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

public interface UserService {

    void save(User user);

    void patch(User user);

    void deleteUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    UserDetails loadUserByUsername(String username);

}