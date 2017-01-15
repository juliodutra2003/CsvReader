package net.crazyminds.Command;

import static org.junit.Assert.*;

import org.junit.Test;

import net.crazyminds.controller.Response;
import net.crazyminds.model.FileData;
import net.crazyminds.model.Model;

public class ShowTest {

	@Test
	public final void InterpretShowTest() {
		Model.getInstance().Initialize("test.csv");
		Show show = new Show();
		Response response = show.Interpret(new String[]{"show"});
		
		String[] propertyNames = new String[]{"param1","param2"};
		assertArrayEquals(propertyNames, ((String[])response.getValues()) );
	}

}
