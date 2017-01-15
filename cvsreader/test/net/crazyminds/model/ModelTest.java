package net.crazyminds.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.crazyminds.controller.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModelTest {

	@Test
	public final void test1Initialize() {
		Response resp = Model.getInstance().Initialize("test.csv");
		boolean expResult = true;
		boolean result = resp.GetStatus();
		assertEquals(expResult, result);
	}

	@Test
	public final void test2GetTotalCount() {
		int expResult = 2;
		int result = Model.getInstance().GetTotalCount();
		assertEquals(expResult, result);
	}

	@Test
	public final void test3IsValidProperty() {
		boolean expResult = true;
		boolean result = Model.getInstance().isValidProperty("param1");
		assertEquals(expResult, result);
	}

	@Test
	public final void test4GetProperties() {
		String[] expResult = new String[2];
		expResult[0] = "param1";
		expResult[1] = "param2";
		String[] result = Model.getInstance().GetProperties();
		assertArrayEquals(expResult, result);
	}

	@Test
	public final void test5GetCountDistinct() {
		int expResult = 2;
		int result = Model.getInstance().GetCountDistinct("param1");
		assertEquals(expResult, result);
		
	}

	@Test
	public final void test6GetLinesStringString() {
		ArrayList<ArrayList<String>> result = Model.getInstance().GetLines("param1", "line1param1");
		assertEquals(1, result.size());
	}

	@Test
	public final void testGet7Lines() {
		assertEquals(2, Model.getInstance().GetLines().size());
	}

	@Test
	public final void testGet8LinesInt() {
		assertEquals(1, Model.getInstance().GetLines(1).size());
	}

}
