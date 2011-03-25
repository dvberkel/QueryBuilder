package org.effrafax.querybuilder;

import static org.junit.Assert.assertEquals;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.strategy.SqlStrategy;
import org.junit.Test;

public class QueryBuilderTest
{
	@Test
	public void aQueryBuilderShouldBeAbleToMatchPrecisly()
	{
		QueryBuilder<Example> builder = QueryBuilder.queryFor(Example.class);

		builder.name().matches("test");

		assertEquals("select * from Example where name = 'test';", builder.buildWith(new SqlStrategy()));
	}
}

class Example
{
	@SuppressWarnings("unused")
	private String name;
}
