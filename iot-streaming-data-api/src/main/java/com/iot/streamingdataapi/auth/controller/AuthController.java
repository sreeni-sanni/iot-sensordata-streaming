package com.iot.streamingdataapi.auth.controller;

import com.iot.streamingdataapi.auth.model.AuthRequest;
import com.iot.streamingdataapi.auth.service.AuthService;
import com.iot.streamingdataapi.auth.service.JwtUtilService;
import com.iot.streamingdataapi.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.iot.streamingdataapi.constants.Constants.AUTH_TOKEN;
import static com.iot.streamingdataapi.constants.Constants.USER_NOT_FOUND;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private JwtUtilService jwtUtilService;

    private AuthService authServiceImpl;

    @PostMapping(AUTH_TOKEN)
    public ResponseEntity<String> getToken(@RequestBody @Valid AuthRequest authRequest) {

        UserDetails user = authServiceImpl.getUser(authRequest.username());
        if (user != null) {
            return ResponseEntity.ok(jwtUtilService.generateToken(user.getUsername()));
        }
        throw new UserNotFoundException(USER_NOT_FOUND);
    }
}
