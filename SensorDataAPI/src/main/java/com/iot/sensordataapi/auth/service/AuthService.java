package com.iot.sensordataapi.auth.service;

import com.iot.sensordataapi.auth.model.AuthRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@Service
public class AuthService {

    /**
     * @param userName
     * @return
     */
    public User getUser(String userName) {
        if (userName.equalsIgnoreCase("datastream")) {
            return (User) User.builder()
                    .username("datastream")
                    .password("Datastream1!")
                    .roles("USER")
                    .build();

        }
        return null;
    }

}
