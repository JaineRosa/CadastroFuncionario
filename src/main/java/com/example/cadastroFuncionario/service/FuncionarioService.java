package com.example.cadastroFuncionario.service;

import com.example.cadastroFuncionario.FuncionarioDTO.CargoUpdateDTO;
import com.example.cadastroFuncionario.FuncionarioDTO.FuncionarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FuncionarioService {

    FuncionarioDTO criar(FuncionarioDTO dto);
    FuncionarioDTO buscarPorId(Long id);
    Page<FuncionarioDTO> listarTodos(String cargo, String nacionalidade, Double salarioMin, Date dataContratacaoInicio, Date dataContratacaoFim, Pageable pageable);
    void deletar(Long id);
    FuncionarioDTO atualizar(Long id, FuncionarioDTO dto);
    FuncionarioDTO mudarCargo(Long id, CargoUpdateDTO cargoUpdateDTO);

}
