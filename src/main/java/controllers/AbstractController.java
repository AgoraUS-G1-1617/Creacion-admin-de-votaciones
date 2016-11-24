/* AbstractController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
* @Class AbstractController
* @classDec La clase contiene el controlador que redirecciona a la página de error cuando se cometen excepciones en el código
*/
@Controller
public class AbstractController {
	
	// Panic handler ----------------------------------------------------------
	/**
	* @param oops La expcepción que ha sido lanzada
	* @return Este método devuelve el modelo de vista de error por excepción.
	*/
	@ExceptionHandler(Throwable.class)
	public ModelAndView panic(Throwable oops) {
		ModelAndView result;

		result = new ModelAndView("misc/panic");
		result.addObject("name", ClassUtils.getShortName(oops.getClass()));
		result.addObject("exception", oops.getMessage());
		result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));

		return result;
	}	

}
