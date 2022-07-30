package com.bbenslimane.app.ws.responses;

public enum ErrorMessages {
	
	
	Missing_REQUIRED_FIELD("Missing Required Field."),
	RECORD_ALREADY_EXISTS("Record already exists."),
	INTERNAL_SERVER_ERROR("Internal Bilal server error."),
	NO_RECORD_FOUND("Record with provided id is not found");
	
	private String ErrorMessage;

	private ErrorMessages(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	


}
