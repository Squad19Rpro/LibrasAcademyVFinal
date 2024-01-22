package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.CursosDTO;
import br.com.academy.entidades.Cursos;
import br.com.academy.service.CursosService;
import br.com.academy.service.ProfessorService;

@Controller
@RequestMapping("/cursos")
public class CursosController {
	
	@Autowired
	private CursosService cursosService;
	
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cursos/home");
        modelAndView.addObject("cursos", cursosService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursos/detalhes");
        modelAndView.addObject("curso", cursosService.findById(id));

        return modelAndView;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cursos/formulario");
        modelAndView.addObject("cursos", new Cursos());
        modelAndView.addObject("professores", professorService.findAll());

        return modelAndView;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursos/editCursos");
        modelAndView.addObject("cursos", cursosService.findById(id));
        modelAndView.addObject("professores", professorService.findAll());

        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(CursosDTO curso) {
        cursosService.save(curso);

        return "redirect:/cursos";
    }
    
    @PostMapping("/{id}/editar")
    public String editar(CursosDTO cursoUpdate, @PathVariable Long id) throws Exception {
    	CursosDTO cursoAlvo = cursosService.findById(id);
        cursosService.update(cursoUpdate, cursoAlvo);

        return "redirect:/cursos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        cursosService.deleteById(id);

        return "redirect:/cursos";
    }
}
