package org.effrafax.querybuilder.core.strategy.propertycriterium;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

import com.google.common.base.Function;

public class LogPropertyCriteriumRepresentation<T> implements Function<PropertyCriterium<T, ?>, String>
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
