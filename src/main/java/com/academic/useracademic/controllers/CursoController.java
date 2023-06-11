package com.academic.useracademic.controllers;

import com.academic.useracademic.entities.Curso;
import com.academic.useracademic.entities.Professor;
import com.academic.useracademic.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @GetMapping
    public List<Curso> findAll() {
        List<Curso> result = repository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public Curso findById(@PathVariable Long id) {
        Curso result = repository.findById(id).get();
        return result;
    }
    @PostMapping
    public Curso insert(@RequestBody Curso curso) {
        Curso result = repository.save(curso);
        return result;
    }
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public Curso update(@PathVariable Long id, @RequestBody Curso updatedCurso) {
        Curso existingCurso = repository.findById(id).get();
        existingCurso.updateFields(updatedCurso);
        return repository.save(existingCurso);
    }
}
