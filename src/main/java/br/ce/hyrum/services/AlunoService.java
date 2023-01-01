package br.ce.hyrum.services;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.ce.hyrum.dtos.aluno.AlunoRequestDto;
import br.ce.hyrum.dtos.aluno.AlunoResponseDto;
import br.ce.hyrum.mappers.AlunoMapper;
import br.ce.hyrum.models.Aluno;
import br.ce.hyrum.models.Professor;
import br.ce.hyrum.repositories.AlunoRepository;

@ApplicationScoped
public class AlunoService {

    @Inject
    AlunoRepository alunoRepository;

    public List<AlunoResponseDto> getAlunos() {
        return AlunoMapper.toAlunosResponseDto(alunoRepository.all());
    }

    @Transactional
    public AlunoResponseDto save(AlunoRequestDto alunoRequestDto) {

        // Validação

        // Tranforma de DTO de request para a entidade
        Aluno aluno = AlunoMapper.fromAlunoRequestDto(alunoRequestDto);

        // Transforma de entidade para o DTO de response
        return AlunoMapper.toAlunoResponseDto(alunoRepository.save(aluno));
    }
    
    @Transactional
    public AlunoResponseDto update(Long id, AlunoRequestDto alunoRequestDto) {

        // Tranforma de DTO de request para a entidade
        Aluno aluno = AlunoMapper.fromAlunoRequestDto(alunoRequestDto);

        // Transforma de entidade para o DTO de response
        return AlunoMapper.toAlunoResponseDto(alunoRepository.update(id, aluno));
    }

    @Transactional
    public void delete(Long id) {
        
        alunoRepository.delete(id);
    }

    @Transactional
    public void setTutor(Long id, Long idProfessor) {

        Optional<Aluno> alunoOptional = Aluno.findByIdOptional(id);

        if (!alunoOptional.isPresent()) {
            new RuntimeException("Aluno não encontrado.");
        }

        Optional<Professor> professorOptional = Professor.findByIdOptional(idProfessor);

        if (!professorOptional.isPresent()) {
            new RuntimeException("Professor não encontrado.");
        }

        var aluno = alunoOptional.get();
        var professor = professorOptional.get();

        aluno.setTutor(professor);
        aluno.persistAndFlush();
    }   
}
