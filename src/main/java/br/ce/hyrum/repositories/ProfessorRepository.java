package br.ce.hyrum.repositories;


import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import br.ce.hyrum.dtos.aluno.AlunoResponseDto;
import br.ce.hyrum.dtos.professor.ProfessorResponseDto;
import br.ce.hyrum.mappers.AlunoMapper;
import br.ce.hyrum.mappers.ProfessorMapper;
import br.ce.hyrum.models.Professor;

@ApplicationScoped
public class ProfessorRepository {

    public Professor findById(long id) {

        Optional<Professor> professorOptional = Professor.findByIdOptional(id);

        if (professorOptional.isPresent()) {
            return professorOptional.get();
        }

        return null;
    }
    
    public List<ProfessorResponseDto> findAll() {

        List<Professor> professores = Professor.listAll();

        return professores.stream()
                    .map(professor -> ProfessorMapper.toProfessorResponseDto(professor))
                    .toList();
    }

    public Professor save(Professor professor) {

        professor.persistAndFlush();

        return professor;
    }

    public Professor update(Professor professor) {
        professor.persistAndFlush();
        return professor;
    }

    public List<AlunoResponseDto> tutorados(Integer id) {

        Optional<Professor> professorOptional = Professor.findByIdOptional(id);

        if (professorOptional.isPresent()) {
            var alunos = professorOptional.get().getAlunos();

            return alunos.stream()
                    .map(aluno -> AlunoMapper.toAlunoResponseDto(aluno))
                    .toList();
        }
        
        return null;
    }


    public void delete(Professor professor) {
        
        professor.delete();

    }
}
