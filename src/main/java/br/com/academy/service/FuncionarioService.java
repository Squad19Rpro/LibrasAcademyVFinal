package br.com.academy.service;

import java.util.List;

import br.com.academy.dto.FuncionarioDTO;

public interface FuncionarioService {
	void save(FuncionarioDTO funcionarioDTO);
	
	FuncionarioDTO findById(Long id);
	
	List<FuncionarioDTO> findAll();
	
	FuncionarioDTO update(FuncionarioDTO funcionarioUpdate, FuncionarioDTO funcionarioAlvo) throws Exception;
	
	void deleteById(Long id);
}
