package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.ProfessorDTO;
import br.com.academy.entidades.Professor;
import br.com.academy.service.CargoService;
import br.com.academy.service.ProfessorService;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("professor/home");
        modelAndView.addObject("professores", professorService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("professor/detalhes");
        modelAndView.addObject("professor", professorService.findById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("professor/formulario");
        modelAndView.addObject("professor", new Professor());
        modelAndView.addObject("cargos", cargoService.findAll());      

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("professor/editProf");
        modelAndView.addObject("professor", professorService.findById(id));
        modelAndView.addObject("cargos", cargoService.findAll());

        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(ProfessorDTO professorDTO) {
        professorService.save(professorDTO);

        return "redirect:/professores";
    }

    @PostMapping("/{id}/editar")
    public String editar(ProfessorDTO professorUpdate, @PathVariable Long id) throws Exception {
        ProfessorDTO professorAlvo = professorService.findById(id);
        professorService.update(professorUpdate, professorAlvo);

        return "redirect:/professores";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
    	professorService.deleteById(id);

        return "redirect:/professores";
    }
    
}
