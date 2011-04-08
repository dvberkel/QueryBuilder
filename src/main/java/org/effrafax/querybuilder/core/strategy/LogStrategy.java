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
		Collection<String> representations = new ArrayList<String>();
		for (PropertyCriterium<T, ?> propertyCriterium : queryBuilder.getPropertyCriteria())
		{
			representations.add(representationOf(propertyCriterium));
		}
		return StringUtils.join(representations, ", ");
	}

	public <T> String representationOf(PropertyCriterium<T, ?> propertyCriterium)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(representationOfPropertyName(propertyCriterium));
		builder.append(representationOfConnector());
		builder.append(representationOfMatchValue(propertyCriterium));
		return builder.toString();
	}

	private <T> String representationOfPropertyName(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getPropertyName();
	}

	private String representationOfConnector()
	{
		return " = ";
	}

	private <T> String representationOfMatchValue(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getMatchValue().toString();
	}
}
