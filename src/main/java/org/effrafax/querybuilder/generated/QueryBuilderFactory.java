package org.effrafax.querybuilder.generated;

import org.effrafax.querybuilder.generated.builder.ExampleQueryBuilder;
import org.effrafax.querybuilder.generated.builder.SubExampleQueryBuilder;

public class QueryBuilderFactory
{

	public static ExampleQueryBuilder exampleQueryBuilder()
	{
		return new ExampleQueryBuilder();
	}

	public static SubExampleQueryBuilder subExampleQueryBuilder()
	{
		return new SubExampleQueryBuilder();
	}

}
