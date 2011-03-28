package org.effrafax.querybuilder.core.strategy;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.QueryBuilder;

public class SqlStrategy implements Strategy
{

	@Override
	public <T> String build(QueryBuilder<T> queryBuilder)
	{
		return String.format("select * from %s where %s;", tableName(queryBuilder), whereClause(queryBuilder));
	}

	private <T> String tableName(QueryBuilder<T> queryBuilder)
	{
		return queryBuilder.getTargetClass().getSimpleName();
	}

	private <T> String whereClause(QueryBuilder<T> queryBuilder)
	{
		return StringUtils.join(queryBuilder.getPropertyCriteria(), " and ");
	}
}
