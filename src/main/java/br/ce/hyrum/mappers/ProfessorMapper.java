package br.ce.hyrum.mappers;

import java.util.List;

import br.ce.hyrum.dtos.professor.ProfessorRequestDto;
import br.ce.hyrum.dtos.professor.ProfessorResponseDto;
import br.ce.hyrum.models.Professor;

public class ProfessorMapper {

    public static Professor fromProfessorRequestDto(ProfessorRequestDto professorDto) {

        Professor professor = Professor.builder()
                                .id(professorDto.getId())
                                .nome(professorDto.getNome())
                                .build();

        return professor;
    }

    public static ProfessorResponseDto toProfessorResponseDto(Professor professor) {

        var professorResponseDto = ProfessorResponseDto.builder()
                                            .id(professor.getId())
                                            .nome(professor.getNome())
                                            .build();

        return professorResponseDto;
    }


    public static List<ProfessorResponseDto> toProfessorResponseDtoList(List<Professor> professores) {

        return professores.stream()
                    .map(professor -> toProfessorResponseDto(professor))
                    .toList();
    }
    
}
