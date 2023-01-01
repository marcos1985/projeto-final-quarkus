package br.ce.hyrum.dtos.aluno;

import br.ce.hyrum.dtos.professor.ProfessorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDto {
    private Long id;
    private String nome;
    private String email;

    private ProfessorResponseDto tutor;
}
