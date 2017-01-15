package net.crazyminds.Command;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.crazyminds.controller.Response;
import net.crazyminds.model.FileData;
import net.crazyminds.model.Model;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilterTest {

	@Test
	public final void Interpret1FilterNoParameter() {
		Model.getInstance().Initialize("test.csv");
		Filter filter = new Filter();
		Response response = filter.Interpret(new String[]{"filter"});
		
		assertEquals("wrong filter parameters", response.getMessage() );
	}
	
	@Test
	public final void Interpret2FilterOnlyTwoParameters() {
		Model.getInstance().Initialize("test.csv");
		Filter filter = new Filter();
		Response response = filter.Interpret(new String[]{"filter", "foo"});
		
		assertEquals("wrong filter parameters", response.getMessage() );
	}
	
	@Test
	public final void Interpret3FilterWithWrongProperty() {
		Model.getInstance().Initialize("test.csv");
		Filter filter = new Filter();
		Response response = filter.Interpret(new String[]{"filter", "foo", "foo"});
		
		assertEquals("property is not valid: foo", response.getMessage() );
	}
	
	@Test
	public final void Interpret4FilterWithWrongValue() {
		Model.getInstance().Initialize("test.csv");
		Filter filter = new Filter();
		Response response = filter.Interpret(new String[]{"filter", "param2", "foo"});
		assertEquals(0, ((FileData)response.getValues()).getLines().size() );
	}

	@Test
	public final void Interpret5FilterWithValue() {
		Model.getInstance().Initialize("test.csv");
		Filter filter = new Filter();
		Response response = filter.Interpret(new String[]{"filter", "param2", "line2param2"});
		ArrayList<ArrayList<String>> lines = new ArrayList<>();
		ArrayList<String> line2 = new ArrayList<String>();
		line2.add("line2param1");
		line2.add("line2param2");
		lines.add(line2);
		assertArrayEquals(lines.get(0).toArray(), ((FileData)response.getValues()).getLines().get(0).toArray() );
	}
}
