package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Question;
import domain.Survey;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SurveyServiceTest extends AbstractTest{

	//Service under test --------------------------------
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private QuestionService questionService;
	
	//Supporting Services -------------------------
	

	
	//Tests ------------------------------------------
	
	@Test
	public void testFindOne(){
		int id=0;
		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////// Test find one /////////////////////////////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println("Id " + s.getId() + ", " + s );
			id=s.getId();
		}
		System.out.println("Mostraremos el último survey almacenando el id del último de la lista");
		System.out.println("");
		System.out.println(surveyService.findOne(id));
		System.out.println("");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFindOneNegativo(){
		int id=485;
		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////// Test find one negativo /////////////////////////////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println("Id " + s.getId() + ", " + s );
		}
		System.out.println("Intentaremos mostrar un survey con id inexistente, en este caso: " + id);
		System.out.println("");
		System.out.println("Si tras esto, el test no muestra nada más, pero la ejecución JUnit ha sido correcta, todo ha ido bien");
		System.out.println("");
		System.out.println(surveyService.findOne(id));
		System.out.println("");
		
	}
	@Test
	public void testCreate(){
		Survey res;
		
		res = surveyService.create();
		
		System.out.println("\n\n\n///////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de Creación de un Objeto Survey //////////////////");
		System.out.println("///////////////////////////////////////////////////////////////////////\n");
		
		System.out.println(res);
	}
	
	@Test
	public void testSave(){
		Survey res;
		Date startDate;
		Date endDate;
		String user ="pollale";
		
		startDate= new Date();
		endDate= new Date();
		
		
		res = surveyService.create();
		res.setTitle("Cuestionario prueba");
		res.setDescription("Descripcion de cuestionerio de pruebas");
		res.setStartDate(startDate);
		res.setEndDate(endDate);
		res.setCensus(7);
		res.setTipo("Cuestionario");
		res.setPostalCode("41015");
		
		
		
		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de guardar una encuesta de forma persistente //////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");
		
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Guardamos el survey: " + res);
		System.out.println("");
		surveyService.save(res,user);
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
		}
		System.out.println("");
	}
	
	
	@Test(expected = ConstraintViolationException.class)
	public void testSaveNegativo(){
		Survey res;
		Date startDate;
		Date endDate;
		String user ="pollale";
		startDate= new Date();
		endDate= new Date();
		res = surveyService.create();
		res.setTitle("Cuestionario prueba");
		res.setDescription("Descripcion de cuestionerio de pruebas");
		res.setStartDate(startDate);
		res.setEndDate(endDate);
		res.setCensus(7);
		res.setTipo("Cuestionario");
		res.setPostalCode("");
		
		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test negativo de guardar una encuesta //////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");
		
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Intentamos guardar con un código postal menor que 5 el siguiente survey");
		System.out.println(res);
		System.out.println("");
		System.out.println("Si tras esto, el test no muestra nada más, pero la ejecución JUnit ha sido correcta, todo ha ido bien");
		System.out.println("");
		surveyService.save(res,user);
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
		}
		System.out.println("");
	}
	

	@Test
	public void testDelete(){
		Survey res=new Survey();
		
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de eliminar una encuesta de forma persistente //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
			res=s;
		}
		System.out.println("");
		System.out.println("Eliminamos el último survey");
		System.out.println("");
		surveyService.delete(res.getId());
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
		}
		System.out.println("");
	}
	
	@Test(expected = NullPointerException.class)
	public void testDeleteNegativo(){
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test negativo de eliminar una encuesta de forma persistente //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Eliminamos un survey con id inexistente");
		System.out.println("");
		System.out.println("Si tras esto, el test no muestra nada más, pero la ejecución JUnit ha sido correcta, todo ha ido bien");
		System.out.println("");
		surveyService.delete(485);
		System.out.println("Todos los surveys: ");
		for(Survey s: surveyService.allSurveys()){
			System.out.println(s);
		}
		System.out.println("");
	}
		
	
	
	
	
	
	@Test
	public void testallFinishedSurveys(){
		
		Collection<Survey> all;
		
		all = surveyService.allFinishedSurveys();
		
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de mostrar todas las encuestas finalizadas //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
	
		for (Survey i: all){
			System.out.println(i.getTitle() + " - " + i.getEndDate().toString());
		}
	}
	
	@Test
	public void testsaveQuestion(){
		
		
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test añadir a una encuesta una pregunta //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
		
		System.out.println("Encuesta:");
		Survey s = new Survey();
		s.setTitle("Encuesta de prueba");
		s.setDescription("Descripción");
		s.setCensus(1500);
		s.setEndDate(new Date());
		s.setStartDate(new Date());
		s.setPostalCode("41500");
		s.setTipo("Tipo");
		s.setUsernameCreator("Usuario");
		s.setQuestions(new ArrayList<Question>());
		s = surveyService.findOne(surveyService.save(s, s.getUsernameCreator()));
		System.out.println(s.getId() + " Preguntas: " + s.getQuestions());
		Question q = new Question();
		q.setText("Pregunta de prueba");
		q = questionService.findOne(questionService.saveAndFlush(q));
		surveyService.saveAddQuestion(s.getId(), q.getId(), true);
		System.out.println("Preguntas despues de añadir: " + s.getQuestions());
		
		System.out.println("La pregunta se ha añadido con exito");

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testsaveQuestionNegativo(){
		
		
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test negativo de añadir a una encuesta una pregunta //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
		
		System.out.println("Encuesta:");
		Survey s = new Survey();
		s.setTitle("Encuesta de prueba");
		s.setDescription("Descripción");
		s.setCensus(1500);
		s.setEndDate(new Date());
		s.setStartDate(new Date());
		s.setPostalCode("41500");
		s.setTipo("Tipo");
		s.setUsernameCreator("Usuario");
		s.setQuestions(new ArrayList<Question>());
		s = surveyService.findOne(surveyService.save(s, s.getUsernameCreator()));
		System.out.println(s.getId() + " Preguntas: " + s.getQuestions());
		Question q = new Question();
		q.setText("");
		q = questionService.findOne(questionService.saveAndFlush(q));
		System.out.println("Intentamos añadir una question con texto vacío");
		System.out.println("");
		System.out.println("Si tras esto, el test no muestra nada más, pero la ejecución JUnit ha sido correcta, todo ha ido bien");
		System.out.println("");
		Survey s2 = surveyService.saveAddQuestion(s.getId(), q.getId(), true);
		System.out.println("Preguntas despues de añadir: " + s2.getQuestions());
		
		System.out.println("La pregunta se ha añadido con exito");

	}
	
	@Test
	public void testallCreatedSurveys(){
		
		Collection<Survey> all;
		
		all = surveyService.findOneByUsername("pepe");
		
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de mostrar todas las encuestas de un usuario //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
	
		for (Survey i: all){
			System.out.println(i.getTitle() + " - " + i.getEndDate().toString());
		}
	}
	
}
