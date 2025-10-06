package com.example.cadastroFuncionario.repository;

import com.example.cadastroFuncionario.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Funcionario> findByCPF(String email);
    boolean existsByCPF(String CPF);

}
