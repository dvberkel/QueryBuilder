package org.effrafax.querybuilder.core.criteria;


public class CharacterPropertyCriterium<T> extends PropertyCriterium<T, Character>
{

	public CharacterPropertyCriterium(String propertyName)
	{
		super(propertyName);
	}

	@Override
	protected String representationOfMatchValue()
	{
		return String.format("'%s'", super.representationOfMatchValue());
	}
}
