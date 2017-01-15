package net.crazyminds.utilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import net.crazyminds.controller.InputController;
import net.crazyminds.controller.Response;

public class CsvFileReaderTest {

	CsvFileReader csvFileReader = new CsvFileReader();
	
	@Test
	public final void testRead() {
		Response response = csvFileReader.Read("test.csv"); 
		assertTrue(response.GetStatus());
	}

	@Test
	public final void testParseLine() {
		String[] expectedLine = new String[]{"value1","value2","value3","value4 value5"};
		String[] line = csvFileReader.parseLine("value1,value2,value3,'value4 value5'").toArray(expectedLine); 
		assertArrayEquals(expectedLine , line);
	}

}
