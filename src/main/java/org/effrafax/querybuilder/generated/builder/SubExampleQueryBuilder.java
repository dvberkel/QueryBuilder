package org.effrafax.querybuilder.generated.builder;

import org.effrafax.querybuilder.core.QueryBuilder;
import org.effrafax.querybuilder.core.criteria.CharacterPropertyCriterium;
import org.effrafax.querybuilder.core.criteria.LongPropertyCriterium;
import org.effrafax.querybuilder.core.criteria.PropertyCriterium;
import org.effrafax.querybuilder.core.criteria.StringPropertyCriterium;
import org.effrafax.querybuilder.test.SubExample;

public class SubExampleQueryBuilder extends QueryBuilder<SubExample>
{

	public SubExampleQueryBuilder()
	{
		super(SubExample.class);
	}

	public PropertyCriterium<SubExample, String> name()
	{
		PropertyCriterium<SubExample, String> propertyCriterium = new StringPropertyCriterium<SubExample>("name");
		registerPropertyCriterium(propertyCriterium);
		return propertyCriterium;
	}

	public PropertyCriterium<SubExample, Long> id()
	{
		PropertyCriterium<SubExample, Long> propertyCriterium = new LongPropertyCriterium<SubExample>("id");
		registerPropertyCriterium(propertyCriterium);
		return propertyCriterium;
	}

	public PropertyCriterium<SubExample, Character> character()
	{
		PropertyCriterium<SubExample, Character> propertyCriterium = new CharacterPropertyCriterium<SubExample>(
			"character");
		registerPropertyCriterium(propertyCriterium);
		return propertyCriterium;
	}

}
