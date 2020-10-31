package com.ess.filter.service.impl;

import com.ess.filter.entity.User;
import com.ess.filter.service.UserService;
import com.ess.filter.service.naming.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImplement implements UserService {

    private UserServiceApi userServiceApi;

    @Autowired
    public UserServiceImplement(UserServiceApi userServiceApi) {
        this.userServiceApi = userServiceApi;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = findByUsername(userName);
        if (user == null || user.getId() == null || user.getUsername() == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));
    }

    @Override
    public User findByUsername(String name) {
        return userServiceApi.findUSerByName(name);
    }

    private Set getAuthority(User user) {
        Set authorities = new HashSet();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            role.getPrivileges().forEach(pr->{
                authorities.add(new SimpleGrantedAuthority("PRIVILEGE_" + pr.getName()));
            });
        });
        return authorities;
    }

//    public User testApi(String username, String token){
//        UriTemplateHandler url = restTemplate.getUriTemplateHandler();
//        url.expand("Authorization", token);
//        User test = restTemplate.getForObject(configValue.getUserUrl()+"/user/"+username, User.class);
//        return test;
//    }

}
