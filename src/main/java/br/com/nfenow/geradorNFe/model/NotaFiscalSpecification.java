package br.com.nfenow.geradorNFe.model;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class NotaFiscalSpecification {
    public static Specification<NotaFiscal> filtrarPorCriterios(String numeroNotaFiscal, String numeroSerie,
                                                                String razaoSocial, String cnpj,
                                                                String endereco, double valor) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (numeroNotaFiscal != null && !numeroNotaFiscal.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("numeroNotaFiscal"), numeroNotaFiscal));
            }

            if (numeroSerie != null && !numeroSerie.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("numeroSerie"), numeroSerie));
            }

            if (razaoSocial != null && !razaoSocial.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("razaoSocial"), razaoSocial));
            }

            if (cnpj != null && !cnpj.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("cnpj"), cnpj));
            }

            if (endereco != null && !endereco.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("endereco"), endereco));
            }

            if (valor != 0){
                predicates.add(criteriaBuilder.equal(root.get("valor"), valor));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
