package br.com.academy.service;

import java.util.List;

import br.com.academy.dto.ProfessorDTO;

public interface ProfessorService {
	void save(ProfessorDTO professorDTO);
	
	ProfessorDTO findById(Long id);
	
	List<ProfessorDTO> findAll();
	
	ProfessorDTO update(ProfessorDTO professorUpdate, ProfessorDTO professorAlvo) throws Exception;
	
	void deleteById(Long id);
}
