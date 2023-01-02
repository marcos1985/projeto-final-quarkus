package br.ce.hyrum.repositories;

import javax.enterprise.context.ApplicationScoped;

import br.ce.hyrum.models.Aluno;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class AlunoRepository implements PanacheRepositoryBase<Aluno, Long> {

    
}
