package org.dougllas.securitycontrol.exception.auth;

public class AuthenticationException extends RuntimeException {

	public AuthenticationException(Reason reason) {
		super(reason.getMessage());
	}
	
}

