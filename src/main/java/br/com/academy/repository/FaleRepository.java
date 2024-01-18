package br.com.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.academy.entidades.FaleConosco;

public interface FaleRepository extends JpaRepository<FaleConosco, Long> {

}
