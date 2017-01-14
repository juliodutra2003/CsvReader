package net.crazyminds.Command;

import java.util.ArrayList;
import java.util.Hashtable;

import net.crazyminds.controller.Response;
import net.crazyminds.model.FileData;
import net.crazyminds.model.Model;

public class Filter implements Command {
	
	private String CommandName = "filter";

	@Override
	public Response<?> Interpret(String[] commandline) {
		
		Response response = new Response();
		
		if (commandline.length < 3  )	
		{
			response.setStatus(false);
			response.setMessage("wrong " + CommandName + " parameters");
			return response;
		}
		
		String targetProperty = commandline[1];
		String targetValue = commandline[2];
		
		boolean isValidProperty = Model.getInstance().isValidProperty(targetProperty);
		
		if (!isValidProperty)
		{
				response.setStatus(false);
				response.setMessage("property is not valid: " + targetProperty);
				response.setValues(null);
				return response;
		}	
		
		ArrayList<ArrayList<String>> lines = Model.getInstance().GetLines(targetProperty , targetValue);
		response.setStatus(true);
		response.setMessage(null);
		response.setValues(new FileData(Model.getInstance().GetProperties(),  lines ));		

		return response;
	}

}
