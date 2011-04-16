package org.effrafax.querybuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

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
	 * Location of the file.
	 * 
	 * @parameter expression="${querybuilder.generatedSourceDirectory}" default-value="src/generated/java"
	 * @required
	 */
	private File generatedSourceDirectory;

	public void execute() throws MojoExecutionException
	{
		File sourceDirectory = generatedSourceDirectory;

		if (!sourceDirectory.exists())
		{
			sourceDirectory.mkdirs();
		}

		File touch = new File(sourceDirectory, "touch.txt");

		FileWriter w = null;
		try
		{
			w = new FileWriter(touch);

			w.write("touch.txt");
		}
		catch (IOException e)
		{
			throw new MojoExecutionException("Error creating file " + touch, e);
		}
		finally
		{
			if (w != null)
			{
				try
				{
					w.close();
				}
				catch (IOException e)
				{
					// ignore
				}
			}
		}
	}
}
