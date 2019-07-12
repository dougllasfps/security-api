package org.dougllas.securitycontrol.exception.auth;

public enum Reason {

	USER_NOT_FOUND("Usuário não encontrado."), 
	PASSWORD_DOESNT_MATCH("Senha inválida.");

	private String message;

	Reason(String message) {
		this.message = message;
	}

	String getMessage() {
		return this.message;
	}

}
