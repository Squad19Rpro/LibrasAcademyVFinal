package br.com.academy.service;

import java.util.List;

import br.com.academy.dto.AlunoDTO;

public interface AlunoService {
	void save(AlunoDTO alunoDTO);
	
	AlunoDTO findById(Long id);
	
	List<AlunoDTO> findAll();
	
	AlunoDTO update(AlunoDTO alunoUpdate, AlunoDTO alunoAlvo) throws Exception;
	
	void deleteById(Long id);
}
