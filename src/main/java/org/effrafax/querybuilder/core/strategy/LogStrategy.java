package org.effrafax.querybuilder.core.strategy;

import org.effrafax.querybuilder.core.QueryBuilder;

public class LogStrategy implements Strategy
{

	@Override
	public <T> String build(QueryBuilder<T> queryBuilder)
	{
		return "building query for Example: name = test";
	}

}
