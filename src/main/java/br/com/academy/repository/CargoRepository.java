package br.com.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academy.entidades.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
