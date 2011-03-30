package org.effrafax.velocity;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

public class VelocityTest
{
	public static final String TEMPLATE_DIR = "src/test/resources/";

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
}