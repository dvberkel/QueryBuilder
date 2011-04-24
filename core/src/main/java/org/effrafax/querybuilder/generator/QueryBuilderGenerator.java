package org.effrafax.querybuilder.generator;

import java.io.File;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
		Template template = Velocity.getTemplate(getQueryBuilderTemplatePath());
		Context context = new VelocityContext();
		context.put("packageName", createPackageName());
		context.put("criteriumPackageNames", createCriteriumPackageNames());
		context.put("className", createClassName());
		context.put("fieldInfos", createFieldInfos());
		template.merge(context, writer);
	}

	private String getQueryBuilderTemplatePath()
	{
		return getClass().getResource("QueryBuilderTemplate.vm").getPath();
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
