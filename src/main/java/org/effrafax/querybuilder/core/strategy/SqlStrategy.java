package org.effrafax.querybuilder.core.strategy;

import static com.google.common.collect.Collections2.transform;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

import com.google.common.base.Function;

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
		return StringUtils.join(representations, " and ");
	}
}

class SqlPropertyCriteriumRepresentation<T> implements Function<PropertyCriterium<T, ?>, String>
{

	@Override
	public String apply(PropertyCriterium<T, ?> propertyCriterium)
	{
		return representationOf(propertyCriterium);
	}

	public String representationOf(PropertyCriterium<T, ?> propertyCriterium)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(representationOfPropertyName(propertyCriterium));
		builder.append(representationOfConnector());
		builder.append(representationOfMatchValue(propertyCriterium));
		return builder.toString();
	}

	private String representationOfPropertyName(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getPropertyName();
	}

	private String representationOfConnector()
	{
		return " = ";
	}

	private String representationOfMatchValue(PropertyCriterium<T, ?> propertyCriterium)
	{
		if (!(propertyCriterium.getMatchValue() instanceof Long))
		{
			return String.format("'%s'", propertyCriterium.getMatchValue());
		}
		return propertyCriterium.getMatchValue().toString();
	}

}