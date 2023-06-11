package com.academic.useracademic.controllers;

import com.academic.useracademic.entities.Professor;
import com.academic.useracademic.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public List<Professor> findAll() {
        List<Professor> result = repository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public Professor findById(@PathVariable Long id) {
        Professor result = repository.findById(id).get();
        return result;
    }

    @PostMapping
    public Professor insert(@RequestBody Professor professor) {
        Professor result = repository.save(professor);
        return result;
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public Professor update(@PathVariable Long id, @RequestBody Professor updateProfessor) {
        Professor existingProfessor = repository.findById(id).get();
        existingProfessor.updateFields(updateProfessor);
        return repository.save(existingProfessor);
    }
}
