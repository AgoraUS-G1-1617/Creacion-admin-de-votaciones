package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;


@Controller
@RequestMapping("/admin")
public class AdminController {

	// Services ------------------------------------------

	@Autowired
	private UserAccountRepository accountService;

	/**
	 * @return Constructor del Controlador.
	 */
	// Constructor ---------------------------------------
	public AdminController() {
		super();
	}

	public ModelAndView createEditModelAndView(UserAccount c) {
		ModelAndView result;
		result = createEditModelAndView(c, null);

		return result;
	}

	public ModelAndView createEditModelAndView(UserAccount c, String message) {
		ModelAndView result;
		result = new ModelAndView("admin/login");
		result.addObject("userAccount", c);
		result.addObject("message", message);
		return result;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		UserAccount c = new UserAccount();
		Collection<Authority> auth = new ArrayList<Authority>();
		c.setAuthorities(auth);
		result = createEditModelAndView(c);
		return result;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserAccount c, BindingResult binding) {
		ModelAndView result=null;

//		if (binding.hasErrors()) {
//			result = createEditModelAndView(c);
//			binding.getAllErrors();
//		} else {
			if(true){
				
			
			Authority e = new Authority();
			e.setAuthority("ADMIN");
			Collection<Authority> aux = new ArrayList<Authority>();
			aux.add(e);
			c.setAuthorities(aux);
			Md5PasswordEncoder password = new Md5PasswordEncoder();
			String encodedPassword = password.encodePassword(c
					.getPassword(), null);
			c.setPassword(encodedPassword);
			accountService.saveAndFlush(c);
			System.out.println("Guardado");
			}else{
				System.out.println("Falso");
			}
//			}

		return result;
	}
	
}
