package net.crazyminds.controller;

public class Response<T> {
	private boolean Status;
	private String Message;
	private T values;	
	
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	public boolean GetStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	
	public T getValues() {
		return values;
	}
	public void setValues(T values) {
		this.values = values;
	}
	
	
}
