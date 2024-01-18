package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.entidades.Cursos;
import br.com.academy.repository.CursosRepository;
import br.com.academy.repository.ProfessorRepository;

@Controller
@RequestMapping("/cursos")
public class CursosController {
	
	@Autowired
	private CursosRepository cursosRepository;
	
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cursos/home");
        modelAndView.addObject("cursos", cursosRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursos/detalhes");
        modelAndView.addObject("curso", cursosRepository.getReferenceById(id));

        return modelAndView;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cursos/formulario");
        modelAndView.addObject("cursos", new Cursos());
        modelAndView.addObject("professores", professorRepository.findAll());

        return modelAndView;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursos/editCursos");
        modelAndView.addObject("cursos", cursosRepository.getReferenceById(id));
        modelAndView.addObject("professores", professorRepository.findAll());

        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(Cursos curso) {
        cursosRepository.save(curso);

        return "redirect:/cursos";
    }
    
    @PostMapping("/{id}/editar")
    public String editar(Cursos curso, @PathVariable Long id) {
        cursosRepository.save(curso);

        return "redirect:/cursos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        cursosRepository.deleteById(id);

        return "redirect:/cursos";
    }
}
