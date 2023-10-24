package br.com.nfenow.geradorNFe.service.exception;

import br.com.nfenow.geradorNFe.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ExceptionConstruction {
    protected ResponseEntity<Object> responseConstructor(HttpStatus status, String message, String details){
        ErrorResponse response = new ErrorResponse(status.value(), message, details);
        return new ResponseEntity<>(response, status);
    }


}