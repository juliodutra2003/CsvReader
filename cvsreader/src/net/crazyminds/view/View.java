package net.crazyminds.view;

import net.crazyminds.controller.Response;

public class View {

	public static void ShowCommandPrompt() {
		
		System.out.print("> ");	
	}
	
	public static void ShowFatalError(Response response ) {
		System.out.println(response.Message);
		System.out.println("Program Terminated.");
	}

	public static void ShowOpenning() {
		System.out.println("Welcome to CsvReader 0.1 SNAPSHOT");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Author: Julio Dutra");
		System.out.println("juliodutra2003@yahoo.com.br");
		System.out.println("----------------------------------------------------------------------");
		
	}

	public static void ShowCommandOkMessage(Response workingResponse) {
		System.out.println(workingResponse.Message);
	}

	public static void ShowCommandWarningMessage(Response workingResponse) {
		System.out.println(workingResponse.Message);
	}
	
	public static void ShowHelpMessage(Response workingResponse) {
		System.out.println(workingResponse.Message);		
	}
	
	public static void ShowQuitMessage(Response workingResponse) {
		System.out.println("Bye.");		
	}

}
