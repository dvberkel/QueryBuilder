package org.effrafax.querybuilder.core.strategy.propertycriterium;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

public class LogPropertyCriteriumRepresentation<T> extends PropertyCriteriumRepresentation<T>
{

	@Override
	protected String representationOfPropertyName(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getPropertyName();
	}

	@Override
	protected String representationOfConnector(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getConnector();
	}

	@Override
	protected String representationOfMatchValue(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getMatchValue().toString();
	}
}
