package com.tcs.bbsr.ats.bi;

public class AtsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	String 		errorCode;
	String 		shortText;
	String		description;
	Exception 	rootException;
	
	public AtsException(String errorCode, String shortText) {
		this.errorCode = errorCode;
		this.shortText = shortText;
	}
	
	public AtsException(String errorCode, String shortText, String description) {
		this(errorCode, shortText);
		this.description = description;
	}
	
	public AtsException(String errorCode, String shortText, String description, Exception e) {
		this(errorCode, shortText, description);
		this.rootException = e;
	}
	
	public AtsException(String shortText, Exception e) {
		this(null, shortText, null, e);
		
	}
	public AtsException(String shortText, String description, Exception e) {
		this(null, shortText, description, e);
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getShortText() {
		return shortText;
	}
	public void setShortText(String shortText) {
		this.shortText = shortText;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Exception getRootException() {
		return rootException;
	}
	public void setRootException(Exception rootException) {
		this.rootException = rootException;
	}
}
