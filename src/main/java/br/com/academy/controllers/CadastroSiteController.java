package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.entidades.Aluno;
import br.com.academy.repository.AlunoRepository;
import br.com.academy.utils.SenhaUtils;



@Controller
@RequestMapping("/cadastro")
public class CadastroSiteController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("pages/cadastro");
        modelAndView.addObject("aluno", new Aluno());      

        return modelAndView;
    }
	
	@PostMapping("/cadastroSite")
    public String cadastrar(Aluno aluno) {
		String senhaEncriptada = SenhaUtils.encode(aluno.getSenha());
        aluno.setSenha(senhaEncriptada);
        alunoRepository.save(aluno);

        return "redirect:/login";
    }
	
}
