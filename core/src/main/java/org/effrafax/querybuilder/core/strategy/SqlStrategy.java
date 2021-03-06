package org.effrafax.querybuilder.core.strategy;

import static com.google.common.collect.Collections2.transform;

import java.util.Collection;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.strategy.propertycriterium.SqlPropertyCriteriumRepresentation;

import com.google.common.base.Joiner;

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
		Collection<String> representations = transform(queryBuilder.getPropertyCriteria(),
			new SqlPropertyCriteriumRepresentation<T>());
		return Joiner.on(" and ").join(representations);
	}
}