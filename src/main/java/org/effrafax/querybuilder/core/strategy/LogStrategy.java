package org.effrafax.querybuilder.core.strategy;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

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
		Collection<PropertyCriterium<T, ?>> propertyCriteria = queryBuilder.getPropertyCriteria();
		Collection<String> representations = new ArrayList<String>();
		for (PropertyCriterium<T, ?> propertyCriterium : queryBuilder.getPropertyCriteria())
		{
			representations.add(representationOf(propertyCriterium));
		}
		return StringUtils.join(representations, ", ");
	}

	private <T> String representationOf(PropertyCriterium<T, ?> propertyCriterium)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(representationOfPropertyName(propertyCriterium)).append(" = ")
			.append(representationOfMatchValue(propertyCriterium));
		return builder.toString();
	}

	private <T> String representationOfPropertyName(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getPropertyName();
	}

	private <T> String representationOfMatchValue(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getMatchValue().toString();
	}
}
