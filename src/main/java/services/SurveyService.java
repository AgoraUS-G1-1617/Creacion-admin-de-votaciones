package services;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurveyRepository;
import domain.Survey;
@Service
public class SurveyService {

	//Repositories
	@Autowired
	private SurveyRepository surveyRepository;
	
	//Services
	@Autowired
	private QuestionService questionService;
	
	@Transactional
	public Integer save(Survey s) {
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
		String userName = getUserNameFromOtherSubsystem();
		s.setUsernameCreator(userName);

		// Se le pone 0 temporalmente. Cuando guardamos despues de crear
		// las preguntas entonces establecemos la conexion con ceso
		s.setCensus(0);

		Survey s1 = surveyRepository.saveAndFlush(s);
		return s1.getId();

	}

	// Metodo para obtener la id del censo. Tenemos que enviarle la survey.
	private Integer getIdCensusFromOtherSubsystem(Survey survey) {
		return 1;
	}

	// Metodo para obtener el nombre del usuario
	private String getUserNameFromOtherSubsystem() {
		return "User1";
	}

	public Survey findOne(int id) {
		Assert.notNull(id);
		return surveyRepository.findOne(id);
	}
	// Método de interacción con el subsistema de Visualización
	public List<Survey> allFinishedSurveys() {

		Date now = new Date(System.currentTimeMillis());
		List<Survey> res = surveyRepository.allFinishedSurveys(now);
		return res;
	}

	public List<Survey> allCreatedSurveys(String usernameCreator) {
		List<Survey> res = surveyRepository.allCreatedSurveys(usernameCreator);
		return res;
	}

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

	public Boolean posible(int id) {
		Assert.notNull(id);
		Survey s = findOne(id);

		if (s.getCensus() == null) {
			return true;
		} else {
			return false;
		}
	}

	public Collection<Survey> allSurveys() {
		return surveyRepository.findAll();
	}

	public Survey create() {
		Survey result;
		result = new Survey();
		List<Question> questions = new LinkedList<Question>();
		result.setQuestions(questions);
		return result;
	}

	public void saveAddQuestion(int id, int questionId, boolean esFinal) {
		Survey survey = surveyRepository.findOne(id);
		Collection<Question> questions = survey.getQuestions();
		questions.add(questionService.findOne(questionId));
		survey.setQuestions(questions);
		Survey s = surveyRepository.saveAndFlush(survey);
		if (esFinal) {
			Integer idCensus = getIdCensusFromOtherSubsystem(s);
			s.setCensus(idCensus);
			surveyRepository.saveAndFlush(s);
		}
	}

	public void saveFinal(Survey survey) {
		Assert.notNull(survey);
		surveyRepository.saveAndFlush(survey);
	}

	
}