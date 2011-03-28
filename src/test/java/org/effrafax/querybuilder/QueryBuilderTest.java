package org.effrafax.querybuilder;

import static org.junit.Assert.assertEquals;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.strategy.SqlStrategy;
import org.effrafax.querybuilder.generated.QueryBuilderFactory;
import org.effrafax.querybuilder.test.Example;
import org.effrafax.querybuilder.test.SubExample;
import org.junit.Test;

public class QueryBuilderTest
{
	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchNamePrecisly()
	{
		QueryBuilder<Example> builder = QueryBuilderFactory.exampleQueryBuilder();

		builder.name().matches("test");

		assertEquals("select * from Example where name = 'test';", builder.buildWith(new SqlStrategy()));
	}

	@Test
	public void aQueryBuilderForASubclassOfExampleShouldBeAbleToMatchNamePrecisly()
	{
		QueryBuilder<SubExample> builder = QueryBuilderFactory.subExampleQueryBuilder();

		builder.name().matches("test");

		assertEquals("select * from SubExample where name = 'test';", builder.buildWith(new SqlStrategy()));
	}

	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchIdPrecisly()
	{
		QueryBuilder<Example> builder = QueryBuilderFactory.exampleQueryBuilder();

		builder.id().matches(0L);

		assertEquals("select * from Example where id = 0;", builder.buildWith(new SqlStrategy()));
	}

	@Test
	public void aQueryBuilderForExampleShouldBeAbleToMatchIdAndNamePrecislySimultanously()
	{
		QueryBuilder<Example> builder = QueryBuilderFactory.exampleQueryBuilder();

		builder.name().matches("test");
		builder.id().matches(0L);

		assertEquals("select * from Example where id = 0 and name = 'test';", builder.buildWith(new SqlStrategy()));
	}

}
