package br.com.academy.service;

import java.util.List;

import br.com.academy.dto.CursosDTO;

public interface CursosService {
	void save(CursosDTO cursosDTO);
	
	CursosDTO findById(Long id);
	
	List<CursosDTO> findAll();
	
	CursosDTO update(CursosDTO cursosUpdate, CursosDTO cursosAlvo) throws Exception;
	
	void deleteById(Long id);
}
