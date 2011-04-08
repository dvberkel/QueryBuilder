package org.effrafax.querybuilder.core.strategy;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

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
		Collection<String> representations = new ArrayList<String>();
		for (PropertyCriterium<T, ?> propertyCriterium : queryBuilder.getPropertyCriteria())
		{
			representations.add(representationOf(propertyCriterium));
		}
		return StringUtils.join(representations, " and ");
	}

	@Override
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
		if (!(propertyCriterium.getMatchValue() instanceof Long))
		{
			return String.format("'%s'", propertyCriterium.getMatchValue());
		}
		return propertyCriterium.getMatchValue().toString();
	}
}
