package br.ce.hyrum.dtos.aluno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDto {
    private Long id;
    private String nome;
    private String email;
}
