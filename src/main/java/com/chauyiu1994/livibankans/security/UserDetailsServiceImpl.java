package com.chauyiu1994.livibankans.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

// dummy implementation of UserDetailsService, actually implementation should connect to database
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetails loadUserByAccessToken(String username) throws UsernameNotFoundException {
        return new User(username, "", Collections.singleton(new SimpleGrantedAuthority(Roles.SCORE_CALCULATOR)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        switch (username) {
            case "normal":
                return new User("normal", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", Collections.singleton(new SimpleGrantedAuthority(Roles.SCORE_CALCULATOR)));
            case "hello":
                return new User("hello", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());

            default:
                throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
