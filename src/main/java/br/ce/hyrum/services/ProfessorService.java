package br.ce.hyrum.services;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.ce.hyrum.dtos.professor.ProfessorRequestDto;
import br.ce.hyrum.dtos.professor.ProfessorResponseDto;
import br.ce.hyrum.mappers.ProfessorMapper;
import br.ce.hyrum.models.Professor;
import br.ce.hyrum.repositories.ProfessorRepository;

@ApplicationScoped
public class ProfessorService {
    
    @Inject
    ProfessorRepository professorRepository;
    
    @Transactional
    public ProfessorResponseDto save(@Valid ProfessorRequestDto professorRequestDto) {
        Professor professor = ProfessorMapper.fromProfessorRequestDto(professorRequestDto);
        return ProfessorMapper.toProfessorResponseDto(professorRepository.save(professor));
    }

    public List<ProfessorResponseDto> findAll() {
        return professorRepository.findAll();
    }

    @Transactional
    public ProfessorResponseDto update(Long id, ProfessorRequestDto professoreRequestDto) {
        
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

        professor = professorRepository.update(professor);

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

}
