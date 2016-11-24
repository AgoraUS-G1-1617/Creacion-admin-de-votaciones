package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

import domain.Question;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class QuestionServiceTest extends AbstractTest{
	

	//Service under test --------------------------------
	@Autowired
	private QuestionService questionService;
	
	//Supporting Services -------------------------
	

	//Tests ------------------------------------------
	
	@Test
	public void testFindOne(){
		Question res;
		
		res = questionService.findOne(2);

		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////// Test find one /////////////////////////////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");

		System.out.println(res.getText());
		
	}
	
	@Test
	public void testCreateString(){
		Question res;
		String question;
		
		question = "¿de que color era el caballo blaco de Santiago?";
		
		res = questionService.create(question);

		System.out.println("\n\n\n/////////////////////////////////////////////////////////////////////////////////");
		System.out.println("/////////////////////// Test persistir una pregunta desde un String///////////////////////////////////");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n");

		System.out.println(res.getText());
		
	}
	



}
