package br.ce.hyrum.services;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.ce.hyrum.dtos.aluno.AlunoRequestDto;
import br.ce.hyrum.dtos.aluno.AlunoResponseDto;
import br.ce.hyrum.mappers.AlunoMapper;
import br.ce.hyrum.models.Aluno;
import br.ce.hyrum.models.Professor;
import br.ce.hyrum.repositories.AlunoRepository;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class AlunoService {

    
    private AlunoRepository alunoRepository;

    public AlunoResponseDto findById(Long id) {

        Optional<Aluno> alunoOptional = alunoRepository.findByIdOptional(id);

        if(!alunoOptional.isPresent()) {
            throw new RuntimeException("Aluno não encontrado.");
        }

        Aluno aluno = alunoOptional.get();

        return AlunoMapper.toAlunoResponseDto(aluno);
    }

    public List<AlunoResponseDto> findAll() {

        List<Aluno> alunos = alunoRepository.findAll().list();

        return alunos.stream()
                    .map(aluno -> AlunoMapper.toAlunoResponseDto(aluno))
                    .toList();
    }

    @Transactional
    public AlunoResponseDto save(@Valid AlunoRequestDto alunoRequestDto) {

        // Validação

        // Tranforma de DTO de request para a entidade
        Aluno aluno = AlunoMapper.fromAlunoRequestDto(alunoRequestDto);
        alunoRepository.persistAndFlush(aluno);

        // Transforma de entidade para o DTO de response
        return AlunoMapper.toAlunoResponseDto(aluno);
    }
    
    @Transactional
    public AlunoResponseDto update(Long id, @Valid AlunoRequestDto alunoRequestDto) {

        Optional<Aluno> alunoOptional = alunoRepository.findByIdOptional(id);

        if(!alunoOptional.isPresent()) {
            throw new RuntimeException("Aluno não encontrado.");
        }

        Aluno aluno = alunoOptional.get();

        aluno.setNome(alunoRequestDto.getNome());
        aluno.setEmail(alunoRequestDto.getEmail());

        alunoRepository.persistAndFlush(aluno);

        return AlunoMapper.toAlunoResponseDto(aluno);
        
    }

    @Transactional
    public void delete(Long id) {
        
        Optional<Aluno> alunoOptional = alunoRepository.findByIdOptional(id);

        if(!alunoOptional.isPresent()) {
            throw new RuntimeException("Aluno não encontrado.");
        }

        Aluno aluno = alunoOptional.get();

        alunoRepository.delete(aluno);
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

        alunoRepository.persistAndFlush(aluno);
    }   
}
