package net.crazyminds.controller;

import net.crazyminds.utilities.CsvFileReader;

public class InputController {
	
	CsvFileReader multiLineFileReader = new CsvFileReader();
	
	public InputController()
	{
		
	}
	
	public Response Initialize(String[] args )
	{
		String[] lines = null;
		Response resp = multiLineFileReader.Read(args, lines);
		
		if (resp.Status)
		{
			return resp;
		}
		else
		{
			return resp;
		}
	}

}
