package org.effrafax.querybuilder.reflection;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.effrafax.querybuilder.test.Example;
import org.junit.Test;

public class ReflectionTest
{
	@Test
	public void shouldReturnFields()
	{
		Class<?> aClass = Example.class;

		Field[] fields = aClass.getFields();

		assertTrue(2 == fields.length);
	}
}
