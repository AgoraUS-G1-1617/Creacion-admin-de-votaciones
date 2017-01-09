package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TokenServiceTest extends AbstractTest{
	

	//Service under test --------------------------------
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private LoginService loginService;
	//Supporting Services -------------------------
	

	//Tests ------------------------------------------
	
	@Test
	public void testQuestionsListing(){
		UserAccount userAccount= new UserAccount();
		userAccount.setUsername("admin");
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		userAccount.setPassword("21232f297a57a5a743894a0e4a801fc3");
		userAccountRepository.save(userAccount);
		System.out.println(loginService.verifyToken(userAccount));

		
		

		
	}
}
