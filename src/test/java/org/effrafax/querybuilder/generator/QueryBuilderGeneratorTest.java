package org.effrafax.querybuilder.generator;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.effrafax.querybuilder.test.Example;
import org.effrafax.querybuilder.test.SubExample;
import org.junit.Test;

public class QueryBuilderGeneratorTest
{
	@Test
	public void canGenerateAnExampleQueryBuilder()
	{
		QueryBuilderGenerator generator = QueryBuilderGenerator.generatorFor(Example.class);

		Writer writer = new StringWriter();
		generator.generate(writer);

		assertEquals(referenceQueryBuilderCodeFor(Example.class), writer.toString());
	}

	@Test
	public void canGenerateASubExampleQueryBuilder()
	{
		QueryBuilderGenerator generator = QueryBuilderGenerator.generatorFor(SubExample.class);

		Writer writer = new StringWriter();
		generator.generate(writer);

		assertEquals(referenceQueryBuilderCodeFor(SubExample.class), writer.toString());
	}

	private String referenceQueryBuilderCodeFor(Class<?> aClass)
	{
		Template template = Velocity.getTemplate(String.format(
			"src/test/resources/templates/reference/reference%sQueryBuilder.vm", aClass.getSimpleName()));
		Writer writer = new StringWriter();
		template.merge(new VelocityContext(), writer);
		return writer.toString();
	}
}
