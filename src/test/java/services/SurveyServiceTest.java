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
		Survey res;
		
		res = surveyService.findOne(1);
		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////// Test find one /////////////////////////////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");
		System.out.println(res.getTitle());
		
	}
	@Test
	public void testCreate(){
		Survey res;
		
		res = surveyService.create();
		
		System.out.println("\n\n\n///////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de Creaci�n de un Objeto Comment //////////////////");
		System.out.println("///////////////////////////////////////////////////////////////////////\n");
		
		System.out.println(res);
		authenticate(null);
	}
	
	@Test
	public void testSave(){
		Survey res;
		Date startDate;
		Date endDate;
		
		startDate= new Date();
		endDate= new Date();
		
		res = surveyService.create();
		res.setTitle("Que piensas de las patatas?");
		res.setDescription("Descripcion de patatas");
		res.setStartDate(startDate);
		res.setEndDate(endDate);
		res.setCensus(7);
		res.setTipo("Cuestionario");;
		
		surveyService.save(res);
		
		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de guardar una encuesta de forma persistente //////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");
		
		System.out.println("La encuesta se ha guardado con �xito");
		authenticate(null);
		
	}

	@Test
	public void testDelete(){
		
		Survey res;
		
		//Buscar ID para cada prueba
		res = surveyService.findOne(1);
		surveyService.delete(res.getId());
		
		System.out.println("\n\n\n//////////////////////////////////////////////////////////////////////////////////");
		System.out.println("////////////// Test de eliminar una encuesta de forma persistente //////////////////");
		System.out.println("//////////////////////////////////////////////////////////////////////////////////\n");
		
		System.out.println("La encuesta se ha eliminado con �xito");
		authenticate(null);
	}
	
	
	
	
	
	@Test
	public void testallFinishedSurveys(){
		
		Collection<Survey> all;
		
		all = surveyService.allFinishedSurveys();
		
		System.out.println("***Todas las encuestas finalizadas***");
		for (Survey i: all){
			System.out.println(i.getTitle() + " - " + i.getEndDate().toString());
		}
	}
	
	@Test
	public void testsaveQuestion(){
		
		Collection<Survey> all;
		
		all = surveyService.allFinishedSurveys();
		
		System.out.println("***Todas las encuestas finalizadas***");
		for (Survey i: all){
			System.out.println(i.getTitle() + " - " + i.getEndDate().toString());
		}
	}
	
}
