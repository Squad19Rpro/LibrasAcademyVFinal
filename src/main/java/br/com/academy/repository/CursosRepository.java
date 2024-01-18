package br.com.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.academy.entidades.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Long> {

}
