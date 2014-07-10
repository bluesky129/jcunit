package com.github.dakusui.jcunit.generators;

/**
 */

import com.github.dakusui.jcunit.compat.core.annotations.GeneratorParameters;
import com.github.dakusui.jcunit.core.Tuple;
import com.github.dakusui.jcunit.core.factor.Factor;
import com.github.dakusui.jcunit.core.factor.Factors;

import java.util.Iterator;
import java.util.List;

/**
 * Implementations of this interface must guarantee that a public constructor without
 * any parameters exists.
 */
public interface TestCaseGenerator extends Iterator<Tuple>,
		Iterable<Tuple> {

	/**
	 * <code>null</code> will be returned if undefined key is specified.
	 */
	public Factor getFactor(String factorName);

	/**
	 * Initializes this object.
	 * Users of the implementations of this interface must call this method
	 * after this class is instantiated.
	 * <p/>
	 * Until this method is called, behaviors of any other methods are not predictable.
	 */
	public void init(String[] params,
	                 Factors factors);

	/**
	 * Returns an index of the value for {@code key} in <code>testId</code>.
	 * <p/>
	 * Using the returned value of this method, JCUnit determines which value in {@code key}'s
	 * domain should be used for the test run {@code testId}.
	 * {@code testId} is a long integer which is in {@code [0, s)}, where {@code s} is
	 * the value returned by {@code size} method.
	 * <p/>
	 * The implementation of this method must return an integer in the range of {@code [0, t)},
	 * where t is the returned value of {@code getDomain(key).size()}.
	 *
	 * @param factorName    A key which identifies a parameters used by this object.
	 * @param testId A long integer which identifies a test case generated by this object.
	 */
	public int getIndex(String factorName, long testId);

	public List<String> getFactorNames();

	/**
	 * Returns a tuple which represents a test case identified by {@code testId}
	 */
	public Tuple get(long testId);

	/**
	 * Returns total number of test cases generated by the implementations of this interface.
	 */
	public long size();
}
