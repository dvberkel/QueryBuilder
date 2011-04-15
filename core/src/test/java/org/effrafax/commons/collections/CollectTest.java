package org.effrafax.commons.collections;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.junit.Test;

public class CollectTest
{
	@Test
	public void collectShouldTransformACollection()
	{
		List<Integer> collection = Arrays.asList(new Integer[] { 1, 2, 3, 4 });

		@SuppressWarnings("unchecked")
		Collection<Integer> result = CollectionUtils.collect(collection, (Transformer) new Squarer());

		assertEquals(Arrays.asList(new Integer[] { 1, 4, 9, 16 }), result);
	}
}

class Squarer implements Transformer
{

	@Override
	public Object transform(Object input)
	{
		Integer n = (Integer) input;
		return n * n;
	}

}