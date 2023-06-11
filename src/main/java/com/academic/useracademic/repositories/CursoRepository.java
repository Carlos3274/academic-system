package com.academic.useracademic.repositories;

import com.academic.useracademic.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
