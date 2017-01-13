package net.crazyminds.utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import net.crazyminds.controller.Response;

public class CsvFileReader implements FileReader {



	/**
	 * Read a csv file and return as an array of strings. 
	 * Each line of the file corresponds to a roll in the array.
	 * 
	 * @param workingfilename is the name of the file to read.
	 * @param filelines is the return of the method.
	 * 
	 * @return Response
	 */
	@Override
	public Response<ArrayList<ArrayList<String>>> Read(String workingfilename )
	{
		Response<ArrayList<ArrayList<String>>> response  = new Response<ArrayList<ArrayList<String>>>();
	
		BufferedReader bReader = null;
        String line = "";
        ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
        try {
        	bReader = new BufferedReader(new InputStreamReader( new FileInputStream(workingfilename), "UTF-8"));
            while ((line = bReader.readLine()) != null) {

                lines.add(parseLine(line));
            }
            
            response.setStatus(true);
            response.setMessage("File parsed.");
            response.setValues(lines);

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
	
	

	public static ArrayList<String> parseLine(String cvsLine ) {

		 char separators = ',';
		 char customQuote = '"';
		 
		ArrayList<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
}

