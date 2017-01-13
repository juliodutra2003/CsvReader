package net.crazyminds.utilities;

import java.util.ArrayList;

import net.crazyminds.controller.Response;

public interface FileReader {
	
	public Response<ArrayList<ArrayList<String>>> Read(String workingfilename );

}
