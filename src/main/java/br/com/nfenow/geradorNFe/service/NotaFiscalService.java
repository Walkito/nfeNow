package br.com.nfenow.geradorNFe.service;

import br.com.nfenow.geradorNFe.Utils;
import br.com.nfenow.geradorNFe.model.NotaFiscal;
import br.com.nfenow.geradorNFe.model.NotaFiscalSpecification;
import br.com.nfenow.geradorNFe.model.repository.NotaFiscalRepository;
import br.com.nfenow.geradorNFe.service.exception.CNPJNotValidException;
import br.com.nfenow.geradorNFe.service.exception.NFNotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {
    @Autowired
    NotaFiscalRepository repository;

    public ResponseEntity<Object> getAllNotas(int actualPage, int itemsPerPage){
        try {
            if(itemsPerPage <= 0){
                itemsPerPage = 10;
            }
            Pageable page = createPage(actualPage, itemsPerPage);
            return repository.findAll(page).getNumberOfElements() != 0 ?
                    new ResponseEntity<>(repository.findAll(page).getContent(), HttpStatus.OK) :
                    new NFNotFoundException().nullNFException();
        } catch (Exception e){
            throw e;
        }
    }

    public ResponseEntity<Object> getNotaFilter(String numeroNotaFiscal, String numeroSerie,
                                          String razaoSocial, String cnpj, String endereco, double valor,
                                          int actualPage, int itemsPerPage){
        try {
            Pageable pageable = createPage(actualPage, itemsPerPage);
            Specification<NotaFiscal> specification = NotaFiscalSpecification.filtrarPorCriterios(numeroNotaFiscal, numeroSerie, razaoSocial, cnpj, endereco, valor);
            if(repository.findAll(specification).isEmpty()){
                return new NFNotFoundException().nullNFException();
            } else {
                return new ResponseEntity<>(repository.findAll(specification),HttpStatus.OK);
            }
        } catch (Exception e){
            throw e;
        }
    }

    public ResponseEntity<Object> insertNotaFiscal(NotaFiscal notaFiscal){
        try {
            if(!Utils.validateCNPJ(notaFiscal.getCnpj())){
              return new CNPJNotValidException().cnpjNotValid();
            }
            notaFiscal.setNumeroNotaFiscal(inserNumeroNotaFiscal(notaFiscal.getNumeroSerie()));
            repository.save(notaFiscal);
            return new ResponseEntity<>("Nota Fiscal inserida com Sucesso!", HttpStatus.OK);
        } catch (Exception e){
            throw e;
        }
    }

    public ResponseEntity<Object> editNotaFiscal(NotaFiscal notaFiscal){
        try {
            if(!Utils.validateCNPJ(notaFiscal.getCnpj())){
                return new CNPJNotValidException().cnpjNotValid();
            }
            NotaFiscal actualNota = repository.searchById(notaFiscal.getId());
            if(actualNota == null){
                return new NFNotFoundException().nullNFException();
            } else {
                BeanUtils.copyProperties(notaFiscal, actualNota);
                repository.save(actualNota);
                return new ResponseEntity<>("Nota Fiscal Editada com Sucesso!", HttpStatus.OK);
            }
        } catch (Exception e){
            throw e;
        }
    }

    public ResponseEntity<Object> deleteNotaFiscal(String numeroNota, String numeroSerie){
        try {
            NotaFiscal notaFiscal = repository.findByNumeroNotaFiscalAndNumeroSerie(numeroNota, numeroSerie);
            if(notaFiscal == null){
                return new NFNotFoundException().nullNFException();
            } else {
                repository.delete(notaFiscal);
                return new ResponseEntity<>("Nota Fiscal excluída com sucesso!", HttpStatus.OK);
            }
        } catch (Exception e){
            throw e;
        }
    }

    private Pageable createPage(int actualPage, int itemsPerPage){
        return PageRequest.of(actualPage, itemsPerPage);
    }

    private String inserNumeroNotaFiscal(String numSerie){
        int temporaryNumeroNotaFiscal = 0;
        if(repository.searchMaxNumeroNota(numSerie) != null){
            temporaryNumeroNotaFiscal = repository.searchMaxNumeroNota(numSerie) + 1;
            String newNumeroNotaFiscal = String.valueOf(temporaryNumeroNotaFiscal);
            return addZerosLeft(newNumeroNotaFiscal);
        } else {
            return addZerosLeft("1");
        }
    }

    private String addZerosLeft(String numeroNotaFiscal){
        return String.format("%" + 9 + "s", numeroNotaFiscal).replace(' ', '0');
    }
}
