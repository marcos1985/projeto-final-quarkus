package br.ce.hyrum.repositories;


import javax.enterprise.context.ApplicationScoped;
import br.ce.hyrum.models.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Long> {

}
