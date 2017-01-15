package net.crazyminds.Command;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.crazyminds.controller.Response;
import net.crazyminds.model.Model;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountTest {

	@Test
	public final void Interpret1CountNoParameter() {
		Model.getInstance().Initialize("test.csv");
		Count count = new Count();
		Response response = count.Interpret(new String[]{"count"});
		
		assertEquals("wrong count parameters", response.getMessage() );
	}
		
	@Test
	public final void Interpret2CountAsterisk() {
		Count count = new Count();
		Response response = count.Interpret(new String[]{"count","*"});
		
		assertEquals(2, Integer.valueOf( (String)response.getValues()).intValue() );
	}
	
	
	@Test
	public final void Interpret3CountDistinct() {
		Count count = new Count();
		Response response = count.Interpret(new String[]{"count","distinct","param1"});
		
		assertEquals(2, Integer.valueOf( (String)response.getValues()).intValue() );
	}
	
	@Test
	public final void Interpret4CountDistinctWithWrongThirdParameter() {
		Count count = new Count();
		Response response = count.Interpret(new String[]{"count","distinct", "wrongparameter"});
		
		assertEquals("property is not valid: wrongparameter", response.getMessage() );
	}

	@Test
	public final void Interpret5CountDistinct() {
		Count count = new Count();
		Response response = count.Interpret(new String[]{"count","distinct"});
		
		assertEquals("count need the property name as third parameter", response.getMessage() );
	}
	
	@Test
	public final void Interpret6AllWrongParameters() {
		Count count = new Count();
		Response response = count.Interpret(new String[]{"count","bar", "foo"});
		
		assertEquals("wrong count parameters", response.getMessage() );
	}

}
