package controllers;

import java.util.Collection;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
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
@RequestMapping("/vote")
public class SurveyController {

	// Services ------------------------------------------

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private QuestionService questionService;

//	private requestsHttp httpRequest = new requestsHttp();

	/**
	 * @return Constructor del Controlador.
	 */
	// Constructor ---------------------------------------
	public SurveyController() {
		super();
	}

	// Listing -------------------------------------------
	/**
	 * @return Este método devuelve el modelo de vista con el listado de
	 *         votaciones.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@CookieValue("user") String user) {
		ModelAndView result;
		System.out.println(user);
		Collection<Survey> surveis;
		surveis = surveyService.allCreatedSurveys(user);
		System.out.println( surveis);
		Date now = new Date(System.currentTimeMillis() - 1000);
		result = new ModelAndView("vote/list");
		result.addObject("surveis", surveis);
		result.addObject("hoy", now);

		return result;
	}

	// Creation ------------------------------------------
	/**
	 * @return Este método devuelve el modelo de vista para el primer paso de la
	 *         votacion. El cual consiste en elegir el nombre, la descripción,
	 *         el tipo de censo y el intervalo de fecha de inicio y finalización
	 *         de la votación.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Survey survey;
		
		survey = surveyService.create();

		result = new ModelAndView("vote/create");
		result.addObject("survey", survey);
		result.addObject("actionURL", "vote/create.do");

		return result;
	}

	/**
	 * @param survey
	 *            La votación que se ha creado en el primer paso.
	 * @param bindingResult
	 *            Este parámetro nos indica si hay algun error con los datos
	 *            introducidos comparándolos con las restricciones escritas en
	 *            el dominio.
	 * @return Este método comprueba si la votación es válida y en caso negativo
	 *         volvemos a la vista de creación de la votación para poder editar
	 *         los campos erróneos. En caso afirmativo, procederemos a la
	 *         siguiente vista la cual es para añadir preguntas a la votación.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "addQuestion")
	public ModelAndView addQuestio(@CookieValue("user") String user,Survey survey, BindingResult bindingResult) {
		ModelAndView result;
		Assert.notNull(survey);
		Date now = new Date(System.currentTimeMillis() - 1000);
		System.out.println(survey.getStartDate());
		System.out.println(survey.getEndDate());
		if (bindingResult.hasErrors() || survey.getStartDate() == null || survey.getEndDate() == null
				|| survey.getTitle() == "" || survey.getTipo() == null || now.after(survey.getStartDate())
				|| now.after(survey.getEndDate()) || survey.getStartDate().after(survey.getEndDate())) {
			System.out.println(bindingResult.toString());
			result = new ModelAndView("vote/create");
			result.addObject("actionURL", "vote/create.do");
			result.addObject("survey", survey);
			if (survey.getStartDate() == null || survey.getEndDate() == null || survey.getTitle() == ""
					|| survey.getTipo() == null) {
				result.addObject("message", "survey.fields.empty");
			}
			if (now.after(survey.getStartDate()) || now.after(survey.getEndDate())) {
				result.addObject("message", "survey.dates.future");
			}
			if (survey.getStartDate().after(survey.getEndDate())) {
				result.addObject("message", "survey.start.end");
			}
		} else {
			try {
				Integer s2 = surveyService.save(survey,user);
				result = new ModelAndView("redirect:/vote/addQuestion.do");
				result.addObject("surveyId", s2);
			} catch (Throwable oops) {
				result = new ModelAndView("/vote/create");
				result.addObject("message", "survey.commit.error");
				result.addObject("survey", survey);
				if (survey.getStartDate() == null || survey.getEndDate() == null || survey.getTitle() == ""
						|| survey.getTipo() == null) {
					result.addObject("message", "survey.fields.empty");
				}
				if (survey.getStartDate() == null || survey.getEndDate() == null || survey.getTitle() == ""
						|| survey.getTipo() == null) {
					result.addObject("message", "survey.fields.empty");
				}
				if (now.after(survey.getStartDate()) || now.after(survey.getEndDate())) {
					result.addObject("message", "survey.dates.future");
				}
				if (survey.getStartDate().after(survey.getEndDate())) {
					result.addObject("message", "survey.start.end");
				}
			}
		}
		return result;
	}

	/**
	 * @param surveyId
	 *            La id votación que a la que se le añadiran preguntas.
	 * @return Este método devuelve la vista en la que se podrán añadir
	 *         preguntas a la votación.
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
	public ModelAndView addQuestion(Integer surveyId) {
		ModelAndView result;
		Survey survey = surveyService.findOne(surveyId);
		Question question = questionService.create(surveyId);
		question.setSurveyId(surveyId);
		result = new ModelAndView("vote/addQuestion");
		result.addObject("actionURL", "vote/addQuestion.do");
		result.addObject("survey", survey);
		result.addObject("questio", question);
		return result;
	}

	/**
	 * @param questio
	 *            La pregunta introducida de la votación
	 * @param bindingResult
	 *            Este parámetro nos indica si hay algun error con los datos
	 *            introducidos comparandolos con las restricciones escritas en
	 *            el dominio.
	 * @return Este método añade la pregunta a la votación siempre que no esté
	 *         en blanco y vuelve a la misma vista para añadir otra pregunta.
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST, params = "addQuestion")
	public ModelAndView addAnotherQuestion(Question questio, BindingResult bindingResult) {
		ModelAndView result;
		Assert.notNull(questio);
		Survey survey = surveyService.findOne(questio.getSurveyId());
		if (bindingResult.hasErrors()||questio.getText() == "") {
			result = new ModelAndView("vote/addQuestion");
			result.addObject("actionURL", "vote/addQuestion.do");
			result.addObject("surveyId", survey.getId());
			result.addObject("questio", questio);
			if(questio.getText() == ""){
			result.addObject("message","survey.fields.empty");
			}
		} else {
			try {
				int idQuestion = questionService.saveAndFlush(questio);
				surveyService.saveAddQuestion(survey.getId(), idQuestion, false);
				result = new ModelAndView("vote/addQuestion");
				Question question = questionService.create(survey.getId());
				question.setSurveyId(survey.getId());
				result.addObject("survey", survey);
				result.addObject("questio", question);

			} catch (Throwable oops) {
				result = new ModelAndView("vote/addQuestion");
				result.addObject("message", "survey.commit.error");
				result.addObject("actionURL", "vote/addQuestion.do");
				result.addObject("survey", survey.getId());
				result.addObject("questio", questio);
				if(questio.getText() == ""){
					result.addObject("message","survey.fields.empty");
					}
			}
		}
		return result;
	}

	/**
	 * @param questio
	 *            La pregunta introducida de la votación
	 * @param bindingResult
	 *            Este parámetro nos indica si hay algun error con los datos
	 *            introducidos comparandolos con las restricciones escritas en
	 *            el dominio.
	 * @return Este método añade la pregunta a la votación siempre que no esté
	 *         en blanco y guarda del todo la votación. Comprueba que el usuario
	 *         este en el sistema en la integración con Verificación. Establece
	 *         la conexión con Censo para crear el censo de la votacion y
	 *         Establece conexión con deliberaciones para crear el hilo de las
	 *         deliberaciones.
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST, params = "save")
	public ModelAndView saveSurvey(Question questio, BindingResult bindingResult) {
		ModelAndView result;
		Assert.notNull(questio);
		Survey survey = surveyService.findOne(questio.getSurveyId());
		if (bindingResult.hasErrors()|| questio.getText() == "") {
			result = new ModelAndView("vote/addQuestion");
			result.addObject("actionURL", "vote/addQuestion.do");
			result.addObject("survey", survey.getId());
			result.addObject("questio", questio);
			if(questio.getText() == ""){
				result.addObject("message","survey.fields.empty");
				}
		} else {
			try {
				int idQuestion = questionService.saveAndFlush(questio);
				Survey s1 = surveyService.saveAddQuestion(survey.getId(), idQuestion, true);
				// Integracion con Verificación.
				//Authority a = new AuthorityImpl();
				Integer token = CheckToken.calculateToken(survey.getId());
				System.out.println("Id Votacion: " + survey.getId());
				//a.postKey(String.valueOf(survey.getId()), token);
				System.out.println("Token: " + token);
				// TODO integracion con censo. Falla el metodo que devuelve
				// censo.
				Integer censoId = 1;
				// censoId = httpRequest.generaPeticionCenso(survey.getId(),
				// survey.getStartDate(), survey.getEndDate(),
				// survey.getTitle(), survey.getTipo());
				surveyService.addCensus(censoId, s1.getId());

				// TODO integracion con deliberaciones.(Cambiar url de despliegue en el metodo)
//				httpRequest.generaPeticionDeliberations(survey.getId(), survey.getTitle());
				result = new ModelAndView("redirect:/vote/list.do");
			} catch (Throwable oops) {
				result = new ModelAndView("vote/addQuestion");
				result.addObject("message", "survey.commit.error");
				result.addObject("actionURL", "vote/addQuestion.do");
				result.addObject("survey", survey);
				result.addObject("questio", questio);
				if(questio.getText() == ""){
					result.addObject("message","survey.fields.empty");
					}
			}
		}
		return result;
	}

	/**
	 * @param surveyId
	 *            ID de la votación a borrar
	 * @return Este método cancela la votación si se decide que no se terminarán
	 *         de rellenar los datos del formulario de creación(Una vez que se
	 *         pasa a añadirle las preguntas).
	 */
	@RequestMapping(value = "/cancelSurvey", method = RequestMethod.GET)
	public ModelAndView cancelSurvey(@RequestParam int surveyId) {
		ModelAndView result;
		Assert.notNull(surveyId);
		try {
			surveyService.delete(surveyId);
			result = new ModelAndView("redirect:/vote/list.do");
		} catch (Throwable oops) {
			result = new ModelAndView("vote/addQuestion");
			result.addObject("message", "survey.commit.error");
			result.addObject("actionURL", "vote/addQuestion.do");
			result.addObject("survey", surveyId);
		}
		return result;
	}

	// Details ----------------------------------------------

	/**
	 * @param surveyId
	 *            ID de la votación de la que se desea ver los detalles
	 * @return Este método muestra la vista de los detalles de la votacion.
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details(@RequestParam int surveyId) {
		ModelAndView result;
		Survey survey;
		survey = surveyService.findOne(surveyId);
		Assert.notNull(survey);
		result = new ModelAndView("vote/details");
		result.addObject("survey", survey);
		return result;
	}

	/**
	 * @param surveyId
	 *            ID de la votación de la que se desea ver los detalles
	 * @return Este método muestra la vista de los detalles de la votacion.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int surveyId) {
		ModelAndView result;
		try {
			surveyService.delete(surveyId);
			result = new ModelAndView("redirect:/vote/list.do");
		} catch (Throwable oops) {
			result = new ModelAndView("vote/list");
			result.addObject("message", "survey.error.dates");
		}

		return result;
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