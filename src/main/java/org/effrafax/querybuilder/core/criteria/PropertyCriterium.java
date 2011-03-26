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
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(propertyName).append(" = ");
		builder.append(matchValue instanceof String ? "'" : "");
		builder.append(matchValue);
		builder.append(matchValue instanceof String ? "'" : "");
		return builder.toString();
	}

	@Override
	public int compareTo(PropertyCriterium<T, ?> o)
	{
		return propertyName.compareTo(o.propertyName);
	}
}
