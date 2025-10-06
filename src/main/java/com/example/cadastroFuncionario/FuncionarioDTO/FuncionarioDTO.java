package com.example.cadastroFuncionario.FuncionarioDTO;

import jakarta.validation.constraints.*;

import java.util.Date;

public class FuncionarioDTO {


    private Long id;

    @NotBlank(message = "Nome do funcionário é obrigatório")
    @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
    private String nome;

    @NotBlank(message = "Email do funcionário é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 50, message = "O email deve ter no máximo 50 caracteres")
    private String email;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @NotBlank(message = "cpf do funcionário é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O cpf deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private Date dataNasc;

    @NotNull(message = "Salário do funcionário é obrigatório")
    @Positive(message = "O salário deve ser maior que zero")
    private Double salario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome do funcionário é obrigatório") @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome do funcionário é obrigatório") @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Email do funcionário é obrigatório") @Email(message = "Email inválido") @Size(max = 50, message = "O email deve ter no máximo 50 caracteres") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email do funcionário é obrigatório") @Email(message = "Email inválido") @Size(max = 50, message = "O email deve ter no máximo 50 caracteres") String email) {
        this.email = email;
    }

    public @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres") String telefone) {
        this.telefone = telefone;
    }

    public @NotBlank(message = "cpf do funcionário é obrigatório") @Pattern(regexp = "\\d{11}", message = "O cpf deve conter exatamente 11 dígitos numéricos") String getcpf() {
        return cpf;
    }

    public void setcpf(@NotBlank(message = "cpf do funcionário é obrigatório") @Pattern(regexp = "\\d{11}", message = "O cpf deve conter exatamente 11 dígitos numéricos") String cpf) {
        this.cpf = cpf;
    }

    public @NotNull(message = "Data de nascimento é obrigatória") @Past(message = "A data de nascimento deve ser anterior à data atual") Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(@NotNull(message = "Data de nascimento é obrigatória") @Past(message = "A data de nascimento deve ser anterior à data atual") Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public @NotNull(message = "Salário do funcionário é obrigatório") @Positive(message = "O salário deve ser maior que zero") Double getSalario() {
        return salario;
    }

    public void setSalario(@NotNull(message = "Salário do funcionário é obrigatório") @Positive(message = "O salário deve ser maior que zero") Double salario) {
        this.salario = salario;
    }
}
