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
			response.setMessage(String.valueOf(count));
			response.values = new String[]{"0"};
		}
		else if (commandline[1] .equals("distinct"))
		{
			if (commandline.length < 3 )	
			{
				response.setStatus(false);
				response.setMessage(CommandName + " need the property name as third parameter");
				return response;
			}
			
			boolean isValid = Model.getInstance().isValidProperty(commandline[2]);
			if (!isValid)
			{
				response.setStatus(false);
				response.setMessage("property is not valid: " + commandline[2]);
				response.values = null;
				return response;
			}
//			
//			int count = Model.getInstance().GetCountDistinct(commandline[3]);
//			response.setStatus(true);
//			response.setMessage(String.valueOf(count));
//			response.values = new String[]{"0"};
		}
		else
		{
			response.setStatus(false);
			response.setMessage("wrong " + CommandName + " parameters");
		}

		return response;
	}
}
