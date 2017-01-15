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
public class FileTest {

	@Test
	public final void Interpret1FileWithNoParameterTestingPropertyName() {
		Model.getInstance().Initialize("test.csv");
		File file = new File();
		Response response = file.Interpret(new String[]{"file"});
		
		String[] propertyNames = new String[]{"param1","param2"};
		assertArrayEquals(propertyNames, ((FileData)response.getValues()).getPropertyNames() );
	}
	
	@Test
	public final void Interpret2FileWithNoParameterTestingLines() {
		File file = new File();
		Response response = file.Interpret(new String[]{"file"});
		
		ArrayList<ArrayList<String>> lines = new ArrayList<>();
		ArrayList<String> line1 = new ArrayList<String>();
		line1.add("line1param1");
		line1.add("line1param2");
		ArrayList<String> line2 = new ArrayList<String>();
		line2.add("line2param1");
		line2.add("line2param2");
		lines.add(line1);
		lines.add(line2);
		assertArrayEquals(lines.get(1).toArray(), ((FileData)response.getValues()).getLines().get(1).toArray() );
	}
	
	@Test
	public final void Interpret3FileWithParameterTestingLines() {
		File file = new File();
		Response response = file.Interpret(new String[]{"file", "1"});
		
		ArrayList<ArrayList<String>> lines = new ArrayList<>();
		ArrayList<String> line1 = new ArrayList<String>();
		line1.add("line1param1");
		line1.add("line1param2");
		lines.add(line1);
		assertArrayEquals(lines.get(0).toArray(), ((FileData)response.getValues()).getLines().get(0).toArray() );
	}

}
