package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.DomainEntity;
import domain.Question;

@Service
public class QuestionService extends DomainEntity{

	//Repository
	@Autowired
	private QuestionRepository questionRepository;

	//Methods
	//Creación de una question, a la que le pasamos la id del survey.
	/**
	 * 
	 * @param surveyId almacena la id del survery (votación).
	 * @return Este metodo devuelve una question (pregunta).
	 */
	public Question create(Integer surveyId){
		Question o = new Question();
		o.setSurveyId(surveyId);
		questionRepository.saveAndFlush(o);
		return o;
	}

	//Creación de una question con un texto que le pasamos como parámetro. 
	/**
	 * 
	 * @param question es el texto de una question (pregunta).
	 * @return Este metodo devuelve una question (pregunta).
	 */
	public Question create(String question) {
		Question o = new Question();
		o.setText(question);
		
		questionRepository.saveAndFlush(o);
		return o;
	}

	
	//Método que crea y almacena una question, y devuelve la id de dicha question.
	/**
	 * 
	 * @param question es un objeto de tipo Question (Pregunta).
	 * @return Este metodo devuelve la id de la question creada.
	 */
	public int saveAndFlush(Question question) {
		Assert.notNull(question);
		Question q2 = questionRepository.saveAndFlush(question);
		int questionID = q2.getId();
		return questionID;
	}

	//Debuelve un objeto de tipo question cuya id es la id que se le pasa como
	//parámetro.
	/**
	 * 
	 * @param questionId es la id de una question.
	 * @return Este metodo devuelve la question que ha sido identificada por su id.
	 */
	public Question findOne(int questionId) {
		return questionRepository.findOne(questionId);
	}
}
