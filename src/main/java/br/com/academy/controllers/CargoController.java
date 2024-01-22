package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.CargoDTO;
import br.com.academy.entidades.Cargo;
import br.com.academy.service.CargoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
    private CargoService cargoService;
	
	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cargo/home");
        modelAndView.addObject("cargos", cargoService.findAll());

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
        modelAndView.addObject("cargo", cargoService.findById(id));

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String salvar(CargoDTO cargo) {
        cargoService.save(cargo);

        return "redirect:/cargos";
    }
    
    @PostMapping("/{id}/editar")
    public String editar(CargoDTO cargoUpdate, @PathVariable Long id) throws Exception {
    	CargoDTO cargoAlvo = cargoService.findById(id);
    	cargoService.update(cargoUpdate, cargoAlvo);
    	return "redirect:/cargos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        cargoService.deleteById(id);

        return "redirect:/cargos";
    }
}
