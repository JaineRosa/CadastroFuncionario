package com.example.cadastroFuncionario.specification;
import com.example.cadastroFuncionario.model.Funcionario;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionarioSpecification {
    public static Specification<Funcionario> comFiltros(
            String cargo,
            String nacionalidade,
            Double salarioMin,
            Date dataContratacaoInicio,
            Date dataContratacaoFim
    ) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (cargo != null && !cargo.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("cargo")), cargo.toLowerCase()));
                System.out.println("Filtro Cargo Ativo: " + cargo);
            }

            if (nacionalidade != null && !nacionalidade.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("nacionalidade")), nacionalidade.toLowerCase()));
                System.out.println("Filtro Nacionalidade Ativo: " + nacionalidade);
            }

            if (salarioMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("salario"), salarioMin));
                System.out.println("Filtro Salário Mínimo Ativo: " + salarioMin);
            }

            if (dataContratacaoInicio != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataContratacao"), dataContratacaoInicio));
            }

            if (dataContratacaoFim != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataContratacao"), dataContratacaoFim));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}