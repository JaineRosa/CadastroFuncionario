package com.example.cadastroFuncionario.service;

import com.example.cadastroFuncionario.FuncionarioDTO.FuncionarioDTO;
import com.example.cadastroFuncionario.excepetions.ResourceNotFoundException;
import com.example.cadastroFuncionario.model.Funcionario;
import com.example.cadastroFuncionario.repository.FuncionarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService{

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    private FuncionarioDTO toDto(Funcionario f) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(f.getId());
        dto.setNome(f.getNome());
        dto.setEmail(f.getEmail());
        dto.setTelefone(f.getTelefone());
        dto.setcpf(f.getcpf());
        dto.setDataNasc(f.getDataNasc());
        dto.setSalario(f.getSalario());
        return dto;
    }

    private Funcionario toEntity(FuncionarioDTO dto) {
        Funcionario f = new Funcionario();
        f.setNome(dto.getNome());
        f.setEmail(dto.getEmail());
        f.setTelefone(dto.getTelefone());
        f.setcpf(dto.getcpf());
        f.setDataNasc(dto.getDataNasc());
        f.setSalario(dto.getSalario());
        return f;
    }


    @Override
    public FuncionarioDTO criar(FuncionarioDTO dto) {
        if (funcionarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado para outro funcionário");
        }

        if (funcionarioRepository.existsByCpf(dto.getcpf())) {
            throw new IllegalArgumentException("cpf já cadastrado para outro funcionário");
        }

        Funcionario novoFuncionario = toEntity(dto);
        Funcionario salvo = funcionarioRepository.save(novoFuncionario);
        return toDto(salvo);
    }

    @Transactional(readOnly = true)
    @Override
    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o id " + id));
        return toDto(f);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FuncionarioDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        if(!funcionarioRepository.existsById(id)){
            throw  new ResourceNotFoundException("Pagante não encontrado com o id " +id);
        }
        funcionarioRepository.deleteById(id);
    }

}
