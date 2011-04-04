package org.effrafax.querybuilder.plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.effrafax.querybuilder.generator.QueryBuilderGenerator;
import org.effrafax.querybuilder.test.Example;
import org.effrafax.querybuilder.test.SubExample;

public class Runner
{
	private static final String DIRECTORY = "src/main/java/org/effrafax/querybuilder/generated/builder/";

	public static void main(String[] args) throws IOException
	{
		for (Class<?> aClass : new Class<?>[] { Example.class, SubExample.class })
		{
			QueryBuilderGenerator generator = QueryBuilderGenerator.generatorFor(aClass);
			Writer writer = new FileWriter(new File(String.format("%s%sQueryBuilder.java", DIRECTORY,
				aClass.getSimpleName())));
			generator.generate(writer);
			writer.close();
		}

	}
}
