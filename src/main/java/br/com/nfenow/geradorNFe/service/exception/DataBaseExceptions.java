package br.com.nfenow.geradorNFe.service.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataBaseExceptions{
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> dataBaseGenericException(Exception ex) {
        ExceptionConstruction ec = new ExceptionConstruction();
        return ec.responseConstructor(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno do sistema",
                ex.getLocalizedMessage());
    }
}
