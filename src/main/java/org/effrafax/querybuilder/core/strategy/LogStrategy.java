package org.effrafax.querybuilder.core.strategy;

import org.effrafax.querybuilder.core.QueryBuilder;

public class LogStrategy implements Strategy
{

	@Override
	public <T> String build(QueryBuilder<T> queryBuilder)
	{
		return String.format("building query for %s: name = test", className(queryBuilder));
	}

	private <T> String className(QueryBuilder<T> queryBuilder)
	{
		return queryBuilder.getTargetClass().getSimpleName();
	}

}
