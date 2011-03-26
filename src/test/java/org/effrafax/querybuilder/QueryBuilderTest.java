package org.effrafax.querybuilder;

import static org.junit.Assert.assertEquals;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.strategy.SqlStrategy;
import org.junit.Test;

public class QueryBuilderTest
{
	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchNamePrecisly()
	{
		QueryBuilder<Example> builder = QueryBuilder.queryFor(Example.class);

		builder.name().matches("test");

		assertEquals("select * from Example where name = 'test';", builder.buildWith(new SqlStrategy()));
	}

	@Test
	public void aQueryBuilderForASubclassOfExampleShouldBeAbleToMatchNamePrecisly()
	{
		QueryBuilder<SubExample> builder = QueryBuilder.queryFor(SubExample.class);

		builder.name().matches("test");

		assertEquals("select * from SubExample where name = 'test';", builder.buildWith(new SqlStrategy()));
	}

	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchIdPrecisly()
	{
		QueryBuilder<Example> builder = QueryBuilder.queryFor(Example.class);

		builder.id().matches(0L);

		assertEquals("select * from Example where id = 0;", builder.buildWith(new SqlStrategy()));
	}

}

class Example
{
	@SuppressWarnings("unused")
	private String name;

	@SuppressWarnings("unused")
	private Long id;
}

class SubExample extends Example
{

}
