package br.ce.hyrum;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;

import br.ce.hyrum.dtos.ProfessorDto;

@ApplicationScoped
public class Database {

    public static Integer sequenciaProfessores = 0;
    public static HashMap<Integer, ProfessorDto> professores = new HashMap<>();
    
}
