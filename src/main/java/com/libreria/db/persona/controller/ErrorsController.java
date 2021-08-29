package com.libreria.db.persona.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController implements ErrorController {

	@RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
	public String mostrarPaginaError(Model model, HttpServletRequest httpServletRequest) {
		String mensajeError = "";
		/*como la clase Servlet nos va a traer el codigo del error, debemos castear a int para luego utilizar el switch*/
		int codigoError = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
		switch (codigoError) {
			case 400:
				mensajeError = "El recurso solicitado no existe"; 
				break;
			case 401:
				mensajeError = "No autorizado! inicie sesión o registrese";
				break;
			case 403:
				mensajeError = "Acceso denegado"; 
				model.addAttribute("codigo", codigoError);
				model.addAttribute("mensaje", mensajeError); 
				return "error403"; 
			case 404:
				mensajeError = "El recurso solicitado no se ha encontrado"; 
				break;
			case 500:
				mensajeError = "El servidor no pudo cargar la peticion con exito";
				break;
			default:
		}
		model.addAttribute("codigo", codigoError);
		model.addAttribute("mensaje", mensajeError); 
		return "error";
	}

}
