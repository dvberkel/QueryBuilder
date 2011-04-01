package org.effrafax.querybuilder.generator;

import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

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
		Context context = new VelocityContext();
		context.put("className", generateeClass.getSimpleName());
		template.merge(context, writer);
	}
}
