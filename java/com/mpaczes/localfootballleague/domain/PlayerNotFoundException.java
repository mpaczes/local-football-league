package com.mpaczes.localfootballleague.domain;

public class PlayerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public PlayerNotFoundException() {
		// do nothing ..
	}
	
	public PlayerNotFoundException(String message) {
		this.message = message;
	}
	
}
