package com.academic.useracademic.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Curso() {
    }
    public void updateFields(Curso updatedCurso) {
        this.setName(updatedCurso.getName());
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
