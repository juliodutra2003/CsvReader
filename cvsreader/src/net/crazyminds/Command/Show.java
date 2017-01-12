package net.crazyminds.Command;

import net.crazyminds.controller.Response;
import net.crazyminds.model.Model;

public class Show implements Command{

	@Override
	public Response<?> Interpret(String[] commandline) {

		Response response = new Response<>();
		response.setStatus(true);
		response.setValues(Model.getInstance().GetProperties());
		response.setMessage("");
		return response;
	}

}
