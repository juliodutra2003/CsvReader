package net.crazyminds.Command;

import java.util.ArrayList;
import java.util.Hashtable;

import net.crazyminds.controller.Response;
import net.crazyminds.model.FileData;
import net.crazyminds.model.Model;

public class File  implements Command  {
	
	private String CommandName = "file";

	@Override
	public Response<?> Interpret(String[] commandline) {
		Response response = new Response();
		
		int countParam = 0;
		
		if (commandline.length > 1  )	
		{
			try
			{
				countParam = Integer.parseInt( commandline[1]);
			}
			catch (NumberFormatException e)
			{
				response.setStatus(false);
				response.setMessage("Invalid parameter");
				response.setValues(null);
				return response;
			}
			
			ArrayList<ArrayList<String>> lines = Model.getInstance().GetLines(countParam);
			response.setStatus(true);
			response.setMessage(null);
			response.setValues(new FileData(Model.getInstance().GetProperties(),  lines ));
			return response;
		}
		
		ArrayList<ArrayList<String>> lines = Model.getInstance().GetLines();
		response.setStatus(true);
		response.setMessage(null);
		response.setValues(new FileData(Model.getInstance().GetProperties(),  lines ));	
		
		return response;
	}

}
