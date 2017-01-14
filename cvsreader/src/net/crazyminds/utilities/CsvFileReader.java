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
            while ((line = bReader.readLine()) != null) 
            {
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
	
	
	/**
	 * Parses each line of the csv file
	 * 
	 * 
	 * @param cvsLine raw file line like (city,car,house)
	 * @return ArrayList<String>, which contains the strings of the line
	 * 
	 * ps:if a line is empty, returns null 
	 * ps2: \r = CR (Carriage Return) // Used as a new line character in Mac OS before X
	 *		\n = LF (Line Feed) // Used as a new line character in Unix/Mac OS X
	 *		\r\n = CR + LF // Used as a new line character in Windows
	 */

	public static ArrayList<String> parseLine(String cvsLine ) {

		 char charSeparator = ',';
		 char customCharQuote = '"';
		 
		ArrayList<String> line = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return line;
        }

        StringBuffer stringBuffer = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars)
        {
            if (inQuotes)
            {
                startCollectChar = true;
                if (ch == customCharQuote) 
                {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                }
                else
                {
                    if (ch == '\"') 
                    {
                        if (!doubleQuotesInColumn) 
                        {
                        	stringBuffer.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } 
                    else 
                    {
                    	stringBuffer.append(ch);
                    }
                }
            } 
            else 
            {
                if (ch == customCharQuote) 
                {
                    inQuotes = true;

                    if (startCollectChar) 
                    {
                    	stringBuffer.append('"');
                    }

                } 
                else if (ch == charSeparator) 
                {
                	line.add(stringBuffer.toString());

                	stringBuffer = new StringBuffer();
                    startCollectChar = false;
                } 
                else if (ch == '\r')
                {
                    continue;
                } 
                else if (ch == '\n')
                {
                    break;
                }
                else
                {
                	stringBuffer.append(ch);
                }
            }

        }

        line.add(stringBuffer.toString());

        return line;
    }
}

