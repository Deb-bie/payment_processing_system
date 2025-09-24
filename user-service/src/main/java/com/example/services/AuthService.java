package com.example.services;

import com.example.config.JWTService;
import com.example.dto.requests.RegisterUserDto;
import com.example.dto.responses.AuthResponseDto;
import com.example.dto.responses.ResponseHandler;
import com.example.entities.User;
import com.example.exceptions.AlreadyExistsException;
import com.example.exceptions.EmptyRequestException;
import com.example.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JWTService jwtService
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public ResponseEntity<ResponseHandler<AuthResponseDto>> registerUser(RegisterUserDto registerUserDto) throws Exception {
        String token;
        AuthResponseDto authResponseDto = new AuthResponseDto();
        if (registerUserDto != null) {
            Optional<User> existingUser = userRepository.findByEmail(registerUserDto.getEmail());
            if (existingUser.isPresent()){
                throw new AlreadyExistsException("Email in use!");
            }
            if (isEmailValid(registerUserDto.getEmail())){
                User newUser = User.builder()
                        .firstName(registerUserDto.getFirstName())
                        .lastName(registerUserDto.getLastName())
                        .password(registerUserDto.getPassword())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .updatedAt(new Timestamp(System.currentTimeMillis()))
                        .build();
                userRepository.save(newUser);
                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                registerUserDto.getEmail(),
                                registerUserDto.getPassword()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
                UserDetails userDetails = (UserDetails) auth.getPrincipal();

                token = jwtService.generateToken(userDetails);

                Optional<User> user = userRepository.findByEmail(registerUserDto.getEmail());
                user.ifPresent(value -> authResponseDto.setUserId(value.getUserId()));

                authResponseDto.setEmail(registerUserDto.getEmail());
                authResponseDto.setToken(token);
                authResponseDto.setFirstName(registerUserDto.getFirstName());
                authResponseDto.setLastName(registerUserDto.getLastName());
            }
        } else {
            throw new EmptyRequestException("Email or password is empty!");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseHandler.<AuthResponseDto>builder()
                                .code(HttpStatus.CREATED.value())
                                .status(HttpStatus.CREATED)
                                .message(authResponseDto)
                                .build()
        );

    }


    boolean isEmailValid(String email){
        String regex = "^[A-Z0-9._%+-/!$*]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        return email != null && Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .matches();
    }


}
