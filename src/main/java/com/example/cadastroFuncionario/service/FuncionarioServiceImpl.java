package com.example.cadastroFuncionario.service;

import com.example.cadastroFuncionario.FuncionarioDTO.CargoUpdateDTO;
import com.example.cadastroFuncionario.FuncionarioDTO.FuncionarioDTO;
import com.example.cadastroFuncionario.excepetions.ResourceNotFoundException;
import com.example.cadastroFuncionario.model.Funcionario;
import com.example.cadastroFuncionario.repository.FuncionarioRepository;
import com.example.cadastroFuncionario.specification.FuncionarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
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
        dto.setCpf(f.getCpf());
        dto.setDataNasc(f.getDataNasc());
        dto.setSalario(f.getSalario());
        dto.setCargo(f.getCargo());
        dto.setDataContratacao((f.getDataContratacao()));
        return dto;
    }

    private Funcionario toEntity(FuncionarioDTO dto) {
        Funcionario f = new Funcionario();
        f.setNome(dto.getNome());
        f.setEmail(dto.getEmail());
        f.setTelefone(dto.getTelefone());
        f.setCpf(dto.getCpf());
        f.setDataNasc(dto.getDataNasc());
        f.setSalario(dto.getSalario());
        f.setCargo(dto.getCargo());
        f.setDataContratacao((dto.getDataContratacao()));
        return f;
    }


    @Override
    public FuncionarioDTO criar(FuncionarioDTO dto) {
        if (funcionarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado para outro funcionário");
        }

        if (funcionarioRepository.existsByCpf(dto.getCpf())) {
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
    public Page<FuncionarioDTO> listarTodos (
            String cargo,
            String nacionalidade,
            Double salarioMin,
            Date dataContratacaoInicio,
            Date dataContratacaoFim,
            Pageable pageable
    ) {
        var spec = FuncionarioSpecification.comFiltros(cargo, nacionalidade, salarioMin,dataContratacaoInicio, dataContratacaoFim);
        return funcionarioRepository.findAll(spec,pageable)
                .map(this::toDto);

    }

    @Override
    public void deletar(Long id) {
        if(!funcionarioRepository.existsById(id)){
            throw  new ResourceNotFoundException("Pagante não encontrado com o id " +id);
        }
        funcionarioRepository.deleteById(id);
    }


    @Override
    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        Funcionario existe = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o id " + id));

        // Verifica se o e-mail já pertence a outro funcionário
        if (funcionarioRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new IllegalArgumentException("Email já cadastrado para outro funcionário");
        }

        // Verifica se o CPF já pertence a outro funcionário
        if (funcionarioRepository.existsByCpfAndIdNot(dto.getCpf(), id)) {
            throw new IllegalArgumentException("CPF já cadastrado para outro funcionário");
        }

        // Atualiza os dados
        existe.setNome(dto.getNome());
        existe.setEmail(dto.getEmail());
        existe.setTelefone(dto.getTelefone());
        existe.setCpf(dto.getCpf());
        existe.setDataNasc(dto.getDataNasc());
        existe.setSalario(dto.getSalario());
        existe.setCargo(dto.getCargo());
        existe.setDataContratacao(dto.getDataContratacao());

        Funcionario atualizado = funcionarioRepository.save(existe);

        return toDto(atualizado);
    }

    @Override
    public FuncionarioDTO mudarCargo(Long id, CargoUpdateDTO cargoUpdateDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));

        funcionario.setCargo(cargoUpdateDTO.getCargo());
        funcionario.setSalario(cargoUpdateDTO.getSalario());

        Funcionario atualizado = funcionarioRepository.save(funcionario);

        return toDto(atualizado);
    }

}
