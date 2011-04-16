package org.effrafax.querybuilder.plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.effrafax.querybuilder.generator.QueryBuilderFactoryGenerator;
import org.effrafax.querybuilder.generator.QueryBuilderGenerator;
import org.effrafax.querybuilder.test.Example;
import org.effrafax.querybuilder.test.SubExample;

public class Runner
{
	private static final String SRC_FOLDER = "src/generated/java/";

	public static void main(String[] args) throws IOException
	{

		Class<?>[] classes = new Class<?>[] { Example.class, SubExample.class };
		for (Class<?> aClass : classes)
		{
			QueryBuilderGenerator generator = QueryBuilderGenerator.generatorFor(aClass);
			File file = generator.fileIn(SRC_FOLDER);
			file.getParentFile().mkdirs();
			file.createNewFile();

			Writer writer = new FileWriter(file);
			generator.generate(writer);
			writer.close();
		}

		QueryBuilderFactoryGenerator factoryGenerator = QueryBuilderFactoryGenerator.generatorFor(classes);
		factoryGenerator.setPackage("org.effrafax.querybuilder");
		File factoryFile = factoryGenerator.fileIn(SRC_FOLDER);
		factoryFile.createNewFile();

		Writer factoryWriter = new FileWriter(factoryFile);
		factoryGenerator.generate(factoryWriter);
		factoryWriter.close();
	}
}
