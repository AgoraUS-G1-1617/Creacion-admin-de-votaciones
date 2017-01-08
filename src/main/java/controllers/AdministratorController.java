/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Token;

/**
 * @Class AdministratorController
 * @classDec La clase contiene el controlador que maneja las acciones del
 *           usuario con Rol Administrador
 */
@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {
	@Autowired
	private LoginService loginService;

	// Constructors -----------------------------------------------------------
	/**
	 * @return Constructor del controlador.
	 */
	public AdministratorController() {
		super();
	}

	@RequestMapping("/loginMake")
	public ModelAndView loginMake(@Valid UserAccount userAccount,
			BindingResult bindingResult, HttpServletRequest request)
			throws IOException {

		ModelAndView result = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String tokenToVerify;
		Token response;

		// Se comprueban errores en el formulario de login
		if (bindingResult.hasErrors()) {
			result = LoginModelAndView(null);

			// En caso de no haber errores
		} else {

			// Se monta el token a verificar para el usuario

			tokenToVerify = loginService.verifyToken(userAccount);

			// Se recupera la respuesta a la petición

			response = objectMapper.readValue(new URL(
					"https://autha.agoraus1.egc.duckdns.org/api/index.php?method=checkToken&token="
							+ tokenToVerify), Token.class);

			// Se comprueba que la respuesta recibida sea válida
			if (response.isValid()) {

				Md5PasswordEncoder md5 = new Md5PasswordEncoder();

				// Se comprueba que el usuario que accede exista ya en
				// Deliberaciones y se inicia sesión
				DaoAuthenticationProvider authenticator;
				Authentication authentication;

				Assert.isTrue(loginService
						.loadUserByUsername(userAccount.getUsername())
						.getPassword()
						.equals(md5.encodePassword(userAccount.getPassword(),
								null)));

				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						userAccount.getUsername(), md5.encodePassword(
								userAccount.getPassword(), null));

				token.setDetails(new WebAuthenticationDetails(request));

				authenticator = new DaoAuthenticationProvider();

//				authenticator.setUserDetailsService(userDetailsService);

				authentication = authenticator.authenticate(token);

				SecurityContextHolder.getContext().setAuthentication(
						authentication);

				result = new ModelAndView("redirect:/");

				// En caso de que la respuesta recibida no sea válida, se
				// deniega el acceso
			} else {

				result = LoginModelAndView("login.error");

			}
		}

		return result;

	}

	// Ancillary methods
	// ----------------------------------------------------------------------

	private ModelAndView LoginModelAndView(String message) {
		ModelAndView result;

		result = new ModelAndView("user/login");
		result.addObject("messageError", message);

		return result;
	}

}