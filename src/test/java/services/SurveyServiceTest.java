package services;

import java.util.Collection;
import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import domain.Survey;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class SurveyServiceTest extends AbstractTest{

	//Service under test --------------------------------
	@Autowired
	private SurveyService surveyService;
	
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
		String user ="";
		
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
		
		
		surveyService.saveAddQuestion(1, 3, false);
		
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test añadir a una encuesta una pregunta //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
		
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
