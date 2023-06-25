package com.academic.useracademic.controllers;

import com.academic.useracademic.entities.Curso;
import com.academic.useracademic.entities.Professor;
import com.academic.useracademic.repositories.CursoRepository;
import com.academic.useracademic.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public ProfessorController(ProfessorRepository professorRepository, CursoRepository cursoRepository) {
        this.professorRepository = professorRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public String getProfessores(Model model) {
        List<Professor> professores = professorRepository.findAll();
        model.addAttribute("professores", professores);
        return "professores";
    }

    @PostMapping("/{id}/delete")
    public String deleteProfessor(@PathVariable("id") Long id) {
        professorRepository.deleteById(id);
        return "redirect:/professores";
    }

    @PostMapping("/{id}/edit")
    public String editProfessor(@PathVariable("id") Long id, @RequestParam("name") String newName, @RequestParam("email") String newEmail, @RequestParam("cursoId") Long newCursoId) {
        Professor existingProfessor = professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid professor Id"));
        existingProfessor.setName(newName);
        existingProfessor.setEmail(newEmail);
        existingProfessor.setCurso(cursoRepository.findById(newCursoId).orElse(null));
        professorRepository.save(existingProfessor);
        return "redirect:/professores";
    }

    @PostMapping("/add")
    public String addProfessor(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("cursoId") Long cursoId) {
        Professor newProfessor = new Professor();
        newProfessor.setName(name);
        newProfessor.setEmail(email);
        newProfessor.setCurso(cursoRepository.findById(cursoId).orElse(null));
        professorRepository.save(newProfessor);
        return "redirect:/professores";
    }

    @ModelAttribute("cursos")
    public Iterable<Curso> getCursos() {
        return cursoRepository.findAll();
    }
}
