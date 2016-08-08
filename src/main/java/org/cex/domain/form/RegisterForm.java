package org.cex.domain.form;

public class RegisterForm {

	String email;
	String password;
	String passwordAgain;

	String displayName;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
