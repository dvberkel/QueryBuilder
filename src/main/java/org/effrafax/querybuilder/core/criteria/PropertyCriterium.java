package org.effrafax.querybuilder.core.criteria;

public class PropertyCriterium<T, U> implements Comparable<PropertyCriterium<T, ?>>
{

	private String propertyName;

	private U matchValue;

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
}
