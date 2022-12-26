package br.ce.hyrum.dtos;

public class ProfessorDto {
    
    private Integer id;
    private String nome;
    private String titulo;
    private char sexo;
    

    public ProfessorDto() {
    }


    public ProfessorDto(Integer id, String nome, String titulo, char sexo) {
        this.id = id;
        this.nome = nome;
        this.titulo = titulo;
        this.sexo = sexo;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public char getSexo() {
        return sexo;
    }


    public void setSexo(char sexo) {
        this.sexo = sexo;
    }


    
}
