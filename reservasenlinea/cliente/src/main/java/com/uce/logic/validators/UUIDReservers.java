package com.uce.logic.validators;

public class UUIDReservers {


    //Es estatico porque lo voy a usar en todo el codigo, en este caso solo es un ejemplo
    public static String generateUUID(){
        return java.util.UUID.randomUUID().toString();
    }

}
