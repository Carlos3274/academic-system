package com.academic.useracademic.controllers;

import com.academic.useracademic.entities.Curso;
import com.academic.useracademic.entities.Aluno;
import com.academic.useracademic.repositories.CursoRepository;
import com.academic.useracademic.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public AlunoController(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public String getAlunos(Model model) {
        List<Aluno> alunos = alunoRepository.findAll();
        model.addAttribute("alunos", alunos);
        return "alunos";
    }

    @PostMapping("/{id}/delete")
    public String deleteAluno(@PathVariable("id") Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/alunos";
    }

    @PostMapping("/{id}/edit")
    public String editAluno(@PathVariable("id") Long id, @RequestParam("matricula") int newMatricula,
                            @RequestParam("name") String newName, @RequestParam("email") String newEmail,
                            @RequestParam("cursoId") Long newCursoId) {
        Aluno existingAluno = alunoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid aluno Id"));
        existingAluno.setMatricula(newMatricula);
        existingAluno.setName(newName);
        existingAluno.setEmail(newEmail);
        existingAluno.setCurso(cursoRepository.findById(newCursoId).orElse(null));
        alunoRepository.save(existingAluno);
        return "redirect:/alunos";
    }

    @PostMapping("/add")
    public String addAluno(@RequestParam("matricula") int matricula, @RequestParam("name") String name,
                           @RequestParam("email") String email, @RequestParam("cursoId") Long cursoId) {
        Aluno newAluno = new Aluno();
        newAluno.setMatricula(matricula);
        newAluno.setName(name);
        newAluno.setEmail(email);
        newAluno.setCurso(cursoRepository.findById(cursoId).orElse(null));
        alunoRepository.save(newAluno);
        return "redirect:/alunos";
    }

    @ModelAttribute("cursos")
    public Iterable<Curso> getCursos() {
        return cursoRepository.findAll();
    }
}
