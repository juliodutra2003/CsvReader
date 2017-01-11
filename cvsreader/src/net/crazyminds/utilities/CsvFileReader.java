package net.crazyminds.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import net.crazyminds.controller.Response;

public class CsvFileReader {
	
	
	public CsvFileReader()
	{
		
	}
	
	/**
	 * Read a csv file and return as an array of strings. 
	 * Each line of the file corresponds to a roll in the array.
	 * 
	 * @param workingfilename is the name of the file to read.
	 * @param filelines is the return of the method.
	 * @return 
	 */
	
	public Response Read(String workingfilename, String[] filelines )
	{
		Response response  = new Response();
	
        BufferedReader bReader = null;
        String line = "";
        ArrayList<String> lines = new ArrayList<String>();
        try {
        	bReader = new BufferedReader(new FileReader(workingfilename));
            while ((line = bReader.readLine()) != null) {

                lines.add(line);
            }
            
            filelines = new String[lines.size()];
            filelines = lines.toArray(filelines);
            response.Status = true;
            response.Message = "File parsed.";

        } catch (FileNotFoundException e) {
            response.Status = false;
            response.Message = "File " + workingfilename + " not found.";
        } catch (IOException e) {
        	e.printStackTrace();
            response.Status = false;
            response.Message = e.getStackTrace().toString();
        } finally {
            if (bReader != null) {
                try {
                	bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		
		return response;
	}

}

