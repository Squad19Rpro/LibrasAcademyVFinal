package br.com.academy.service;

import java.util.List;

import br.com.academy.dto.CargoDTO;

public interface CargoService {
	void save(CargoDTO cargoDTO);
	
	CargoDTO findById(Long id);
	
	List<CargoDTO> findAll();
	
	CargoDTO update(CargoDTO cargoUpdate, CargoDTO cargoAlvo) throws Exception;
	
	void deleteById(Long id);
}
