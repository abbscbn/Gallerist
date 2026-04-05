package com.abbascoban.gallerist.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    GENERAL_EXCEPTION("9999","GENEL HATA"),
    TOKEN_IS_EXPIRED("8888","TOKEN SÜRESİ BİTMİŞTİR" ),
    USERNAME_OR_PASSWORD_INVALID("7777","kULLANICI ADI VEYA ŞİFRE HATALI"),
    REFRESH_TOKEN_NOT_FOUND("6666","REFRESH TOKEN BULUNAMADI"),
    REFRESH_TOKEN_IS_EXPIRED("5555","REFRESH TOKEN SÜRESİ DOLMUŞTUR"),
    CURRENCY_RATES_IS_OCCURED("4444","DÖVİZ KURLARINDA DEĞİŞİKLİKLER OLUŞMAKTADIR"),
    NO_RECORDS_EXIST("3333","KAYIT MEVCUT DEĞİL"),
    CAR_STATUS_IS_ALREADY_SALED("2222","ARABA ÇOKTAN SATILMIŞ"),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1111","MÜŞTERİ BAKİYESİ YETERLİ DEĞİL");


    private String code;

    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
