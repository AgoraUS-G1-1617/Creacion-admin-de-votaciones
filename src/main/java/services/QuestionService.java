package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.QuestionRepository;
import domain.DomainEntity;
import domain.Question;

@Service
public class QuestionService extends DomainEntity{

	//Repository
	@Autowired
	private QuestionRepository questionRepository;

	//Methods
	//Creaci�n de una question, a la que le pasamos la id del survey.
	public Question create(Integer surveyId){
		Question o = new Question();
		o.setSurveyId(surveyId);
		questionRepository.saveAndFlush(o);
		return o;
	}

	//Creaci�n de una question con un texto que le pasamos como par�metro. 
	public Question create(String question) {
		Question o = new Question();
		o.setText(question);
		
		questionRepository.saveAndFlush(o);
		return o;
	}

	
	//M�todo que crea y almacena una question, y devuelve la id de dicha question.
	public int saveAndFlush(Question question) {
		Assert.notNull(question);
		Question q2 = questionRepository.saveAndFlush(question);
		int questionID = q2.getId();
		return questionID;
	}

	//Debuelve un objeto de tipo question cuya id es la id que se le pasa como
	//par�metro.
	public Question findOne(int questionId) {
		return questionRepository.findOne(questionId);
	}
}
