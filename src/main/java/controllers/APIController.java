package controllers;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import services.SurveyService;
import domain.CheckToken;
import domain.Question;
import domain.Survey;

/**
 * @Class SurveyController
 * @classDec La clase contiene el controlador que maneja las acciones de las
 *           votaciones, crear, añadir preguntas, borrar, y la api para los
 *           otros módulos.
 */
@Controller
@RestController
@RequestMapping("/api")
public class APIController {

	// Services ------------------------------------------

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private QuestionService questionService;



	/**
	 * @return Constructor del Controlador.
	 */
	// Constructor ---------------------------------------
	public APIController() {
		super();
	}

	/**
	 * @param surveyId
	 *            ID de la votación de la que se desea ver los detalles
	 * @return Este método forma parte de la API de integración. Devuelve un
	 *         JSON con los datos de la votación
	 */
	@RequestMapping(value = "/survey", method = RequestMethod.GET)
	public Survey getSurvey(@RequestParam int id) {
		Survey result = surveyService.findOne(id);
		return result;
	}

	/**
	 * @return Este método forma parte de la API de integración. Devuelve un
	 *         JSON con los datos de todas la votación finalizadas.
	 */
	@RequestMapping(value = "/finishedSurveys", method = RequestMethod.GET)
	public Collection<Survey> findAllfinishedSurveys() {
		Collection<Survey> result = surveyService.allFinishedSurveys();
		return result;
	}

	/**
	 * @return Este método forma parte de la API de integración. Devuelve un
	 *         JSON con los datos de todas la votaciónes.
	 */
	@RequestMapping(value = "/allSurveys", method = RequestMethod.GET)
	public Collection<Survey> findAllSurveys() {
		Collection<Survey> result = surveyService.allSurveys();
		return result;
	}



}