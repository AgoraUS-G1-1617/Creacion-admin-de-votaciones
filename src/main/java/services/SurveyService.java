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
import security.LoginService;

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
	 * @param s es un objeto de tipo Survey (VotaciÃ³n).
	 * @return Este metodo devuelve devuelve un objeto de tipo Int que contiene la id del Survey creado.
	 */
	@Transactional
	public Integer save(Survey s,String user) {
		Assert.notNull(s);
		Date now = new Date(System.currentTimeMillis() - 1000);

		if (now.after(s.getStartDate()) || now.after(s.getEndDate())) {
			throw new IllegalArgumentException("Start date and end date must be in the future");
		}
		if (s.getStartDate().after(s.getEndDate())) {
			throw new IllegalArgumentException("End must be future than start");
		}

//		if (s.getPostalCode().length()!=5) {
//			throw new IllegalArgumentException("Postal Code must have 5 digits");
//		}
//		for(int i=0;i<s.getPostalCode().length();i++){
//			if(!Character.isDigit(s.getPostalCode().charAt(i)))
//					throw new IllegalArgumentException("Postal Code must have 5 digits");
//		}

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
	 * @param survey es un objeto de tipo Survey (VotaciÃ³n).
	 * @return Este metodo devuelve la id del censo, que se pedirÃ­a al subsitema Censo, pero en este caso
	 * como las llamadas http no estÃ¡n correctamente implementadas en el subsistema Censo, obligamo a este
	 * mÃ©todo a devolver siempre una idea estÃ¡tica para que por lo menos no se pierda la traza del conjunto. 
	 */
	private Integer getIdCensusFromOtherSubsystem(Survey survey) {
		return 7;
	}


	// Metodo para obtener un survey mediante su id que le enviamos como
	//parÃ¡metro
	/**
	 * 
	 * @param id hace referencia a la id de un objeto de tipo Survey (VotaciÃ³n).
	 * @return Este metodo devuelve un objeto de tipo Survey (VotaciÃ³n) mediante 
	 * una llamada al repositorio de la entidad Survey (VotaciÃ³n).
	 */ 
	public Survey findOne(int id) {
		Assert.isTrue(id!=0);
		Survey  s = surveyRepository.findOne(id);
		Assert.notNull(s);
		return s;
	}
	
	public Survey findSurvey(int id) {
		Assert.isTrue(id!=0, "commit.illegalOp");
		Assert.isTrue(surveyRepository.exists(id), "commit.illegalOp");
		Survey  s = surveyRepository.findOne(id);
		Assert.isTrue(s.getUsernameCreator().equals(LoginService.getPrincipal().getUsername()), "commit.illegalOp");
		return s;
	}
	// MÃ©todo de interacciÃ³n con el subsistema de VisualizaciÃ³n
	/**
	 * 
	 * @return Este metodo devuelve una lista con todas las survey (votaciones) que han finalizado.
	 */
	public List<Survey> allFinishedSurveys() {

		List<Survey> res = surveyRepository.allFinishedSurveys();
		return res;
	}
	

	/**
	 * 
	 * @return Este método devuelve una lista con todas las survey (votaciones) activas.
	 */
	public List<Survey> findAllActiveSurveys() {

		List<Survey> res = surveyRepository.findAllActiveSurveys();
		return res;
	}
	
	/**
	 * 
	 * @return Este método devuelve una lista con todas las survey (votaciones) del mismo
	 * lugar (mismo código psotal por tanto).
	 */
	public Collection<Survey> findSurveysByPlace(String postalCode){
		
		Collection<Survey> res = surveyRepository.findSurveysByPlace(postalCode);
		return res;
	}

	// Metodo que devuelve una lista de surveys que han sido creados por un usuario
	//que le pasamos como parÃ¡metro.
	/**
	 * 
	 * @param usernameCreator contiene un objeto de tipo String que contiene el nombre 
	 * de usuario del creador de alguna Survey (VotaciÃ³n).
	 * @return Este metodo devuelve una lista de Surveys (Votaciones) que han sido creadas por un usuario que
	 * le enviamos como parÃ¡metro.
	 */
	public Collection<Survey> findOneByUsername(String usernameCreator) {
		Collection<Survey> res = surveyRepository.allCreatedSurveys(usernameCreator);
		return res;
	}
	
	// Metodo que elimina survey de la base de datos, mediante la id del survey
	//que le pasamos como parÃ¡metro
	/**
	 * 
	 * @param id es la identificaciÃ³n de una Survey (VotaciÃ³n).
	 */
	public void delete(int id) {
		Assert.notNull(id);
		Date now = new Date(System.currentTimeMillis() - 1000);
		Survey survey = surveyRepository.findOne(id);
		if (survey.getStartDate().before(now) || survey.getEndDate().after(now)) {
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
	// Metodo que devuelve una colecciÃ³n de todos los survey que persisten en
	//la base de datos del sistema
	/**
	 * 
	 * @return Este metodo devuelve una colecciÃ³n de todas las Survey (VotaciÃ³n) almacenadas en el sistema.
	 */
	
	//este metodo devuelve un conjunto con todas las Surveys guardadas en el sistema.
	public Collection<Survey> allSurveys() {
		return surveyRepository.findAll();
	}

	// Metodo que devuelve un survey una vez que ha sido creado dentro de
	//dicho metodo
	/**
	 * 
	 * @return Este metodod devuelve un objeto de tipo Survey (VotaciÃ³n).
	 */
	public Survey create() {
		Survey result;
		result = new Survey();
		List<Question> questions = new LinkedList<Question>();
		result.setQuestions(questions);
		return result;
	}

	// Metodo que recupera un survey y le modifica la colecciÃ³n de question
	// aÃ±adiendole una nueva question, que se recupera mediante la id
	//que se le pasa por parÃ¡metro.
	/**
	 * 
	 * @param id hace referencia al identificador de un Survey (VotaciÃ³n).
	 * @param questionId hace referencia al identificador de una Question (Pregunta).
	 * @param esFinal hace referencia a una propiedad de tipo Boolean que determina si es la Ãºltima pregunta para
	 * aÃ±adir al survey (VotaciÃ³n).
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
	 * @param survey es un objeto de tipo Survey (VotaciÃ³n).
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
	
	public Collection<Survey> findSurveysFromSevilla(){
		Collection<Survey> s = surveyRepository.findSurveysFromSevilla();
		Assert.notNull(s);
		return s;
	}
	
	public Collection<Survey> findSurveysAlreadyStarted(){
		Collection<Survey> s = surveyRepository.findSurveysAlreadyStarted();
		Assert.notNull(s);
		return s;
	}
	
	public Double ratioOfSurveysWhichHaveNotStartedYet(){
		Double r = surveyRepository.ratioOfSurveysWhichHaveNotStartedYet();
		Assert.notNull(r);
		return r;
	}
	
	public Double averageOfQuestionsPerSurvey(){
		Double a = surveyRepository.averageOfQuestionsPerSurvey();
		Assert.notNull(a);
		return a;
	}
	
}
