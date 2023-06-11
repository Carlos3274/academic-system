package com.academic.useracademic.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_aluno")
public class Aluno  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int matricula;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Aluno() {
    }

    public void updateFields(Aluno updatedAluno) {
        this.setName(updatedAluno.getName());
        this.setEmail(updatedAluno.getEmail());
        this.setCurso(updatedAluno.getCurso());
        this.setMatricula(updatedAluno.getMatricula());
    }
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
