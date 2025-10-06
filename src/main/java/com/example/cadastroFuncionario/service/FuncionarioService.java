package com.example.cadastroFuncionario.service;

import com.example.cadastroFuncionario.FuncionarioDTO.FuncionarioDTO;

import java.util.List;

public interface FuncionarioService {

    FuncionarioDTO criar(FuncionarioDTO dto);
    FuncionarioDTO buscarPorId(Long id);
    List<FuncionarioDTO> listarTodos();
    void deletar(Long id);


}
