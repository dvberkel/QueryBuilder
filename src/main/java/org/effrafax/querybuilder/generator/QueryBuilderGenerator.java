package org.effrafax.querybuilder.generator;

import java.io.Writer;

import org.effrafax.querybuilder.test.Example;

public class QueryBuilderGenerator
{

	public static QueryBuilderGenerator generatorFor(Class<Example> aClass)
	{
		return new QueryBuilderGenerator();
	}

	public void generate(Writer writer)
	{

	}

}
