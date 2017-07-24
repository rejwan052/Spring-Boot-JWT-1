package com.preu.virtualu.service;

import com.preu.virtualu.model.Role;
import com.preu.virtualu.model.User;
import com.preu.virtualu.repository.RoleRepository;
import com.preu.virtualu.repository.UserRepository;
import com.preu.virtualu.security.utils.JwtUserFactory;
import com.preu.virtualu.security.utils.extras.TimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Service("userService")
public class UserService implements IUserService, UserDetailsService{

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        user.setName(user.getEmail());
        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        }else{
            return JwtUserFactory.create(user);
        }

    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setState(true);
        final Date createdDate = timeProvider.now();
        user.setLastPasswordReset(createdDate);
        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void removeUserByEmail(String email) {
        userRepository.removeByEmail(email);
    }
}
