package br.com.academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.academy.entidades.Aluno;
import br.com.academy.entidades.CursoAluno;
import br.com.academy.entidades.Cursos;

public interface CursoAlunoRepository extends JpaRepository <CursoAluno, Long> {
	@Query("SELECT ca.curso FROM CursoAluno ca WHERE ca.aluno.id = :alunoId")
    List<Cursos> findCursosByAlunoId(@Param("alunoId") Long alunoId);
	
	@Query("SELECT ca.aluno FROM CursoAluno ca WHERE ca.curso.id = :cursoId")
    List<Aluno> findAlunosByCursoId(@Param("cursoId") Long cursoId);
}
