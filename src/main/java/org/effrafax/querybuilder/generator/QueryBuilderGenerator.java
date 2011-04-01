package org.effrafax.querybuilder.generator;

import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class QueryBuilderGenerator
{

	public static QueryBuilderGenerator generatorFor(Class<?> aClass)
	{
		return new QueryBuilderGenerator(aClass);
	}

	private Class<?> generateeClass;

	private QueryBuilderGenerator(Class<?> aClass)
	{
		this.generateeClass = aClass;
	}

	public void generate(Writer writer)
	{
		Template template = Velocity.getTemplate("src/main/resources/QueryBuilderTemplate.vm");

		Template referenceTemplate = Velocity.getTemplate(referenceQueryBuilderSource());
		referenceTemplate.merge(new VelocityContext(), writer);
	}

	private String referenceQueryBuilderSource()
	{
		return String.format("src/test/resources/templates/reference/reference%sQueryBuilder.vm", generateeClass
			.getSimpleName());
	}

}
