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
		PropertyCriterium<T, String> propertyCriterium = new PropertyCriterium<T, String>("name");
		this.propertyCriterium = propertyCriterium;
		return propertyCriterium;
	}

	public PropertyCriterium<T, Long> id()
	{
		PropertyCriterium<T, Long> propertyCriterium = new PropertyCriterium<T, Long>("id");
		this.propertyCriterium = propertyCriterium;
		return propertyCriterium;
	}

	public String buildWith(Strategy strategy)
	{
		return String.format("select * from %s where %s;", targetClass.getSimpleName(), propertyCriterium);
	}
}
