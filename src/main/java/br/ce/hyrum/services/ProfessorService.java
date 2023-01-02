package br.ce.hyrum.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.ce.hyrum.dtos.aluno.AlunoResponseDto;
import br.ce.hyrum.dtos.professor.ProfessorRequestDto;
import br.ce.hyrum.dtos.professor.ProfessorResponseDto;
import br.ce.hyrum.mappers.AlunoMapper;
import br.ce.hyrum.mappers.ProfessorMapper;
import br.ce.hyrum.models.Aluno;
import br.ce.hyrum.models.Professor;
import br.ce.hyrum.repositories.ProfessorRepository;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class ProfessorService {

    private ProfessorRepository professorRepository;

    @Transactional
    public ProfessorResponseDto save(@Valid ProfessorRequestDto professorRequestDto) {
        
        Objects.requireNonNull(professorRequestDto, "Professor obrigatório!");
        
        Professor professor = ProfessorMapper.fromProfessorRequestDto(professorRequestDto);

        professorRepository.persistAndFlush(professor);

        return ProfessorMapper.toProfessorResponseDto(professor);
    }

    public List<ProfessorResponseDto> findAll() {

        List<Professor> professores = professorRepository.findAll().list();

        return professores.stream()
                    .map(professor -> ProfessorMapper.toProfessorResponseDto(professor))
                    .toList();
    }

    @Transactional
    public ProfessorResponseDto update(Long id, @Valid ProfessorRequestDto professoreRequestDto) {

        // Valida se os ids informados são iguais.
        if (!professoreRequestDto.getId().equals(id)) {
            throw new RuntimeException("Ids divergentes!");
        }

        Optional<Professor> professorOptional = Professor.findByIdOptional(id);

        if (!professorOptional.isPresent()) {
            throw new RuntimeException("Professor não encontrado.");
        }

        var professor = professorOptional.get();

        professor.setNome(professoreRequestDto.getNome());
        professor.setId(id);

        professorRepository.persistAndFlush(professor);
        
        return ProfessorMapper.toProfessorResponseDto(professor);
    }

    @Transactional
    public void delete(Long id) {

        Optional<Professor> professorOptional = Professor.findByIdOptional(id);

        if (!professorOptional.isPresent()) {
            throw new RuntimeException("Professor não encontrado.");
        }

        var professor = professorOptional.get();

        professorRepository.delete(professor);

    }

    public List<AlunoResponseDto> findTutorados(Long id) {

        Optional<Professor> professorOptional = Professor.findByIdOptional(id);

        if (!professorOptional.isPresent()) {
            throw new RuntimeException("Professor não encontrado.");
        }   

        var professor = professorOptional.get();

        return Aluno
                .stream("tutor = ?1", professor)
                .map(aluno -> (Aluno) aluno) 
                .map(aluno -> { 
                        aluno.setTutor(null);
                        return AlunoMapper.toAlunoResponseDto(aluno);
                }).toList();
                
    }

}
