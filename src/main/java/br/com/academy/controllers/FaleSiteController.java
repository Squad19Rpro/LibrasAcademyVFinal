package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.FaleConoscoDTO;
import br.com.academy.entidades.FaleConosco;
import br.com.academy.service.FaleConoscoService;

@Controller
@RequestMapping("/contato")
public class FaleSiteController {
	@Autowired
	private FaleConoscoService faleService;
	
	@GetMapping
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("pages/contato");
        modelAndView.addObject("msg", new FaleConosco());      

        return modelAndView;
    }
	
	@PostMapping("/cadastroMsg")
    public String cadastrar(FaleConoscoDTO fale) {
		faleService.save(fale);

        return "redirect:/";
    }
}
