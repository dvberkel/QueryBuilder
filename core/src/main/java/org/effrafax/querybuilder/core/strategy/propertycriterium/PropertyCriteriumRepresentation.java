package org.effrafax.querybuilder.core.strategy.propertycriterium;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

import com.google.common.base.Function;

public abstract class PropertyCriteriumRepresentation<T> implements Function<PropertyCriterium<T, ?>, String>
{
	@Override
	public final String apply(PropertyCriterium<T, ?> propertyCriterium)
	{
		return representationOf(propertyCriterium);
	}

	private String representationOf(PropertyCriterium<T, ?> propertyCriterium)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(representationOfPropertyName(propertyCriterium));
		builder.append(representationOfConnector(propertyCriterium));
		builder.append(representationOfMatchValue(propertyCriterium));
		return builder.toString();
	}

	protected abstract String representationOfPropertyName(PropertyCriterium<T, ?> propertyCriterium);

	protected abstract String representationOfConnector(PropertyCriterium<T, ?> propertyCriterium);

	protected abstract String representationOfMatchValue(PropertyCriterium<T, ?> propertyCriterium);
}