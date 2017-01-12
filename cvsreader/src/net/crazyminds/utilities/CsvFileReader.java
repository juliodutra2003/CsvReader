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
	 * 
	 * @return Response
	 */
	
	public Response<String[]> Read(String workingfilename )
	{
		Response<String[]> response  = new Response<String[]>();
	
        BufferedReader bReader = null;
        String line = "";
        ArrayList<String> lines = new ArrayList<String>();
        try {
        	bReader = new BufferedReader(new FileReader(workingfilename));
            while ((line = bReader.readLine()) != null) {

                lines.add(line);
            }
            
            String[] filelines = new String[lines.size()];
            filelines = lines.toArray(filelines);
            response.setStatus(true);
            response.setMessage("File parsed.");
            response.values = filelines;

        } catch (FileNotFoundException e) {
            response.setStatus(false);
            response.setMessage("File " + workingfilename + " not found.");
        } catch (IOException e) {
        	e.printStackTrace();
            response.setStatus(false);
            response.setMessage(e.getStackTrace().toString());
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

