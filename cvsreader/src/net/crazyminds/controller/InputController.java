package net.crazyminds.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import net.crazyminds.Command.Count;
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
	static Filter filterCommand = new Filter();
	
	public static void main(String... args) {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		 
		Response response = new Response();
		response = Initialize(args);
		if (response.GetStatus())
		{
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
		
		String[] inputSplited = input.split("\\s");
		
		if(inputSplited.length > 0)//add command
		{
			ArrayList<String> commandline = new ArrayList<String>();
			
			commandline.add(inputSplited[0]); //add first parameter
			if(inputSplited.length > 1)
			{
				commandline.add(inputSplited[1]);	
				
				if(inputSplited.length > 2) //add rest of comandline
				{
					for (int i = 2 ; i < inputSplited.length; i++ )
						commandline.add(inputSplited[i]);
				}	
			}	
			//return valid command
			return commandline.toArray(new String[commandline.size()]);
		}
		else //return invalid command
		{
			return null;
		}
		
//		ArrayList<String> sanitizedCommandline = new ArrayList<String>();
//		 
//		System.out.print("sanitizedCommandline: raw commandline ");
//		for (String c: commandline)
//		{
//			System.out.print(c+",");
//		}
//		System.out.println("");
//		
//		for(String s: commandline)
//		{
//			if( s.contains("'") )
//			{
//				int firstIndex = s.indexOf("'");
//				int lastIndex = s.lastIndexOf("'");
//				if(firstIndex < lastIndex)
//				{
//					sanitizedCommandline.add(s.substring(firstIndex+1, lastIndex));
//				}
//				else //command invalid
//				{
//					sanitizedCommandline.add(s);
//				}
//			}
//			else if( !s.equals("") && !s.equals(" ") )
//			{
//				sanitizedCommandline.add(s);
//			}
//		}
//		
//		System.out.println("sanitizedCommandline: " + sanitizedCommandline);
//		
//		return sanitizedCommandline.toArray(commandline);		
	}
}
