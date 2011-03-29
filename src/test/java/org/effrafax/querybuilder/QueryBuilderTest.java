package org.effrafax.querybuilder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.strategy.LogStrategy;
import org.effrafax.querybuilder.core.strategy.SqlStrategy;
import org.effrafax.querybuilder.core.strategy.Strategy;
import org.effrafax.querybuilder.generated.QueryBuilderFactory;
import org.effrafax.querybuilder.generated.builder.ExampleQueryBuilder;
import org.effrafax.querybuilder.generated.builder.SubExampleQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QueryBuilderTest
{
	private TestBuilder<?> testBuilder;

	public QueryBuilderTest(TestBuilder<?> testBuilder)
	{
		this.testBuilder = testBuilder;
	}

	@Test
	public void givenWhenThen()
	{
		assertEquals(testBuilder.expectedResult, testBuilder.givenWhen().buildWith(testBuilder.strategy));
	}

	@Parameters
	public static Collection<TestBuilder<?>[]> parameters()
	{
		List<TestBuilder<?>[]> testBuilders = new ArrayList<TestBuilder<?>[]>();
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<ExampleQueryBuilder>(
			"select * from Example where name = 'test';", new SqlStrategy())
		{

			@Override
			public ExampleQueryBuilder givenWhen()
			{
				ExampleQueryBuilder builder = builder();

				builder.name().matches("test");

				return builder;
			}

			@Override
			protected ExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.exampleQueryBuilder();
			}
		} });
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<ExampleQueryBuilder>(
			"building query for Example: name = 'test'", new LogStrategy())
		{

			@Override
			public ExampleQueryBuilder givenWhen()
			{
				ExampleQueryBuilder builder = builder();

				builder.name().matches("test");

				return builder;
			}

			@Override
			protected ExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.exampleQueryBuilder();
			}
		} });
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<SubExampleQueryBuilder>(
			"select * from SubExample where name = 'test';", new SqlStrategy())
		{

			@Override
			public SubExampleQueryBuilder givenWhen()
			{
				SubExampleQueryBuilder builder = builder();

				builder.name().matches("test");

				return builder;
			}

			@Override
			protected SubExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.subExampleQueryBuilder();
			}
		} });
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<SubExampleQueryBuilder>(
			"building query for SubExample: name = 'test'", new LogStrategy())
		{

			@Override
			public SubExampleQueryBuilder givenWhen()
			{
				SubExampleQueryBuilder builder = builder();

				builder.name().matches("test");

				return builder;
			}

			@Override
			protected SubExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.subExampleQueryBuilder();
			}
		} });
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<ExampleQueryBuilder>(
			"select * from Example where id = 0;", new SqlStrategy())
		{

			@Override
			public ExampleQueryBuilder givenWhen()
			{
				ExampleQueryBuilder builder = builder();

				builder.id().matches(0L);

				return builder;
			}

			@Override
			protected ExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.exampleQueryBuilder();
			}
		} });
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<ExampleQueryBuilder>(
			"building query for Example: id = 0", new LogStrategy())
		{

			@Override
			public ExampleQueryBuilder givenWhen()
			{
				ExampleQueryBuilder builder = builder();

				builder.id().matches(0L);

				return builder;
			}

			@Override
			protected ExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.exampleQueryBuilder();
			}
		} });
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<ExampleQueryBuilder>(
			"select * from Example where id = 0 and name = 'test';", new SqlStrategy())
		{

			@Override
			public ExampleQueryBuilder givenWhen()
			{
				ExampleQueryBuilder builder = builder();

				builder.name().matches("test");
				builder.id().matches(0L);

				return builder;
			}

			@Override
			protected ExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.exampleQueryBuilder();
			}
		} });
		testBuilders.add(new TestBuilder<?>[] { new TestBuilder<ExampleQueryBuilder>(
			"building query for Example: id = 0, name = 'test'", new LogStrategy())
		{

			@Override
			public ExampleQueryBuilder givenWhen()
			{
				ExampleQueryBuilder builder = builder();

				builder.name().matches("test");
				builder.id().matches(0L);

				return builder;
			}

			@Override
			protected ExampleQueryBuilder builder()
			{
				return QueryBuilderFactory.exampleQueryBuilder();
			}
		} });

		return testBuilders;

	}
}

abstract class TestBuilder<T extends QueryBuilder<?>>
{
	public final String expectedResult;

	public final Strategy strategy;

	public TestBuilder(String expectedResult, Strategy strategy)
	{
		this.expectedResult = expectedResult;
		this.strategy = strategy;
	}

	protected abstract T builder();

	public abstract T givenWhen();
}

abstract class TestBuilderForExample extends TestBuilder<ExampleQueryBuilder>
{

	public TestBuilderForExample(String expectedResult, Strategy strategy)
	{
		super(expectedResult, strategy);
	}

	@Override
	protected ExampleQueryBuilder builder()
	{
		return QueryBuilderFactory.exampleQueryBuilder();
	}
}