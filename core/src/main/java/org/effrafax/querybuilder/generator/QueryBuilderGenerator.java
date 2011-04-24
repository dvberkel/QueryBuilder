package org.effrafax.querybuilder.generator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

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
		Template template = getTemplate();
		Context context = new VelocityContext();
		context.put("packageName", createPackageName());
		context.put("criteriumPackageNames", createCriteriumPackageNames());
		context.put("className", createClassName());
		context.put("fieldInfos", createFieldInfos());
		template.merge(context, writer);
	}

	private Template getTemplate()
	{
		Properties p = new Properties();
		p.setProperty("resource.loader", "string");
		p.setProperty("resource.loader.class", "org.apache.velocity.runtime.resource.loader.StringResourceLoader");
		Velocity.init(p);

		String templatePath = "QueryBuilderTemplate.vm";
		if (!Velocity.resourceExists(templatePath))
		{
			StringResourceRepository repo = StringResourceLoader.getRepository();
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath);
			try
			{
				repo.putStringResource(templatePath, IOUtils.toString(stream, "UTF-8"));
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}

		return Velocity.getTemplate(templatePath);
	}

	private String createPackageName()
	{
		return generateeClass.getPackage().getName();
	}

	private Collection<String> createCriteriumPackageNames()
	{
		Collection<String> criteriumPackageNames = new PriorityQueue<String>();
		criteriumPackageNames.add(createPackageNameFor(null));
		for (Field field : generateeClass.getFields())
		{
			criteriumPackageNames.add(createPackageNameFor(field.getType()));
		}
		return criteriumPackageNames;
	}

	private String createPackageNameFor(Class<?> type)
	{
		return String.format("%sPropertyCriterium", type != null ? type.getSimpleName() : "");
	}

	private String createClassName()
	{
		return generateeClass.getSimpleName();
	}

	private List<Map<String, String>> createFieldInfos()
	{
		List<Map<String, String>> fieldInfos = new ArrayList<Map<String, String>>();
		for (Field field : generateeClass.getFields())
		{
			fieldInfos.add(createFieldInfoFor(field));
		}
		return fieldInfos;
	}

	private Map<String, String> createFieldInfoFor(Field field)
	{
		Map<String, String> fieldInfo = new HashMap<String, String>();
		fieldInfo.put("name", field.getName());
		fieldInfo.put("type", field.getType().getSimpleName());
		return fieldInfo;
	}

	public File fileIn(String sourceFolder)
	{
		String packageName = generateeClass.getPackage().getName().replaceAll("\\.", "/");
		String fileName = String.format("%s%s/%sQueryBuilder.java", sourceFolder, packageName,
			generateeClass.getSimpleName());
		return new File(fileName);
	}
}
