package br.com.academy.controllers;

import java.io.IOException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/error")
public class ErrorsController implements ErrorController {

	@GetMapping
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "errorPages/404.html";
				
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
				Throwable rootCause = findRootCause(throwable);
				String errorMessage = throwable.getMessage();
                if (rootCause != null) {           
                	model.addAttribute("cause", rootCause.getMessage());
                	model.addAttribute("errorMessage", errorMessage);
                }
				return "errorPages/500.html";
				
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				return "errorPages/403.html";
			} else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
				return "errorPages/400.html";
			}
		}
		return "error";
	}
	
	private Throwable findRootCause(Throwable throwable) {
	    while (throwable.getCause() != null) {
	        throwable = throwable.getCause();
	    }
	    return throwable;
	}

//	ModelAndView modelAndView = new ModelAndView("errorPages/500.html");
//    modelAndView.addObject("message", exception.getMessage());
//    return modelAndView;

}