package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import domain.Question;
import domain.Survey;

import security.LoginService;
import services.QuestionService;
import services.SurveyService;

@Controller
@RestController
@RequestMapping("/question")
public class QuestionController {
	
	// Services ------------------------------------------

		@Autowired
		private SurveyService surveyService;

		@Autowired
		private QuestionService questionService;

//		private requestsHttp httpRequest = new requestsHttp();

		/**
		 * @return Constructor del Controlador.
		 */
		// Constructor ---------------------------------------
		public QuestionController() {
			super();
		}
		
		@RequestMapping(value="/listQuestions", method = RequestMethod.GET)
		public ModelAndView listQuestions(@RequestParam Integer surveyId) {
			ModelAndView result;
			Collection<Question> questions;
			Survey s;
			try{
				s = surveyService.findSurvey(surveyId);
				questions = s.getQuestions();
				result = new ModelAndView("question/listQuestions");
				result.addObject("questions", questions);
				result.addObject("requestURI", "question/listQuestions.do");
			}catch(Throwable oops){
				Collection<Survey> surveys;
				Date hoy= new Date(System.currentTimeMillis() - 1000);
				surveys= surveyService.findOneByUsername(LoginService.getPrincipal().getUsername());
				result = new ModelAndView("survey/list");
				result.addObject("surveys", surveys);
				result.addObject("hoy",hoy);
				result.addObject("message", oops.getMessage());
				result.addObject("requestURI", "survey/list.do");
			}

			return result;
		}
		
		@RequestMapping(value="/delete", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam Integer questionId) {
			ModelAndView result;
			Collection<Question> questions;
			try{
				Question q;
				q = questionService.findOne(questionId);
				Survey s;
				s = surveyService.findSurvey(q.getSurveyId());
				questions = s.getQuestions();
				questions.remove(q);
				s.setQuestions(questions);
				surveyService.saveFinal(s);
				result = new ModelAndView("question/listQuestions");
				result.addObject("questions", questions);
				result.addObject("action", "commit.ok");
				result.addObject("requestURI", "question/listQuestions.do");
			}catch(Throwable oops){
				Collection<Survey> surveys;
				Date hoy= new Date(System.currentTimeMillis() - 1000);
				surveys= surveyService.findOneByUsername(LoginService.getPrincipal().getUsername());
				result = new ModelAndView("survey/list");
				result.addObject("surveys", surveys);
				result.addObject("hoy",hoy);
				result.addObject("message", oops.getMessage());
				result.addObject("requestURI", "survey/list.do");
			}
			return result;
		}

}
