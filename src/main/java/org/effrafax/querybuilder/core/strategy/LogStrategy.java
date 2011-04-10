package org.effrafax.querybuilder.core.strategy;

import static com.google.common.collect.Collections2.transform;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

import com.google.common.base.Function;

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

class LogPropertyCriteriumRepresentation<T> implements Function<PropertyCriterium<T, ?>, String>
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
		return propertyCriterium.getMatchValue().toString();
	}

}