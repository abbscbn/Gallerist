package com.abbascoban.gallerist.service.impl;

import com.abbascoban.gallerist.dto.*;
import com.abbascoban.gallerist.enums.Role;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.jwt.JwtService;
import com.abbascoban.gallerist.model.Customer;
import com.abbascoban.gallerist.model.Gallerist;
import com.abbascoban.gallerist.model.RefreshToken;
import com.abbascoban.gallerist.model.User;
import com.abbascoban.gallerist.repository.CustomerRepository;
import com.abbascoban.gallerist.repository.GalleristRepository;
import com.abbascoban.gallerist.repository.RefreshTokenRepository;
import com.abbascoban.gallerist.repository.UserRepository;
import com.abbascoban.gallerist.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationProvider authenticationProvider;

    private final JwtService jwtService;

    private final RefreshTokenRepository refreshTokenRepository;

    private final CustomerRepository customerRepository;

    private final GalleristRepository galleristRepository;

    public User createUser(RegisterRequest input){

        User user= new User();

        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCreateTime(new Date());
        user.setEmail(input.getEmail());

        if(input.getRole().equals(Role.CUSTOMER.name())){
            user.setRole(Role.CUSTOMER);
            user.setIsProfileCompleted(false);
        }
        if(input.getRole().equals(Role.GALLERIST.name())){
            user.setRole(Role.GALLERIST);
            user.setIsProfileCompleted(false);
        }


        return userRepository.save(user);


    }

    @Override
    public DtoUser register(RegisterRequest input) {

        DtoUser dtoUser= new DtoUser();
        User savedUser = createUser(input);

        if(savedUser.getRole().equals(Role.CUSTOMER)){
            Customer customer= new Customer();
            customer.setUser(savedUser);
            customerRepository.save(customer);

        }
        if(savedUser.getRole().equals(Role.GALLERIST)){
            Gallerist gallerist= new Gallerist();
            gallerist.setUser(savedUser);
            galleristRepository.save(gallerist);
        }

        BeanUtils.copyProperties(savedUser,dtoUser);

        return dtoUser;

    }
    private RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken= new RefreshToken();


        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() +1000*60*60*4));

        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setCreateTime(new Date());
        return refreshToken;



    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {

            UsernamePasswordAuthenticationToken authenticationToken= new
                    UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(input.getUsername());

            String accessToken = jwtService.generateToken(optUser.get());

            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));


            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken(),optUser.get().getRole().name(),optUser.get().getId(),optUser.get().getIsProfileCompleted());


        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID,""));
        }
    }

    public boolean isValidRefreshToken(Date expiredDate) {
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {

        Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());

        if(optRefreshToken.isEmpty()) {

            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
        }

        if(!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));
        }

        User user = optRefreshToken.get().getUser();

        String accessToken = jwtService.generateToken(user);

        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken(),user.getRole().name(),user.getId(),user.getIsProfileCompleted());




    }



}
