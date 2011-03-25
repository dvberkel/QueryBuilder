package org.effrafax.querybuilder.core;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;
import org.effrafax.querybuilder.core.strategy.Strategy;

public class QueryBuilder<T>
{

	public static <T> QueryBuilder<T> queryFor(Class<T> aClass)
	{
		return new QueryBuilder<T>();
	}

	private QueryBuilder()
	{

	}

	public PropertyCriterium<T> name()
	{
		return new PropertyCriterium<T>();
	}

	public String buildWith(Strategy strategy)
	{
		return "select * from Example where name = 'test';";
	}

}
