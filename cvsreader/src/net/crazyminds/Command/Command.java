package net.crazyminds.Command;

import net.crazyminds.controller.Response;

/**
 * 
 * @author julio
 *
 * Command interfaces - Signature for every command sub-class
 * 
 * (Strategy Pattern)
 */

public interface Command {

	public Response<?> Interpret(String[] commandline);
}
