package br.com.nfenow.geradorNFe.controller;

import br.com.nfenow.geradorNFe.model.NotaFiscal;
import br.com.nfenow.geradorNFe.service.NotaFiscalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/notas")
public class NotaFiscalController {
    @Autowired
    NotaFiscalService service;

    @GetMapping
    public ResponseEntity<Object> getAllNotas(@RequestParam(name = "actualPage", defaultValue = "0")
                                              int actualPage,
                                              @RequestParam(name = "itemsPerPage", defaultValue = "10")
                                              int itemsPerPage){
        return service.getAllNotas(actualPage, itemsPerPage);
    }

    @GetMapping(path = "/nota")
    public ResponseEntity<Object> getNotaFilter(@RequestParam(name = "numNF", required = false)
                                            String numeroNotaFiscal,
                                          @RequestParam(name = "numSerie", required = false)
                                            String numeroSerie,
                                          @RequestParam(name = "razaoSocial", required = false)
                                            String razaoSocial,
                                          @RequestParam(name = "cnpj", required = false)
                                            String cnpj,
                                          @RequestParam(name = "endereco", required = false)
                                            String endereco,
                                          @RequestParam(name = "valor", defaultValue = "0.00")
                                            double valor,
                                          @RequestParam(name = "actualPage", defaultValue = "0")
                                            int acutalPage,
                                          @RequestParam(name = "itemsPerPage", defaultValue = "10")
                                            int itemsPerPage){
        return service.getNotaFilter(numeroNotaFiscal, numeroSerie, razaoSocial, cnpj,
                                     endereco, valor, acutalPage, itemsPerPage);
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<Object> insertNotaFiscal(@RequestBody @Valid NotaFiscal notaFiscal){
        return service.insertNotaFiscal(notaFiscal);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<Object> editNotaFiscal(@RequestBody @Valid NotaFiscal notaFiscal){
        return service.editNotaFiscal(notaFiscal);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteNotaFiscal(@RequestParam(name = "numNota") String numeroNotaFiscal,
                                                   @RequestParam(name = "numSerie") String numSerie){
        return service.deleteNotaFiscal(numeroNotaFiscal, numSerie);
    }
}
