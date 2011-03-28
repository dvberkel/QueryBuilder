package org.effrafax.querybuilder.core;

import java.util.Collection;
import java.util.PriorityQueue;

import org.apache.commons.lang.StringUtils;
import org.effrafax.querybuilder.core.criteria.LongPropertyCriterium;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;
import org.effrafax.querybuilder.core.criteria.StringPropertyCriterium;
import org.effrafax.querybuilder.core.strategy.Strategy;

public class QueryBuilder<T>
{
	private Class<T> targetClass;

	private Collection<PropertyCriterium<T, ?>> propertyCriteria = new PriorityQueue<PropertyCriterium<T, ?>>();

	public QueryBuilder(Class<T> targetClass)
	{
		this.targetClass = targetClass;
	}

	public PropertyCriterium<T, String> name()
	{
		PropertyCriterium<T, String> propertyCriterium = new StringPropertyCriterium<T>("name");
		registerPropertyCriterium(propertyCriterium);
		return propertyCriterium;
	}

	public PropertyCriterium<T, Long> id()
	{
		PropertyCriterium<T, Long> propertyCriterium = new LongPropertyCriterium<T>("id");
		registerPropertyCriterium(propertyCriterium);
		return propertyCriterium;
	}

	private <U> void registerPropertyCriterium(PropertyCriterium<T, U> propertyCriterium)
	{
		this.propertyCriteria.add(propertyCriterium);
	}

	public String buildWith(Strategy strategy)
	{
		return String.format("select * from %s where %s;", targetClass.getSimpleName(), createWhereClause());
	}

	public String createWhereClause()
	{
		return StringUtils.join(propertyCriteria, " and ");
	}
}
