package br.com.academy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.AlunoDTO;
import br.com.academy.dto.CursoAlunoDTO;
import br.com.academy.dto.CursosDTO;
import br.com.academy.service.AlunoService;
import br.com.academy.service.CursoAlunoService;
import br.com.academy.service.CursosService;

@Controller
@RequestMapping("/cursoAndamento")
public class CursoAndamentoController {
		
	@Autowired
	private CursoAlunoService caService;
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private CursosService cursosService;
	
	@GetMapping
    public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("cursoAndamento/home");
		modelAndView.addObject("curso_andamento", caService.findAll());
		
        return modelAndView;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cursoAndamento/formulario");
        modelAndView.addObject("alunos", alunoService.findAll());
        modelAndView.addObject("cursos", cursosService.findAll());

        return modelAndView;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursoAndamento/editCursoAndamento");
        CursoAlunoDTO cursoAluno = caService.findById(id);
        List<CursoAlunoDTO> relacao = caService.findAll();
        
        modelAndView.addObject("curso", caService.findById(id));
        
        modelAndView.addObject("cursoAluno", cursoAluno);
        modelAndView.addObject("alunoSelecionado", cursoAluno);
        modelAndView.addObject("cursoSelecionado", cursoAluno);
        modelAndView.addObject("alunos", alunoService.findAll());
        modelAndView.addObject("cursos", cursosService.findAll());
        //modelAndView.addObject("matricula", caService.getReferenceById(id));
        
        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(CursoAlunoDTO cursoAluno) {
    	
    	caService.save(cursoAluno);    	
//    	Aluno aluno = alunoService.findById(alunoId).orElseThrow();
//    	Cursos curso = cursosService.findById(cursoId).orElseThrow();
//    	CursoAluno cursoAluno = new CursoAluno();
//        cursoAluno.setAluno(aluno);
//        cursoAluno.setCurso(curso);
//
//        caService.save(cursoAluno);

        return "redirect:/cursoAndamento";
    }
    
    @PostMapping("/{id}/editar")
    public String editar(CursoAlunoDTO cursoAluno, @PathVariable Long matricula) throws Exception  {
    	CursoAlunoDTO caAlvo = caService.findById(matricula);
        caService.update(cursoAluno, caAlvo);

        return "redirect:/cursoAndamento";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        caService.deleteById(id);

        return "redirect:/cursoAndamento";
    }
}
