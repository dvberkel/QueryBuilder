package org.effrafax.querybuilder.generator;

import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

public class QueryBuilderFactoryGenerator
{
	private String packageName;

	private List<Class<?>> classes;

	public static QueryBuilderFactoryGenerator generatorFor(Class<?>[] classes)
	{
		return new QueryBuilderFactoryGenerator(Arrays.asList(classes));
	}

	public QueryBuilderFactoryGenerator(List<Class<?>> classes)
	{
		this.classes = classes;
	}

	public void setPackage(String packageName)
	{
		this.packageName = packageName;
	}

	public File fileIn(String sourceFolder)
	{
		return new File(String.format("%s%sQueryBuilderFactory.java", sourceFolder, directory()));
	}

	private String directory()
	{
		return packageName.replaceAll("\\.", "/").concat("/");
	}

	public void generate(Writer writer)
	{
		Template template = Velocity.getTemplate(getQueryBuilderFactoryTemplatePath());
		Context context = new VelocityContext();
		context.put("packageName", packageName);
		context.put("nameInfos", createNameInfo());
		template.merge(context, writer);
	}

	private String getQueryBuilderFactoryTemplatePath()
	{
		return getClass().getResource("QueryBuilderFactoryTemplate.vm").getPath();
	}

	private Collection<Map<String, String>> createNameInfo()
	{
		Collection<Map<String, String>> classNameInfos = new ArrayList<Map<String, String>>();
		for (Class<?> aClass : classes)
		{
			classNameInfos.add(nameInfoFor(aClass));
		}
		return classNameInfos;
	}

	private Map<String, String> nameInfoFor(Class<?> aClass)
	{
		Map<String, String> classNameInfo = new HashMap<String, String>();
		classNameInfo.put("className", classNameFor(aClass));
		classNameInfo.put("packageName", packageNameFor(aClass));
		classNameInfo.put("methodName", methodNameFor(aClass));
		return classNameInfo;
	}

	private String classNameFor(Class<?> aClass)
	{
		return aClass.getSimpleName();
	}

	private String packageNameFor(Class<?> aClass)
	{
		return aClass.getPackage().getName();
	}

	private String methodNameFor(Class<?> aClass)
	{
		String simpleName = aClass.getSimpleName();
		return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
	}
}
