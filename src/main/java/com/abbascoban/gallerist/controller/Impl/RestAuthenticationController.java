package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestAuthenticationController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.*;
import com.abbascoban.gallerist.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestAuthenticationController extends RestBaseController implements IRestAuthenticationController {


    private final AuthenticationServiceImpl authenticationService;


    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody RegisterRequest input) {
       return ok(authenticationService.register(input));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
        return	ok(authenticationService.authenticate(input));

    }

    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {

        return ok(authenticationService.refreshToken(input));


    }
}
