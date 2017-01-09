package domain;

import javax.validation.constraints.NotNull;

public class Rol {

	// Constructors ------------------------------------------------------------
	
	public Rol() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	String role;

	@NotNull
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Relationships ----------------------------------------------------------

}