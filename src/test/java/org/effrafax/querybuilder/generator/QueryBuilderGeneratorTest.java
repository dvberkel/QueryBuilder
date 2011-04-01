package org.effrafax.querybuilder.generator;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
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

		assertEquals(referenceExampleQueryBuilderCode(), writer.toString());
	}

	private String referenceExampleQueryBuilderCode()
	{
		Template template = Velocity
			.getTemplate("src/test/resources/templates/reference/referenceExampleQueryBuilder.vm");
		Writer writer = new StringWriter();
		template.merge(new VelocityContext(), writer);
		return writer.toString();
	}
}
