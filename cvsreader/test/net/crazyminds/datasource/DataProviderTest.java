package net.crazyminds.datasource;

import static org.junit.Assert.*;

import org.junit.Test;

import net.crazyminds.controller.Response;

public class DataProviderTest {

	DataProvider dataProvider = new DataProvider();
	
	@Test
	public final void testProvideWithWrongDateFile() {		
		Response response = dataProvider.Provide("test.csv");
		assertTrue(response.GetStatus());
	}
	
	@Test
	public final void testProvideWithCsvDateFile() {
		Response response = dataProvider.Provide("fool.txt");
		assertFalse(response.GetStatus());
	}	

}
