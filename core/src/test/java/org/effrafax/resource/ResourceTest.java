package org.effrafax.resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResourceTest
{
	private final String resourceName = "/templates/hello.vm";

	@Test
	public void findResourceOnClassPath()
	{
		assertNotNull(getClass().getResource(resourceName));
	}

	@Test
	public void resolvePathOfResourceOnClassPath()
	{
		assertTrue(getClass().getResource("/templates/hello.vm").getPath().endsWith(resourceName));
	}
}
