package org.effrafax.querybuilder.core.strategy;

import static com.google.common.collect.Collections2.transform;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.strategy.propertycriterium.LogPropertyCriteriumRepresentation;

public class LogStrategy implements Strategy
{

	@Override
	public <T> String build(QueryBuilder<T> queryBuilder)
	{
		return String.format("building query for %s: %s", className(queryBuilder), parameters(queryBuilder));
	}

	private <T> String className(QueryBuilder<T> queryBuilder)
	{
		return queryBuilder.getTargetClass().getSimpleName();
	}

	private <T> String parameters(QueryBuilder<T> queryBuilder)
	{
		Collection<String> representations = transform(queryBuilder.getPropertyCriteria(),
			new LogPropertyCriteriumRepresentation<T>());
		return StringUtils.join(representations, ", ");
	}
}