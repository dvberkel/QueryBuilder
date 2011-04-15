package org.effrafax.velocity;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.junit.Test;

public class VelocityTest
{
	public static final String TEMPLATE_DIR = "src/test/resources/templates/";

	@Test
	public void stringWriterTest() throws IOException
	{
		String expected = "Hello World";

		Writer writer = new StringWriter();
		for (char character : expected.toCharArray())
		{
			writer.append(character);
		}

		assertEquals(expected, writer.toString());
	}

	@Test
	public void velocityUsageWithoutReferences()
	{
		Template template = Velocity.getTemplate(TEMPLATE_DIR + "hello.vm");

		Writer writer = new StringWriter();
		template.merge(new VelocityContext(), writer);

		assertEquals("Hello World", writer.toString());
	}

	@Test
	public void velocityUsageWithReferences()
	{
		Template template = Velocity.getTemplate(TEMPLATE_DIR + "helloReferences.vm");
		VelocityContext context = new VelocityContext();
		context.put("greeting", "Hello");
		context.put("greeted", "World");

		Writer writer = new StringWriter();
		template.merge(context, writer);

		assertEquals("Hello World", writer.toString());
	}

	@Test
	public void velocityUsageWithMap()
	{

		Template template = Velocity.getTemplate(TEMPLATE_DIR + "helloMap.vm");
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("greeting", "Hello");
		mapping.put("greeted", "World");
		Context context = new VelocityContext();
		context.put("mapping", mapping);

		Writer writer = new StringWriter();
		template.merge(context, writer);

		assertEquals("Hello World", writer.toString());
	}

	@Test
	public void velocityUsageWithList()
	{

		Template template = Velocity.getTemplate(TEMPLATE_DIR + "helloList.vm");
		List<String> words = new ArrayList<String>();
		words.add("Hello");
		words.add(" ");
		words.add("World");
		Context context = new VelocityContext();
		context.put("words", words);

		Writer writer = new StringWriter();
		template.merge(context, writer);

		assertEquals("Hello World", writer.toString());
	}
}