package com.academic.useracademic.controllers;

import com.academic.useracademic.entities.Curso;
import com.academic.useracademic.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public String getCursos(Model model) {
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "cursos";
    }

    @PostMapping("/{id}/delete")
    public String deleteCurso(@PathVariable("id") Long id) {
        cursoRepository.deleteById(id);
        return "redirect:/cursos";
    }

    @PostMapping("/{id}/edit")
    public String editCurso(@PathVariable("id") Long id, @RequestParam("name") String newName) {
        Curso existingCurso = cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curso Id"));
        existingCurso.setName(newName);
        cursoRepository.save(existingCurso);
        return "redirect:/cursos";
    }

}

