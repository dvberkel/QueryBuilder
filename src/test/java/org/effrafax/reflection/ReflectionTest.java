package org.effrafax.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.effrafax.querybuilder.test.Example;
import org.effrafax.querybuilder.test.SubExample;
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

	@Test
	public void shouldReturnFieldsOfParent()
	{
		Class<?> aClass = SubExample.class;

		Field[] fields = aClass.getFields();

		assertTrue(3 == fields.length);
	}

	@Test
	public void aFieldHasAName() throws SecurityException, NoSuchFieldException
	{
		Class<?> aClass = Example.class;

		Field field = aClass.getField("name");

		assertEquals("name", field.getName());
	}

	@Test
	public void aFieldHasAType() throws SecurityException, NoSuchFieldException
	{
		Class<?> aClass = Example.class;

		Field field = aClass.getField("name");

		assertEquals(String.class, field.getType());
	}
}
