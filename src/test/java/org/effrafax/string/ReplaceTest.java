package org.effrafax.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReplaceTest
{
	@Test
	public void replacingDotInSlash()
	{
		assertEquals("org/effrafax/example", "org.effrafax.example".replaceAll("\\.", "/"));
	}
}
