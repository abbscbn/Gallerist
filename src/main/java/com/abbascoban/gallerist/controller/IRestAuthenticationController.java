package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.*;

public interface IRestAuthenticationController {

   public RootEntity<DtoUser> register(RegisterRequest input);

   public RootEntity<AuthResponse> authenticate(AuthRequest input);

   public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
