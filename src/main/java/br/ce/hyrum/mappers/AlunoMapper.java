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
        
        var alunoResponse = new AlunoResponseDto();

        alunoResponse.setId(aluno.getId());
        alunoResponse.setNome(aluno.getNome());
        alunoResponse.setEmail(aluno.getEmail());

        return alunoResponse;
    }
    
    public static List<AlunoResponseDto> toAlunosResponseDto(List<Aluno> alunos) {

        return alunos.stream()
                .map(aluno -> AlunoMapper.toAlunoResponseDto(aluno))
                .toList();
    }

}
