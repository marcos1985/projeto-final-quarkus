package br.ce.hyrum.dtos.aluno;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequestDto {
    
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio.")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio.")
    private String email;
}