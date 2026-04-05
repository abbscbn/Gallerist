package com.abbascoban.gallerist.service;

import com.abbascoban.gallerist.dto.*;

public interface IAuthenticationService {

    public DtoUser register(RegisterRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);



}
