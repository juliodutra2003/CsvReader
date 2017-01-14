package net.crazyminds.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import net.crazyminds.Command.Count;
import net.crazyminds.Command.File;
import net.crazyminds.Command.Filter;
import net.crazyminds.Command.Show;
import net.crazyminds.model.Model;
import net.crazyminds.view.View;


/**
 * 
 * @author julio
 *
 * Main Class - Controller layer
 */

public class InputController {
	
	static BufferedReader consoleReader;
	static String workingFileName;
	static Count countCommand = new Count();
	static Show showCommand = new Show();
	static File fileCommand = new File();
	static Filter filterCommand = new Filter();
	
	public static void main(String... args) {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		 
		Response response = new Response();
		response = Initialize(args);
		if (response.GetStatus())
		{
			response = null;
			View.ShowOpenning();	
			readConsole();
		}
		else
		{
			View.ShowFatalError(response);
		}
		
		
	}
	
	static Response Initialize(String[] args )
	{
		Response response = new Response();
		
		if (args != null && args.length > 0 )
		{	
			workingFileName = args[0];
			
			response = Model.getInstance().Initialize(workingFileName);
			
			if (response.GetStatus())
			{
				return response;
			}
			else
			{
				return response;
			}
		}
		else
		{			 
            response.setStatus(false);
            response.setMessage("Need a file name as parameter.");
            return response;
		}
	}
	
	static void readConsole() {
		boolean loop_cond=true;
	    String input = "";

	    while(loop_cond == true)
	    {
	        try
	        {
	        	View.ShowCommandPrompt();
	        	input = consoleReader.readLine();
	        	ProcessCommand(input);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
		
	}
	
	
	/**
	 *
	 * Current Valid Commands:
	 * 
	 *	 help: (show valid commands)
	 *   quit: (quit program)
	 *	 count * (show the total count of registers) 
	 *	 count distinct [property] (show the total count of distinct values of a property )
	 *	 filter [property] [value] (show all lines where a property has the value)
	 *			
	 */

	private static void ProcessCommand(String input) {
		Response response = new Response();
		String[] commandline = Sanitize(input);
		
		if(commandline == null)
		{	
			response.setStatus(true);
			response.setMessage("");
			View.ShowCommandOkMessage( response );
			return;
		}
		
		String command = commandline[0];
		
		switch(command)
		{
			case "help":
			{
				View.ShowHelpMessage();
				break;
			}
			case "quit":
			{
				response.setStatus(true);
				response.setMessage("bye.");
				View.ShowQuitMessage(response);
				System.exit(0);
				break;
			}
			case "show":
			{
				response = showCommand.Interpret(commandline);		
				View.ShowPropertiesMessage( response );
				break;
			}
			case "count":
			{
				response = countCommand.Interpret(commandline);
				View.ShowCountValue( response );
				break;
			}
			case "file":
			{
				response = fileCommand.Interpret(commandline);
				View.ShowFile(response );
				break;
			}
			case "filter":
			{
				response = filterCommand.Interpret(commandline);
				View.ShowFilterValue( response );
				break;
			}
			default:
				response.setStatus(false);
				response.setMessage(command + " Command not found");
				View.ShowCommandWarningMessage( response );
				break;
		}		
	}
	
	
	/**
	 * Receives raw String[] of command line 
	 *  
	 * @param commandline is the name of the file to read.
	 * 
	 * @return return sanitized string[] line command . Null when no valid input. 
	 */
	static String[] Sanitize(String input){
		
		//return invalid command
		if(input.equals(null)||input.equals(""))
			return null;
		
		ArrayList<String> commandline = new ArrayList<String>();
		String inputStream = input;
		while (inputStream.length() > 0)
		{
			ExtratedParam extracted = ExtractParam(inputStream);
			inputStream = extracted.stream;
			if(extracted.paramameter != null)
				commandline.add(extracted.paramameter);
		}
		
		if (commandline.size() == 0)
			return null;
		
		System.out.println("Command: " + commandline);
		return commandline.toArray(new String[commandline.size()]);
	}
	
	
	/**
	 * This method extracts the first parameter found in the input string.
	 * 
	 * @param stream input string to be processed
	 * 
	 * @return ExtratedParam object with the parameter extracted (parameter) and the resultant stream (stream)
	 * 
	 * more: the original string has your content modified. the string parameter returned is removed.
	 */

	private static ExtratedParam ExtractParam(String stream) {

		ExtratedParam extratedParam = new ExtratedParam();
		
		//extracting space
		if (stream.charAt(0) == ' ')
		{
			extratedParam.stream = stream.substring(1,stream.length());
			extratedParam.paramameter = null;
		}
		//extracting param with multiple words between especial character(')
		else if (stream.charAt(0) == '\'')
		{
			String finalChar = "";
			Character nextChar = stream.charAt(0);
			int charIndex = 1;			
			while(charIndex < stream.length() && stream.charAt(charIndex) != '\'')
			{
				nextChar = stream.charAt(charIndex);	
				finalChar += nextChar;
				charIndex ++;	
			};			
			extratedParam.paramameter = (finalChar.equals("")?null:finalChar);
			extratedParam.stream = stream.substring(charIndex, stream.length());
		}
		//extracting single world param
		else
		{
			String finalChar = "";
			Character nextChar = stream.charAt(0);
			int charIndex = 0;
			while (nextChar != ' ' )
			{
				finalChar += nextChar;
				charIndex ++;
				if(charIndex == stream.length() )
					break;				
				nextChar = stream.charAt(charIndex);	
			}
			
			extratedParam.paramameter = finalChar;
			extratedParam.stream = stream.substring(charIndex, stream.length());
		}		
		
		return extratedParam;
	}
	
	private static class ExtratedParam
	{		
		String stream;
		String paramameter;
	}
}
