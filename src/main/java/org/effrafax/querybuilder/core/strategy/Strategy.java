package org.effrafax.querybuilder.core.strategy;

import org.effrafax.querybuilder.core.QueryBuilder;

public interface Strategy
{
	public <T> String build(QueryBuilder<T> queryBuilder);

}
