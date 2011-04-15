package org.effrafax.querybuilder.core.strategy.propertycriterium;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

public class SqlPropertyCriteriumRepresentation<T> extends PropertyCriteriumRepresentation<T>
{

	@Override
	protected String representationOfPropertyName(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getPropertyName();
	}

	@Override
	protected String representationOfConnector(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getConnector().toString();
	}

	@Override
	protected String representationOfMatchValue(PropertyCriterium<T, ?> propertyCriterium)
	{
		if (!(propertyCriterium.getMatchValue() instanceof Long))
		{
			return String.format("'%s'", propertyCriterium.getMatchValue());
		}
		return propertyCriterium.getMatchValue().toString();
	}

}
