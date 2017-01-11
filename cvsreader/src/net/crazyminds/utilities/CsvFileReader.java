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
	
	public Response Read(String[] args, String[] filelines )
	{
		Response response  = new Response();
		
		if (args != null && args.length > 0 )
		{		
			String fileName = args[0];
	        BufferedReader bReader = null;
	        String line = "";
	        String cvsSeparator = ",";
	        ArrayList<String> lines = new ArrayList<>();
	        try {

	        	bReader = new BufferedReader(new FileReader(fileName));
	            while ((line = bReader.readLine()) != null) {

	                lines.add(line);
	                System.out.println("Line: [ " + line + " ]");
	            }
	            
	            filelines = (String[])lines.toArray();
	            response.Status = true;
	            response.Message = "File parsed.";

	        } catch (FileNotFoundException e) {
	        	e.printStackTrace();
	            response.Status = false;
	            response.Message = e.getStackTrace().toString();
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
		}
		else
		{
            response.Status = false;
            response.Message = "Need a file name as parameter.";
		}
		
		return response;
	}

}

