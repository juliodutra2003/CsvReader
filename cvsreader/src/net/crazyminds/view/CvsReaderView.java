package net.crazyminds.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.crazyminds.controller.InputController;
import net.crazyminds.controller.Response;

public class CvsReaderView {
	
	static InputController inputController;
	static BufferedReader breader;
	
	public static void main(String... args) {
		breader = new BufferedReader(new InputStreamReader(System.in));
		String inputControllerMessage = "";
		inputController = new InputController();
		 
		Response response = new Response();
		response = inputController.Initialize(args);
		if (response.Status)
		{
			ShowCommandPrompt();			
		}
		else
		{
			ShowFatalErrorAndFinish(response);
		}
	}

	private static void ShowCommandPrompt() {
		
	    boolean loop_cond=true;
	    String n = "";

	    while(loop_cond==true)
	    {
	        try
	        {
	            System.out.println("input : ");

	            n= breader.readLine();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }

	        System.out.print(n +"\n");

	    }
		
	}
	
	private static void ShowFatalErrorAndFinish(Response response ) {
		System.out.println(response.Message);
		System.out.println("Press enter to finish...");
		try
        {
            breader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}

}
