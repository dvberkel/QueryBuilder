package org.effrafax.querybuilder;

import static org.junit.Assert.assertEquals;

import org.effrafax.querybuilder.core.strategy.LogStrategy;
import org.effrafax.querybuilder.core.strategy.SqlStrategy;
import org.effrafax.querybuilder.generated.QueryBuilderFactory;
import org.effrafax.querybuilder.generated.builder.ExampleQueryBuilder;
import org.effrafax.querybuilder.generated.builder.SubExampleQueryBuilder;
import org.junit.Test;

public class QueryBuilderTest
{
	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchNamePrecisly()
	{
		ExampleQueryBuilder builder = QueryBuilderFactory.exampleQueryBuilder();

		builder.name().matches("test");

		assertEquals("select * from Example where name = 'test';", builder.buildWith(new SqlStrategy()));
		assertEquals("building query for Example: name = 'test'", builder.buildWith(new LogStrategy()));
	}

	@Test
	public void aQueryBuilderForASubclassOfExampleShouldBeAbleToMatchNamePrecisly()
	{
		SubExampleQueryBuilder builder = QueryBuilderFactory.subExampleQueryBuilder();

		builder.name().matches("test");

		assertEquals("select * from SubExample where name = 'test';", builder.buildWith(new SqlStrategy()));
		assertEquals("building query for SubExample: name = 'test'", builder.buildWith(new LogStrategy()));
	}

	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchIdPrecisly()
	{
		ExampleQueryBuilder builder = QueryBuilderFactory.exampleQueryBuilder();

		builder.id().matches(0L);

		assertEquals("select * from Example where id = 0;", builder.buildWith(new SqlStrategy()));
		assertEquals("building query for Example: id = 0", builder.buildWith(new LogStrategy()));
	}

	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchIdAndNamePrecislySimultanously()
	{
		ExampleQueryBuilder builder = QueryBuilderFactory.exampleQueryBuilder();

		builder.name().matches("test");
		builder.id().matches(0L);

		assertEquals("select * from Example where id = 0 and name = 'test';", builder.buildWith(new SqlStrategy()));
		assertEquals("building query for Example: id = 0, name = 'test'", builder.buildWith(new LogStrategy()));
	}
}
