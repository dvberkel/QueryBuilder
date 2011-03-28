package org.effrafax.querybuilder.generated;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.test.Example;
import org.effrafax.querybuilder.test.SubExample;

public class QueryBuilderFactory
{

	public static QueryBuilder<Example> exampleQueryBuilder()
	{
		return QueryBuilder.queryFor(Example.class);
	}

	public static QueryBuilder<SubExample> subExampleQueryBuilder()
	{
		return QueryBuilder.queryFor(SubExample.class);
	}

}
