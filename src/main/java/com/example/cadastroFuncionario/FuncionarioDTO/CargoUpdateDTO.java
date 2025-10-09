package com.example.cadastroFuncionario.FuncionarioDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CargoUpdateDTO {

    @NotNull(message = "Salário do funcionário é obrigatório")
    @Positive(message = "O salário deve ser maior que zero")
    private Double salario;

    @NotNull(message = "Cargo é obrigatório")
    @Size(max = 100, message = "O Cargo deve ter no máximo 100 caracteres")
    private String cargo;

    public @NotNull(message = "Salário do funcionário é obrigatório") @Positive(message = "O salário deve ser maior que zero") Double getSalario() {
        return salario;
    }

    public void setSalario(@NotNull(message = "Salário do funcionário é obrigatório") @Positive(message = "O salário deve ser maior que zero") Double salario) {
        this.salario = salario;
    }

    public @NotNull(message = "Cargo é obrigatório") @Size(max = 100, message = "O Cargo deve ter no máximo 100 caracteres") String getCargo() {
        return cargo;
    }

    public void setCargo(@NotNull(message = "Cargo é obrigatório") @Size(max = 100, message = "O Cargo deve ter no máximo 100 caracteres") String cargo) {
        this.cargo = cargo;
    }
}
