package org.effrafax.querybuilder.core;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;
import org.effrafax.querybuilder.core.strategy.Strategy;

public class QueryBuilder<T>
{
	public static <T> QueryBuilder<T> queryFor(Class<T> aClass)
	{
		return new QueryBuilder<T>(aClass);
	}

	private Class<T> targetClass;

	private PropertyCriterium<T, ?> propertyCriterium;

	public QueryBuilder(Class<T> targetClass)
	{
		this.targetClass = targetClass;
	}

	public PropertyCriterium<T, String> name()
	{
		return createAndRegisterPropertyCriteriumFor("name");
	}

	public PropertyCriterium<T, Long> id()
	{
		return createAndRegisterPropertyCriteriumFor("id");
	}

	private <U> PropertyCriterium<T, U> createAndRegisterPropertyCriteriumFor(String propertyName)
	{
		PropertyCriterium<T, U> propertyCriterium = createPropertyCriteriumFor(propertyName);
		registerPropertyCriterium(propertyCriterium);
		return propertyCriterium;
	}

	private <U> PropertyCriterium<T, U> createPropertyCriteriumFor(String propertyName)
	{
		return new PropertyCriterium<T, U>(propertyName);
	}

	private <U> void registerPropertyCriterium(PropertyCriterium<T, U> propertyCriterium)
	{
		this.propertyCriterium = propertyCriterium;
	}

	public String buildWith(Strategy strategy)
	{
		return String.format("select * from %s where %s;", targetClass.getSimpleName(), propertyCriterium);
	}
}
