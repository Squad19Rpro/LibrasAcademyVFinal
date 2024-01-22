package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.AlunoDTO;
import br.com.academy.repository.CursosRepository;
import br.com.academy.service.AlunoService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private CursosRepository cursosRepository;

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("aluno/home");

		modelAndView.addObject("estudantes", cursosRepository.findAll());
		modelAndView.addObject("alunos", alunoService.findAll());

		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("aluno/detalhes");
		modelAndView.addObject("cursos", cursosRepository.getReferenceById(id));
		modelAndView.addObject("aluno", alunoService.findById(id));

		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("aluno/formulario");
		modelAndView.addObject("aluno", new AlunoDTO());

		return modelAndView;
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("aluno/editAluno");
		modelAndView.addObject("aluno", alunoService.findById(id));

		return modelAndView;
	}

    @PostMapping("/cadastrar")
    public String cadastrar(AlunoDTO alunoDTO) {
        alunoService.save(alunoDTO);
        return "redirect:/alunos";
    }

	@PostMapping("/{id}/editar")
	public String editar(AlunoDTO alunoUpdate, @PathVariable Long id) throws Exception {
		AlunoDTO alunoAlvo = alunoService.findById(id);
		alunoService.update(alunoUpdate, alunoAlvo);
		return "redirect:/alunos";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		alunoService.deleteById(id);

		return "redirect:/alunos";
	}

}
