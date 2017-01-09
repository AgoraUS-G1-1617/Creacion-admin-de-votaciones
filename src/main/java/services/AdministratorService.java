package services;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.LoginService;
import security.UserAccount;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Token;

@Service
public class AdministratorService {
	@Autowired
	private LoginService loginService;

	// M�todo que dado un useraccount hace una llamada a la API de autenticaci�n
	// y comprueba que el token generado es el mismo de ellos,
	// lo que indica que el usuario est� registrado en la aplicaci�n
	public boolean comprobarToken(UserAccount userAccount) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Token response;
		String tokenToVerify = loginService.verifyToken(userAccount);

		// Se recupera la respuesta a la petici�n
		boolean res = false;
		response = objectMapper
				.readValue(
						new URL(
								"https://beta.authb.agoraus1.egc.duckdns.org/api/index.php?method=checkTokenUser&token="
										+ tokenToVerify
										+ "&user="
										+ userAccount.getUsername()),
						Token.class);
		if (response.isValid()) {
			res = true;
		}
		return res;
	}

	// M�todo que dado un useraccount hace una llamada a la API de autenticaci�n
	// y me devuelve si es admin o no
	public boolean getRol(UserAccount userAccount) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Token response;
		// Se recupera la respuesta a la petici�n
		boolean res = false;
		response = objectMapper
				.readValue(
						new URL(
								"https://beta.authb.agoraus1.egc.duckdns.org/api/index.php?method=getRoleUser&user="
										+ userAccount.getUsername()),
						Token.class);
		if (response.equals("ADMIN") || response.equals("CREADOR DE VOTACIONES")) {
			res = true;
		}
		return res;
	}
}
