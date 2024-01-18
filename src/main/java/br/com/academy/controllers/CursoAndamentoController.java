package br.com.academy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.entidades.Aluno;
import br.com.academy.entidades.CursoAluno;
import br.com.academy.entidades.Cursos;
import br.com.academy.repository.AlunoRepository;
import br.com.academy.repository.CursoAlunoRepository;
import br.com.academy.repository.CursosRepository;

@Controller
@RequestMapping("/cursoAndamento")
public class CursoAndamentoController {
		
	@Autowired
	private CursoAlunoRepository caRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private CursosRepository cursosRepository;
	
	@GetMapping
    public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("cursoAndamento/home");
		modelAndView.addObject("curso_andamento", caRepository.findAll());
		
        return modelAndView;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cursoAndamento/formulario");
        List<Aluno> alunos = alunoRepository.findAll();
        List<Cursos> cursos = cursosRepository.findAll();

        modelAndView.addObject("alunos", alunos);
        modelAndView.addObject("cursos", cursos);

        return modelAndView;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursoAndamento/editCursoAndamento");
        CursoAluno cursoAluno = caRepository.findById(id).orElseThrow();
        modelAndView.addObject("curso", caRepository.findById(id).orElseThrow());
        Aluno aluno = cursoAluno.getAluno();
        Cursos curso = cursoAluno.getCurso();
        
        modelAndView.addObject("cursoAluno", cursoAluno);
        modelAndView.addObject("alunoSelecionado", aluno);
        modelAndView.addObject("cursoSelecionado", curso);
        modelAndView.addObject("alunos", alunoRepository.findAll());
        modelAndView.addObject("cursos", cursosRepository.findAll());
        //modelAndView.addObject("matricula", caRepository.getReferenceById(id));
        
        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam("alunoId") Long alunoId, @RequestParam("cursoId") Long cursoId) {
    	
    	Aluno aluno = alunoRepository.findById(alunoId).orElseThrow();
    	Cursos curso = cursosRepository.findById(cursoId).orElseThrow();
    	CursoAluno cursoAluno = new CursoAluno();
        cursoAluno.setAluno(aluno);
        cursoAluno.setCurso(curso);

        caRepository.save(cursoAluno);

        return "redirect:/cursoAndamento";
    }
    
    @PostMapping("/{id}/editar")
    public String editar(CursoAluno curso, @PathVariable Long id) {
        caRepository.save(curso);

        return "redirect:/cursoAndamento";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        caRepository.deleteById(id);

        return "redirect:/cursoAndamento";
    }
}
