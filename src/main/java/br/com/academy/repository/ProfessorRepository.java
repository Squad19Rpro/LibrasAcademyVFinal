package br.com.academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.academy.entidades.Professor;

@EnableJpaRepositories
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findAll();

    @Query("select p from Professor p where p.cargo.nome = :cargo")
    List<Professor> buscarPorCargo(String cargo);

    @Query("select p from Professor p where p.cargo.nome <> :cargo")
    List<Professor> buscarPorCargoExceto(String cargo);

    List<Professor> findByCargoNome(String cargo);

    List<Professor> findByCargoNomeNot(String cargo);
    
    Optional<Professor> findByEmail(String email);
}
