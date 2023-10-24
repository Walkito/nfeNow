package br.com.nfenow.geradorNFe.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NFNotFoundException extends ExceptionConstruction {

    public ResponseEntity<Object> nullNFException(){
        return responseConstructor(HttpStatus.valueOf(404), "Erro ao buscar Nota(s) Fiscal(is)", "Nota(s) Fiscal(is) não encontrada(s).");
    }
}
