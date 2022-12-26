package br.ce.hyrum.repositories;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import br.ce.hyrum.models.Aluno;

@ApplicationScoped
public class AlunoRepository {

    public List<Aluno> all() {
        return Aluno.listAll();
    }
    
    public Aluno save(Aluno aluno) {
        aluno.persistAndFlush();
        return aluno;
    }

    public Aluno update(Long id, Aluno aluno) {
        
        Optional<Aluno> optional = Aluno.findByIdOptional(id);

        if (optional.isPresent()) {
            
            Aluno entidade = optional.get();
            entidade.setId(aluno.getId());
            entidade.setNome(aluno.getNome());
            entidade.setEmail(aluno.getEmail());

            entidade.persistAndFlush();

            return entidade;
        }

        return null;
    }
}
