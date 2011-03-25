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

	public QueryBuilder(Class<T> targetClass)
	{
		this.targetClass = targetClass;
	}

	public PropertyCriterium<T> name()
	{
		return new PropertyCriterium<T>();
	}

	public String buildWith(Strategy strategy)
	{
		return String.format("select * from %s where name = 'test';", targetClass.getSimpleName());
	}

}
