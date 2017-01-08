package domain;

import javax.validation.constraints.NotNull;

public class Token {

	// Constructors ------------------------------------------------------------
	
	public Token() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	boolean valid;

	@NotNull
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	// Relationships ----------------------------------------------------------

}