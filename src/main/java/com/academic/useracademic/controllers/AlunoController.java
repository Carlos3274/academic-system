package com.academic.useracademic.controllers;

import com.academic.useracademic.entities.Aluno;
import com.academic.useracademic.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;
    @GetMapping
    public List<Aluno> findAll() {
        List<Aluno> result = repository.findAll();
        return result;
    }
    @GetMapping(value = "/{id}")
    public Aluno findById(@PathVariable Long id) {
        Aluno result = repository.findById(id).get();
        return result;
    }
    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno) {
        Aluno result = repository.save(aluno);
        return result;
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PutMapping(value = "/{id}")
    public Aluno update(@PathVariable Long id, @RequestBody Aluno updatedAluno) {
        Aluno existingAluno = repository.findById(id).get();
        existingAluno.updateFields(updatedAluno);
        return repository.save(existingAluno);
    }
}
