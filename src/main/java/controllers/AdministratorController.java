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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Class AdministratorController
* @classDec La clase contiene el controlador que maneja las acciones del usuario con Rol Administrador
*/
@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	/**
	* @return Constructor del controlador.
	*/
	public AdministratorController() {
		super();
	}
		
	
	
}