package services;
import java.util.Collection;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurveyRepository;

import domain.Question;
import domain.Survey;


@Service
public class SurveyService {

	//Repositories
	@Autowired
	private SurveyRepository surveyRepository;
	
	//Services
	@Autowired
	private QuestionService questionService;
	
	/**
	 * 
	 * @param s es un objeto de tipo Survey (Votación).
	 * @return Este metodo devuelve devuelve un objeto de tipo Int que contiene la id del Survey creado.
	 */
	@Transactional
	public Integer save(Survey s,String user) {
		Assert.notNull(s);
		Date now = new Date(System.currentTimeMillis() - 1000);
		if (s.getStartDate() == null || s.getEndDate() == null || s.getTitle() == "" || s.getTipo() == null) {
			throw new IllegalArgumentException("Null");
		}
		if (now.after(s.getStartDate()) || now.after(s.getEndDate())) {
			throw new IllegalArgumentException("Dates future");
		}
		if (s.getStartDate().after(s.getEndDate())) {
			throw new IllegalArgumentException("End must be future than start");
		}
		// CAMBIAR POR EL USUARIO LOGEADO Y CENSO POR LA ID DEL CENSO

		// Se le pone 0 temporalmente. Cuando guardamos despues de crear
		// las preguntas entonces establecemos la conexion con ceso
		s.setCensus(0);
		s.setUsernameCreator(user);
		Survey s1 = surveyRepository.saveAndFlush(s);
		return s1.getId();

	}

	// Metodo para obtener la id del censo. Tenemos que enviarle la survey.
	/**
	 * 
	 * @param survey es un objeto de tipo Survey (Votación).
	 * @return Este metodo devuelve la id del censo, que se pediría al subsitema Censo, pero en este caso
	 * como las llamadas http no están correctamente implementadas en el subsistema Censo, obligamo a este
	 * método a devolver siempre una idea estática para que por lo menos no se pierda la traza del conjunto. 
	 */
	private Integer getIdCensusFromOtherSubsystem(Survey survey) {
		return 7;
	}


	// Metodo para obtener un survey mediante su id que le enviamos como
	//parámetro
	/**
	 * 
	 * @param id hace referencia a la id de un objeto de tipo Survey (Votación).
	 * @return Este metodo devuelve un objeto de tipo Survey (Votación) mediante 
	 * una llamada al repositorio de la entidad Survey (Votación).
	 */ 
	public Survey findOne(int id) {
		Assert.notNull(id);
		return surveyRepository.findOne(id);
	}
	// Método de interacción con el subsistema de Visualización
	/**
	 * 
	 * @return Este metodo devuelve una lista con todas las survey (votaciones) que han finalizado.
	 */
	public List<Survey> allFinishedSurveys() {

		Date now = new Date(System.currentTimeMillis());
		List<Survey> res = surveyRepository.allFinishedSurveys(now);
		return res;
	}

	// Metodo que devuelve una lista de surveys que han sido creados por un usuario
	//que le pasamos como parámetro.
	/**
	 * 
	 * @param usernameCreator contiene un objeto de tipo String que contiene el nombre 
	 * de usuario del creador de alguna Survey (Votación).
	 * @return Este metodo devuelve una lista de Surveys (Votaciones) que han sido creadas por un usuario que
	 * le enviamos como parámetro.
	 */
	public List<Survey> allCreatedSurveys(String usernameCreator) {
		List<Survey> res = surveyRepository.allCreatedSurveys(usernameCreator);
		return res;
	}
	
	// Metodo que elimina survey de la base de datos, mediante la id del survey
	//que le pasamos como parámetro
	/**
	 * 
	 * @param id es la identificación de una Survey (Votación).
	 */
	public void delete(int id) {
		Assert.notNull(id);
		Date now = new Date(System.currentTimeMillis() - 1000);
		Survey survey = surveyRepository.findOne(id);
		if (survey.getStartDate().after(now)) {
			surveyRepository.delete(id);
		} else {
			throw new IllegalArgumentException("Survey is started");
		}
	}

	/**
	 * Metodo no usado actualmente
	 */
/*	public Boolean posible(int id) {
		Assert.notNull(id);
		Survey s = findOne(id);

		if (s.getCensus() == null) {
			return true;
		} else {
			return false;
		}
	}
*/	
	// Metodo que devuelve una colección de todos los survey que persisten en
	//la base de datos del sistema
	/**
	 * 
	 * @return Este metodo devuelve una colección de todas las Survey (Votación) almacenadas en el sistema.
	 */
	public Collection<Survey> allSurveys() {
		return surveyRepository.findAll();
	}

	// Metodo que devuelve un survey una vez que ha sido creado dentro de
	//dicho metodo
	/**
	 * 
	 * @return Este metodod evuelve un objeto de tipo Survey (Votación).
	 */
	public Survey create() {
		Survey result;
		result = new Survey();
		List<Question> questions = new LinkedList<Question>();
		result.setQuestions(questions);
		return result;
	}

	// Metodo que recupera un survey y le modifica la colección de question
	// añadiendole una nueva question, que se recupera mediante la id
	//que se le pasa por parámetro.
	/**
	 * 
	 * @param id hace referencia al identificador de un Survey (Votación).
	 * @param questionId hace referencia al identificador de una Question (Pregunta).
	 * @param esFinal hace referencia a una propiedad de tipo Boolean que determina si es la última pregunta para
	 * añadir al survey (Votación).
	 */
	public Survey saveAddQuestion(int id, int questionId, boolean esFinal) {
		Survey survey = surveyRepository.findOne(id);
		Collection<Question> questions = survey.getQuestions();
		questions.add(questionService.findOne(questionId));
		survey.setQuestions(questions);
		Survey s = surveyRepository.saveAndFlush(survey);
		if (esFinal) {
			Integer idCensus = getIdCensusFromOtherSubsystem(s);
			s.setCensus(idCensus);
			Survey s2 = surveyRepository.saveAndFlush(s);
			s=s2;
		}
		return s;
	}

	// Metodo que persiste una survey en la base de datos.
	/**
	 * 
	 * @param survey es un objeto de tipo Survey (Votación).
	 */
	public void saveFinal(Survey survey) {
		Assert.notNull(survey);
		surveyRepository.saveAndFlush(survey);
	}

	public void addCensus(Integer censoId, Integer surveyId) {
		Survey s = surveyRepository.findOne(surveyId);
		s.setCensus(censoId);
		saveFinal(s);
	}

	
}