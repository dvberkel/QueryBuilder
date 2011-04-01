package org.effrafax.querybuilder.generator;

import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.effrafax.querybuilder.test.Example;

public class QueryBuilderGenerator
{

	public static QueryBuilderGenerator generatorFor(Class<Example> aClass)
	{
		return new QueryBuilderGenerator();
	}

	public void generate(Writer writer)
	{
		Template template = Velocity
			.getTemplate("src/test/resources/templates/reference/referenceExampleQueryBuilder.vm");
		template.merge(new VelocityContext(), writer);
	}

}
