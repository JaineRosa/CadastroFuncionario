package com.example.cadastroFuncionario.controller;

import com.example.cadastroFuncionario.FuncionarioDTO.CargoUpdateDTO;
import com.example.cadastroFuncionario.FuncionarioDTO.FuncionarioDTO;
import com.example.cadastroFuncionario.excepetions.ResourceNotFoundException;
import com.example.cadastroFuncionario.model.Funcionario;
import com.example.cadastroFuncionario.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> listarComFiltros(
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String nacionalidade,
            @RequestParam(required = false) Double salarioMin,
            Pageable pageable
    ) {
        return ResponseEntity.ok(funcionarioService.listarTodos(cargo, nacionalidade, salarioMin, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            FuncionarioDTO funcionario = funcionarioService.buscarPorId(id);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Funcionário não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criar(@Valid @RequestBody FuncionarioDTO dto){
        FuncionarioDTO criado =  funcionarioService.criar(dto);

        return ResponseEntity.created(URI.create("/funcionarios" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@Valid @PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        FuncionarioDTO atualizado = funcionarioService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cargo")
    public ResponseEntity<FuncionarioDTO> atualizarCargo(
            @PathVariable Long id,
            @RequestBody CargoUpdateDTO cargoUpdateDTO) {

        FuncionarioDTO atualizado = funcionarioService.mudarCargo(id, cargoUpdateDTO);
        return ResponseEntity.ok(atualizado);
    }
}
