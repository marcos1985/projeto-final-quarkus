package br.ce.hyrum.dtos.professor;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorRequestDto {
    
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    private String nome;
    
}
