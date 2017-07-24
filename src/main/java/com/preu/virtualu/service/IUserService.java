package com.preu.virtualu.service;

import com.preu.virtualu.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    public void removeUserByEmail(String email);
}
