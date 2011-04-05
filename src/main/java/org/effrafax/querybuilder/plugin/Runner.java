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
	private static final String SRC_FOLDER = "src/generated/java/";

	private static final String DIRECTORY = "org/effrafax/querybuilder/generated/builder/";

	public static void main(String[] args) throws IOException
	{
		new File(SRC_FOLDER + DIRECTORY).mkdirs();

		for (Class<?> aClass : new Class<?>[] { Example.class, SubExample.class })
		{
			QueryBuilderGenerator generator = QueryBuilderGenerator.generatorFor(aClass);
			File file = new File(
				String.format("%s%s%sQueryBuilder.java", SRC_FOLDER, DIRECTORY, aClass.getSimpleName()));
			file.createNewFile();

			Writer writer = new FileWriter(file);
			generator.generate(writer);
			writer.close();
		}

	}
}
