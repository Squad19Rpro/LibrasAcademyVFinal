package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.entidades.Professor;
import br.com.academy.repository.CargoRepository;
import br.com.academy.repository.ProfessorRepository;
import br.com.academy.utils.SenhaUtils;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("professor/home");
        modelAndView.addObject("professores", professorRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("professor/detalhes");
        modelAndView.addObject("professor", professorRepository.getReferenceById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("professor/formulario");
        modelAndView.addObject("professor", new Professor());
        modelAndView.addObject("cargos", cargoRepository.findAll());      

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("professor/editProf");
        modelAndView.addObject("professor", professorRepository.getReferenceById(id));
        modelAndView.addObject("cargos", cargoRepository.findAll());

        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(Professor professor) {
    	String senhaEncriptada = SenhaUtils.encode(professor.getSenha());
        professor.setSenha(senhaEncriptada);
        professorRepository.save(professor);

        return "redirect:/professores";
    }

    @PostMapping("/{id}/editar")
    public String editar(Professor professor, @PathVariable Long id) {
    	String senhaEncriptada = SenhaUtils.encode(professor.getSenha());
        professor.setSenha(senhaEncriptada);
        professorRepository.save(professor);

        return "redirect:/professores";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
    	professorRepository.deleteById(id);

        return "redirect:/funcionarios";
    }
    
}
