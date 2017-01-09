package domain;

import javax.validation.constraints.NotNull;

public class Token {

	// Constructors ------------------------------------------------------------
	
	public Token() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	boolean valid;
	String role;

	@NotNull
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Relationships ----------------------------------------------------------

}