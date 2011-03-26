package org.effrafax.querybuilder.core.criteria;

public class PropertyCriterium<T, U>
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
}
