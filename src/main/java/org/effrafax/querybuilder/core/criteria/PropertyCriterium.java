package org.effrafax.querybuilder.core.criteria;

import org.effrafax.querybuilder.core.criteria.connector.Connector;
import org.effrafax.querybuilder.core.criteria.connector.EqualsConnector;
import org.effrafax.querybuilder.core.criteria.connector.LessThenConnector;

public class PropertyCriterium<T, U> implements Comparable<PropertyCriterium<T, ?>>
{

	private String propertyName;

	private U matchValue;

	private Connector connector = new EqualsConnector();

	public PropertyCriterium(String propertyName)
	{
		this.propertyName = propertyName;
	}

	public void matches(U value)
	{
		this.matchValue = value;
	}

	@Override
	public int compareTo(PropertyCriterium<T, ?> o)
	{
		return propertyName.compareTo(o.propertyName);
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public U getMatchValue()
	{
		return matchValue;
	}

	public Connector getConnector()
	{
		return connector;
	}

	public void lessThen(U value)
	{
		this.connector = new LessThenConnector();
		this.matchValue = value;
	}
}
