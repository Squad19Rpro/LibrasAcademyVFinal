package br.com.academy.service;

import java.util.List;

import br.com.academy.dto.FaleConoscoDTO;

public interface FaleConoscoService {
	void save(FaleConoscoDTO faleConoscoDTO);
	
	FaleConoscoDTO findById(Long id);
	
	List<FaleConoscoDTO> findAll();
	
	FaleConoscoDTO update(FaleConoscoDTO faleConoscoUpdate, FaleConoscoDTO faleConoscoAlvo) throws Exception;
	
	void deleteById(Long id);
}
