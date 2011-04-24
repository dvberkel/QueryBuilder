package org.effrafax.querybuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.effrafax.querybuilder.generator.QueryBuilderFactoryGenerator;
import org.effrafax.querybuilder.generator.QueryBuilderGenerator;
import org.effrafax.querybuilder.test.Example;
import org.effrafax.querybuilder.test.SubExample;

/**
 * Goal which generates QueryBuilders and the QueryBuilderFactory.
 * 
 * @goal generate
 * 
 * @phase generate-sources
 */
public class QueryBuilderPlugin extends AbstractMojo
{
	/**
	 * Location of the directory which holds the generated files.
	 * 
	 * @parameter expression="${querybuilder.generatedSourceDirectoryName}" default-value="src/generated/java"
	 * @required
	 */
	private String generatedSourceDirectoryName;

	private Class<?>[] classes = new Class<?>[] { Example.class, SubExample.class };

	private String factoryPackageName = "org.effrafax.querybuilder";

	public void execute() throws MojoExecutionException
	{
		try
		{
			make();
		}
		catch (IOException e)
		{
			throw new MojoExecutionException(String.format("Error during %s's execution.", QueryBuilderPlugin.class), e);
		}
	}

	private void make() throws IOException
	{
		makeSourceDirectories();
		makeQueryBuilders();
		makeQueryBuilderFactory();
	}

	private void makeSourceDirectories()
	{
		File sourceDirectory = new File(generatedSourceDirectoryName);

		if (!sourceDirectory.exists())
		{
			sourceDirectory.mkdirs();
		}
	}

	private void makeQueryBuilders() throws IOException
	{
		for (Class<?> aClass : classes)
		{
			QueryBuilderGenerator generator = QueryBuilderGenerator.generatorFor(aClass);
			File file = generator.fileIn(generatedSourceDirectoryName);
			file.getParentFile().mkdirs();
			file.createNewFile();

			Writer writer = new FileWriter(file);
			generator.generate(writer);
			writer.close();
		}
	}

	private void makeQueryBuilderFactory() throws IOException
	{
		QueryBuilderFactoryGenerator factoryGenerator = QueryBuilderFactoryGenerator.generatorFor(classes);
		factoryGenerator.setPackage(factoryPackageName);
		File factoryFile = factoryGenerator.fileIn(generatedSourceDirectoryName);
		factoryFile.createNewFile();

		Writer factoryWriter = new FileWriter(factoryFile);
		factoryGenerator.generate(factoryWriter);
		factoryWriter.close();
	}
}
