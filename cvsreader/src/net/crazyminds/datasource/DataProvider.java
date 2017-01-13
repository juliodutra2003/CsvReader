package net.crazyminds.datasource;

import net.crazyminds.controller.Response;
import net.crazyminds.utilities.CsvFileReader;


/**
 *  
 * @author julio
 * 
 * This class knows what source to use to get data from file name extension
 * 
 * workingFileName is the filename
 */

public class DataProvider {

	public Response Provide(String workingFileName) {

		Response response = new Response();
		
		int lastIndexOfScore = workingFileName.lastIndexOf(".");
		String fileExtension = workingFileName.substring(lastIndexOfScore+1, workingFileName.length());
		fileExtension = fileExtension.toLowerCase();
		
		switch (fileExtension)
		{
			case "csv":
				CsvFileReader csvFileReader = new CsvFileReader();
				response = csvFileReader.Read(workingFileName);
				csvFileReader=null;	
				return response;
				
			case "xml":
				//TODO if CSV FILE
				response.setStatus(false);
				response.setMessage("ERROR: XML files not supported yet.");
				response.setValues(null);
				return response;
				
			case "json":
				//TODO if JSON FILE	
				response.setStatus(false);
				response.setMessage("ERROR: JSON files not supported yet.");
				response.setValues(null);				
				return response;
				
				default:
					response.setStatus(false);
					response.setMessage("ERROR: file type not supported: " + fileExtension);
					response.setValues(null);				
					return response;
		}

	}

}
