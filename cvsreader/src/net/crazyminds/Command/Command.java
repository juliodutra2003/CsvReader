package net.crazyminds.Command;

import net.crazyminds.controller.Response;

public interface Command {

	public Response<?> Interpret(String[] commandline);
}
