package com.api.vehicles.exceptions;

//tratamento de erros para retorno de mensagens
public class VehicleDataExceptions extends RuntimeException{
    
    public VehicleDataExceptions(String msg) {
        super(msg);
    }
}
