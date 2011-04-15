package org.effrafax.guave;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

public class CollectTest
{
	@Test
	public void collectShouldTransform()
	{
		Collection<Integer> collection = Arrays.asList(new Integer[] { 1, 2, 3, 4 });

		Collection<Integer> result = Collections2.transform(collection, new Square());

		assertTrue(result.contains(1));
		assertTrue(result.contains(4));
		assertTrue(result.contains(9));
		assertTrue(result.contains(16));
		assertTrue(4 == result.size());
	}
}

class Square implements Function<Integer, Integer>
{

	@Override
	public Integer apply(Integer input)
	{
		return input * input;
	}

}
