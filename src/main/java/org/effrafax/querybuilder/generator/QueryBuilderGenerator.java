package org.effrafax.querybuilder.generator;

import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		List<Map<String, String>> fieldInfos = new ArrayList<Map<String, String>>();
		for (Field field : generateeClass.getFields())
		{
			Map<String, String> fieldInfo = new HashMap<String, String>();
			fieldInfo.put("name", field.getName());
			fieldInfo.put("type", field.getType().getSimpleName());

			fieldInfos.add(fieldInfo);
		}
		context.put("fieldInfos", fieldInfos);

		template.merge(context, writer);
	}
}
