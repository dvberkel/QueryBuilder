package org.effrafax.querybuilder.core.criteria;

public class StringPropertyCriterium<T> extends PropertyCriterium<T, String>
{

	public StringPropertyCriterium(String propertyName)
	{
		super(propertyName);
	}

	@Override
	protected String representationOfMatchValue()
	{
		return String.format("'%s'", super.representationOfMatchValue());
	}
}
