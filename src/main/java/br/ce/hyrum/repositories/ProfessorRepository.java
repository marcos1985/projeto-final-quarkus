package br.ce.hyrum.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;

import br.ce.hyrum.Database;
import br.ce.hyrum.dtos.ProfessorDto;

@ApplicationScoped
public class ProfessorRepository {
    
    public ProfessorDto getById(Integer id) {
        
        var professor = Database.professores.get(id);

        return professor;
    }

    public List<ProfessorDto> getAll() {
        return new ArrayList<>(Database.professores.values());
    }

    public ProfessorDto save(ProfessorDto professor) {

        if (Objects.isNull(professor.getId())) {
            professor.setId(++Database.sequenciaProfessores);
        }

        Database.professores.put(professor.getId(), professor);

        return professor;
    }

    public void delete(Integer id) {
        Database.professores.remove(id);
    }

}
