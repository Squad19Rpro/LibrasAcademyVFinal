package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.academy.dto.CargoDTO;
import br.com.academy.entidades.Cargo;
import br.com.academy.repository.CargoRepository;
import br.com.academy.service.CargoService;

public class CargoServiceImpl implements CargoService{
	
    @Autowired
    private CargoRepository cargoRepository;

	@Override
	public void save(CargoDTO cargoDTO) {
		Cargo cargo = new Cargo(cargoDTO);
        cargoRepository.save(cargo);	
	}

	@Override
	public CargoDTO findById(Long id) {
        return new CargoDTO(cargoRepository.findById(id).get());
	}

	@Override
	public List<CargoDTO> findAll() {
        List<Cargo> cargos = cargoRepository.findAll();
        return cargos.stream().map(CargoDTO::new).toList();
	}

	@Override
	public CargoDTO update(CargoDTO cargoUpdate, CargoDTO cargoAlvo) throws Exception {
        if (cargoRepository.equals(cargoUpdate.getNome())) {
            throw new Exception("Cargo já existe");
        }
        cargoUpdate.setNome(cargoUpdate.getNome() != null ? cargoUpdate.getNome() : cargoAlvo.getNome());

        Cargo cargo = new Cargo(cargoUpdate);
        return new CargoDTO(cargoRepository.save(cargo));
	}

	@Override
	public void deleteById(Long id) {
        Optional<Cargo> optionalCargo = cargoRepository.findById(id);

        if (optionalCargo.isPresent()) {
            // O professor foi encontrado, pode deletar
        	Cargo cargo = optionalCargo.get();
        	cargoRepository.delete(cargo);
        } else {
            // Cargo não encontrado, lançar exceção ou lidar de outra forma
            throw new IllegalArgumentException("Cargo não encontrado");
        }
	}

}
