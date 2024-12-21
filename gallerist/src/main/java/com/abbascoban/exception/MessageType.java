package com.abbascoban.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORDS_EXIST("1004","Kayıt Bulunamadı"),
	TOKEN_IS_EXPİRED("1005", "TOKEN süresi bitmiştir"),
	USERNAME_NOT_FOUND("1006","Kullanıcı bulunamadı"),
	USERNAME_OR_PASSWORD_INVALİD("1007","Kullanıcı adı veya şifre geçersiz"),
	REFRESH_TOKEN_NOT_FOUND("1008","Refresh token bulunamadı"),
	REFRESH_TOKEN_IS_EXPIRED("1009","Refresh Tokenin süresi bitmiştir"),
	CURRENCY_RATES_IS_OCCURED("1010","Dövüz kuru alınamadı"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","Yetersiz Bakiye"),
	CAR_STATUS_IS_ALREADY_SALED("1012","Araba daha önceden satılmıştır"),
	GENERAL_EXCEPTION("9999","Genel bir hata oluştu");
	
	private String code;
	
	private String message;
	
	 MessageType(String code, String message) {
		
		this.code= code;
		
		this.message= message;
	}
	
	
	

}
