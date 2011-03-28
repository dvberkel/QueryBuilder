package org.effrafax.querybuilder.core;

import java.util.Collection;
import java.util.PriorityQueue;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;
import org.effrafax.querybuilder.core.strategy.SqlStrategy;
import org.effrafax.querybuilder.core.strategy.Strategy;

public abstract class QueryBuilder<T>
{
	private Class<T> targetClass;

	private Collection<PropertyCriterium<T, ?>> propertyCriteria = new PriorityQueue<PropertyCriterium<T, ?>>();

	public QueryBuilder(Class<T> targetClass)
	{
		this.targetClass = targetClass;
	}

	protected <U> void registerPropertyCriterium(PropertyCriterium<T, U> propertyCriterium)
	{
		this.propertyCriteria.add(propertyCriterium);
	}

	public String buildWith(Strategy strategy)
	{
		if (strategy instanceof SqlStrategy)
		{
			return String.format("select * from %s where %s;", targetClass.getSimpleName(), createWhereClause());
		}
		else
		{
			return "building query for Example: name = test";
		}
	}

	public String createWhereClause()
	{
		return StringUtils.join(propertyCriteria, " and ");
	}
}
