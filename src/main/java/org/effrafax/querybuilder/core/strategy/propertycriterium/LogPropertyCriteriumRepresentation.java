package org.effrafax.querybuilder.core.strategy.propertycriterium;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

public class LogPropertyCriteriumRepresentation<T> extends PropertyCriteriumRepresentation<T>
{

	protected String representationOfPropertyName(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getPropertyName();
	}

	protected String representationOfConnector()
	{
		return " = ";
	}

	protected String representationOfMatchValue(PropertyCriterium<T, ?> propertyCriterium)
	{
		return propertyCriterium.getMatchValue().toString();
	}
}
