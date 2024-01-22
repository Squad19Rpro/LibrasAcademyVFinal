package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.entidades.Cargo;
import br.com.academy.repository.CargoRepository;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
    private CargoRepository cargoRepository;
	
	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cargo/home");
        modelAndView.addObject("cargos", cargoRepository.findAll());

        return modelAndView;
    }
	
	@GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cargo/formulario");
        modelAndView.addObject("cargo", new Cargo());

        return modelAndView;
    }
	
	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cargo/editCargo");
        modelAndView.addObject("cargo", cargoRepository.getReferenceById(id));

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String salvar(Cargo cargo) {
        cargoRepository.save(cargo);

        return "redirect:/cargos";
    }
    
    @PostMapping("/{id}/editar")
    public String edicao(Cargo cargo, @PathVariable Long id) {
    	cargoRepository.save(cargo);
    	return "redirect:/cargos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        cargoRepository.deleteById(id);

        return "redirect:/cargos";
    }
}
