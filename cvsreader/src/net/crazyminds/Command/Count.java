package net.crazyminds.Command;

import net.crazyminds.controller.Response;
import net.crazyminds.model.Model;

public class Count implements Command {

	private String CommandName = "count";
	
		
	@Override
	public Response Interpret(String[] commandline) {
		
		Response response = new Response();
		
		if (commandline.length < 2 )	
		{
			response.setStatus(false);
			response.setMessage("wrong " + CommandName + " parameters");
		}
		else if (commandline[1].equals("*"))		
		{
			int count = Model.getInstance().GetTotalCount();
			response.setStatus(true);
			response.setMessage("");
			response.setValues(String.valueOf(count));
		}
		else if (commandline[1] .equals("distinct"))
		{
			if (commandline.length < 3 )	
			{
				response.setStatus(false);
				response.setMessage(CommandName + " need the property name as third parameter");
				response.setValues(null);
				return response;
			}
			
			String targetProperty = commandline[2];
			boolean isValid = Model.getInstance().isValidProperty(targetProperty);
			if (!isValid)
			{
				response.setStatus(false);
				response.setMessage("property is not valid: " + targetProperty);
				response.setValues(null);
				return response;
			}
	
			int count = Model.getInstance().GetCountDistinct(targetProperty);
			response.setStatus(true);
			response.setMessage("");
			response.setValues(String.valueOf(count));
		}
		else
		{
			response.setStatus(false);
			response.setMessage("wrong " + CommandName + " parameters");
			response.setValues(null);
		}

		return response;
	}
}
