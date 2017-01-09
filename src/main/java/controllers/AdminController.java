package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import services.AdministratorService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	// Services ------------------------------------------

	@Autowired
	private UserAccountRepository accountService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private UserDetailsService userDetailsService;

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
		result = createEditModelAndView(c);
		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "save")
	public ModelAndView save(UserAccount c, BindingResult binding,
			HttpServletRequest request) throws IOException {
		ModelAndView result = null;
		if (administratorService.comprobarToken(c)) {

			Collection<UserAccount> uA = accountService.findAll();
			UserAccount usuario = null;
			for (UserAccount u : uA) {
				if (u.getUsername().equals(c.getUsername())) {
					usuario = u;
				}
			}
			Md5PasswordEncoder password = new Md5PasswordEncoder();
			String encodedPassword = password.encodePassword(c.getPassword(),
					null);
			if (usuario != null) {
				usuario.setPassword(encodedPassword);
				accountService.save(usuario);
			} else {

				Authority e = new Authority();
				e.setAuthority("ADMIN");
				Collection<Authority> aux = new ArrayList<Authority>();
				aux.add(e);
				c.setAuthorities(aux);

				c.setPassword(encodedPassword);
				accountService.save(c);
			}
			System.out.println("Guardado");
			// INICIAMOS SESION
			DaoAuthenticationProvider authenticator;
			Authentication authentication;

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					c.getUsername(), password.encodePassword(c.getPassword(),
							null));

			token.setDetails(new WebAuthenticationDetails(request));

			authenticator = new DaoAuthenticationProvider();

			authenticator.setUserDetailsService(userDetailsService);

			authentication = authenticator.authenticate(token);

			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			
			result = new ModelAndView("redirect:/");
		} else {
			System.out.println("No Guardado");
			return new ModelAndView("redirect:/admin/login.do");
		}

		return result;
	}

}
