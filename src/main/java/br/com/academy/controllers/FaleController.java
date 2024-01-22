package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.CargoDTO;
import br.com.academy.dto.FaleConoscoDTO;
import br.com.academy.entidades.FaleConosco;
import br.com.academy.service.FaleConoscoService;

@Controller
@RequestMapping("/faleMsg")
public class FaleController {
	
	@Autowired
	private FaleConoscoService faleService;
	
	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("fale/home");
        modelAndView.addObject("msgs", faleService.findAll());
        
        return modelAndView;
	}
	
	@GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("fale/formulario");
        modelAndView.addObject("msg", new FaleConosco());      

        return modelAndView;
    }
	
	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("fale/editFale");
        modelAndView.addObject("msg", faleService.findById(id));

        return modelAndView;
    }
	
	@PostMapping("/cadastrar")
	public String cadastrar(FaleConoscoDTO fale) {
		faleService.save(fale);
		
		return "redirect:/faleMsg";
	}
    	
	@PostMapping("/{id}/editar")
    public String editar(FaleConoscoDTO faleUpdate, @PathVariable Long id) throws Exception {
		FaleConoscoDTO faleAlvo = faleService.findById(id);
    	faleService.update(faleUpdate, faleAlvo);
        return "redirect:/faleMsg";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        faleService.deleteById(id);

        return "redirect:/faleMsg";
    }
}
