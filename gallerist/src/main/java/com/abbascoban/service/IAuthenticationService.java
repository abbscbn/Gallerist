package com.abbascoban.service;

import com.abbascoban.dto.AuthRequest;
import com.abbascoban.dto.AuthResponse;
import com.abbascoban.dto.DtoUser;
import com.abbascoban.dto.RefreshTokenRequest;

public interface IAuthenticationService {
	
	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
	

}
