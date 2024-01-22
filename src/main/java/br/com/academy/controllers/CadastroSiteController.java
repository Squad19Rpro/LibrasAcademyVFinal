package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.AlunoDTO;
import br.com.academy.entidades.Aluno;
import br.com.academy.service.AlunoService;



@Controller
@RequestMapping("/cadastro")
public class CadastroSiteController {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("pages/cadastro");
        modelAndView.addObject("aluno", new Aluno());      

        return modelAndView;
    }
	
	@PostMapping("/cadastroSite")
    public String cadastrar(AlunoDTO alunoDTO) {
        alunoService.save(alunoDTO);

        return "redirect:/login";
    }
	
}
