package net.crazyminds.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.crazyminds.model.Model;

public class InputControllerTest {  
    
	@Test
	public final void testSanitizeShow() {
		String[] expResult = new String[]{"show"};
		String[] result = InputController.Sanitize(" show ");
		assertArrayEquals(expResult, result);
	}
	
	@Test
	public final void testSanitizeCount() {
		String[] expResult = new String[]{"count", "*"};
		String[] result = InputController.Sanitize(" count * ");
		assertArrayEquals(expResult, result);
	}
	
	@Test
	public final void testSanitizeDistinct() {
		String[] expResult = new String[]{"count", "distinct", "uf"};
		String[] result = InputController.Sanitize(" count distinct uf ");
		assertArrayEquals(expResult, result);
	}
	
	@Test
	public final void testSanitizeFilter() {
		String[] expResult = new String[]{"filter", "uf", "Santa Maria Copérnica"};
		String[] result = InputController.Sanitize(" filter uf 'Santa Maria Copérnica' ");
		assertArrayEquals(expResult, result);
	}
	
	@Test
	public final void testExtractParamWithQuotes() {
		String expResult = "Santa Maria Copérnica";
		String result = InputController.ExtractParam("'Santa Maria Copérnica'").paramameter;
		assertEquals(expResult, result);
	}
	
	@Test
	public final void testExtractParamWithSpaces() {
		String expResult = "Santa";
		String result = InputController.ExtractParam("Santa Maria Copérnica").paramameter;
		assertEquals(expResult, result);
	}

}
