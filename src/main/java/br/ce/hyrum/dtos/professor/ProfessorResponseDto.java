package br.ce.hyrum.dtos.professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorResponseDto {
    private Long id;
    private String nome;
}
