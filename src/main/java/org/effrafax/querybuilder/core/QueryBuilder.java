package org.effrafax.querybuilder.core;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

import org.effrafax.querybuilder.core.criteria.PropertyCriterium;
import org.effrafax.querybuilder.core.strategy.Strategy;

public abstract class QueryBuilder<T>
{
	private final Class<T> targetClass;

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
		return strategy.build(this);
	}

	public Class<T> getTargetClass()
	{
		return targetClass;
	}

	public Collection<PropertyCriterium<T, ?>> getPropertyCriteria()
	{
		return Collections.unmodifiableCollection(propertyCriteria);
	}
}
