package br.ce.hyrum.mappers;
import java.util.List;

import br.ce.hyrum.dtos.aluno.AlunoRequestDto;
import br.ce.hyrum.dtos.aluno.AlunoResponseDto;
import br.ce.hyrum.models.Aluno;

public class AlunoMapper {

    public static Aluno fromAlunoRequestDto(AlunoRequestDto alunoRequestDto) {
        var aluno = Aluno.builder()
                            .id(alunoRequestDto.getId() != null ? alunoRequestDto.getId() : null)
                            .nome(alunoRequestDto.getNome())
                            .email(alunoRequestDto.getEmail())
                            .build();

        return aluno;
    }

    public static AlunoResponseDto toAlunoResponseDto(Aluno aluno) {
        
        return AlunoResponseDto.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .tutor( aluno.getTutor() != null ? (ProfessorMapper.toProfessorResponseDto(aluno.getTutor())) : null)
                .build();
    }
    
    public static List<AlunoResponseDto> toAlunosResponseDto(List<Aluno> alunos) {

        return alunos.stream()
                .map(aluno -> AlunoMapper.toAlunoResponseDto(aluno))
                .toList();
    }

}
