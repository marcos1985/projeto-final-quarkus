package br.ce.hyrum.services;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.ce.hyrum.dtos.ProfessorDto;
import br.ce.hyrum.repositories.ProfessorRepository;

@ApplicationScoped
public class ProfessorService {
    
    @Inject
    ProfessorRepository professorRepository;
    
    public ProfessorDto salvar(ProfessorDto professor) {
        return professorRepository.save(professor);
    }

    public List<ProfessorDto> getProfessores() {
        return professorRepository.getAll();
    }

    public ProfessorDto atualizar(Integer id, ProfessorDto professor) {
        
        // Valida se os ids informados são iguais.
        if (!professor.getId().equals(id)) {
            throw new RuntimeException("Ids divergentes!");
        }
        
        // Valida se o professor existe na base de dados.
        if (Objects.isNull(professorRepository.getById(id))) {
            throw new RuntimeException("Professor não encontrado!");
        }

        professor.setId(id);

        return professorRepository.save(professor);
    }

    public void deletar(Integer id) {
        
        // Valida se o professor existe na base de dados.
        if (Objects.isNull(professorRepository.getById(id))) {
            throw new RuntimeException("Professor não encontrado!");
        }

        professorRepository.delete(id);

    }

}
