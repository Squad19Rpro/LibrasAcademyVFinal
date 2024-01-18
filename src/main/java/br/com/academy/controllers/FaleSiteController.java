package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.academy.entidades.FaleConosco;
import br.com.academy.repository.FaleRepository;

@Controller
@RequestMapping("/contato")
public class FaleSiteController {
	@Autowired
	private FaleRepository faleRepository;
	
	@PostMapping("/cadastroMsg")
    public String cadastrar(FaleConosco fale) {
        faleRepository.save(fale);

        return "redirect:/";
    }
}
