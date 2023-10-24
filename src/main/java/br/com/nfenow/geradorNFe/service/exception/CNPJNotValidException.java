package br.com.nfenow.geradorNFe.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CNPJNotValidException extends ExceptionConstruction {
    public ResponseEntity<Object> cnpjNotValid(){
        return responseConstructor(HttpStatus.BAD_REQUEST, "Erro com o CNPJ!", "CNPJ inválido");
    }
}
