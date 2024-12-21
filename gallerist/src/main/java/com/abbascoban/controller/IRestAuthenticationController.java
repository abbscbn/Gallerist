package com.abbascoban.controller;

import com.abbascoban.dto.AuthRequest;
import com.abbascoban.dto.AuthResponse;
import com.abbascoban.dto.DtoUser;
import com.abbascoban.dto.RefreshTokenRequest;
import com.abbascoban.model.User;

public interface IRestAuthenticationController {
	
	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

}
