package br.com.nfenow.geradorNFe.model.repository;

import br.com.nfenow.geradorNFe.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer>, JpaSpecificationExecutor<NotaFiscal> {

    public NotaFiscal searchById(int id);

    public NotaFiscal findByNumeroNotaFiscalAndNumeroSerie(String numeroNotaFiscal, String numeroSerie);

    @Query("SELECT MAX(CAST(nf.numeroNotaFiscal AS INTEGER)) FROM NotaFiscal nf WHERE nf.numeroSerie = :numSerie")
    public Integer searchMaxNumeroNota(@Param("numSerie") String numeroSerie);
}
