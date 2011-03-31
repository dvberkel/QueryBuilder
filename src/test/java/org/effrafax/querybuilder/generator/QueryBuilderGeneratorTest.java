package org.effrafax.querybuilder.generator;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.io.Writer;

import org.effrafax.querybuilder.test.Example;
import org.junit.Test;

public class QueryBuilderGeneratorTest
{
	@Test
	public void canGenerateAnExampleQueryBuilder()
	{
		QueryBuilderGenerator generator = QueryBuilderGenerator.generatorFor(Example.class);

		Writer writer = new StringWriter();
		generator.generate(writer);

		assertEquals("", writer.toString());
	}
}
