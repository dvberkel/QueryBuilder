package org.effrafax.querybuilder.core.strategy;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;

public interface Strategy
{
	public <T> String build(QueryBuilder<T> queryBuilder);

	public <T> String representationOf(PropertyCriterium<T, ?> propertyCriterium);
}
