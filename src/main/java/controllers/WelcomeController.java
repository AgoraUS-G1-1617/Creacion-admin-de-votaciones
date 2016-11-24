package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Class WelcomeController
 * @classDec La clase contiene el controlador que maneja las acciones de
 *           bienvenida y para saber más de los desarrolladores
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------
	/**
	 * @return Constructor del controlador.
	 */
	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------
	/**
	 * @param name
	 *            String con el nombre del usuario logeado. Por defecto
	 *            Benavides.
	 * @return Este método devuelve el modelo de vista de la página principal
	 *         del proyecto.
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "Benavides") String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("moment", moment);

		return result;
	}

	/**
	 * @return Este método devuelve el modelo de vista de la página en la cual
	 *         podras saber quienes han sido los desarrolladores del proyecto.
	 */
	@RequestMapping(value = "/aboutus")
	public ModelAndView aboutUs() {
		ModelAndView result;
		result = new ModelAndView("welcome/aboutus");

		result.addObject("requestURI", "welcome/aboutus");
		return result;

	}
}