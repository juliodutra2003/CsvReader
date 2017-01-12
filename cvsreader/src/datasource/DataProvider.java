package datasource;

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
		
		//TODO if CSV FILE
		CsvFileReader csvFileReader = new CsvFileReader();
		response = csvFileReader.Read(workingFileName);
		csvFileReader=null;	
		return response;
	}

}
