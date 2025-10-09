package com.example.cadastroFuncionario.repository;

import com.example.cadastroFuncionario.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> , JpaSpecificationExecutor<Funcionario> {

    Optional<Funcionario> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Funcionario> findByCpf(String email);
    boolean existsByCpf(String cpf);


    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByCpfAndIdNot(String cpf, Long id);

}
