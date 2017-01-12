package net.crazyminds.Command;

import net.crazyminds.controller.Response;
import net.crazyminds.model.Model;

public class Show implements Command{

	@Override
	public Response<?> Interpret(String[] commandline) {

		String message = "Properties: \n";
		message += "----------------------------------------- \n";
		for (String s: Model.getInstance().GetProperties())
		{
			message += s + "\n";
		}
		
		Response response = new Response<>();
		response.setStatus(true);
		response.values = null;
		response.setMessage(message);
		return response;
	}

}
