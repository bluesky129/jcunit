package com.github.dakusui.jcunit.generators;

/**
 */

import com.github.dakusui.jcunit.constraint.ConstraintManager;
import com.github.dakusui.jcunit.core.JCUnitConfigurablePlugin;
import com.github.dakusui.jcunit.core.ParamType;
import com.github.dakusui.jcunit.core.factor.Factor;
import com.github.dakusui.jcunit.core.factor.Factors;
import com.github.dakusui.jcunit.core.tuples.Tuple;

import java.util.Iterator;
import java.util.List;

/**
 * Implementations of this interface must guarantee that a public constructor without
 * any parameters exists.
 */
public interface SchemafulTupleGenerator extends
    JCUnitConfigurablePlugin, Iterator<Tuple>,
    Iterable<Tuple> {

  public Factors getFactors();

  public void setFactors(Factors factors);

  public ConstraintManager getConstraintManager();

  public void setConstraintManager(ConstraintManager constraintManager);

  public Class<?> getTargetClass();

  public void setTargetClass(Class<?> klazz);

  /**
   * Returns a tuple which represents a test case identified by {@code testId}
   */
  public Tuple get(long testId);

  /**
   * Returns next valid id.
   */
  public long nextId(long testId);

  /**
   * Returns the first valid id.
   */
  public long firstId();

  /**
   * Returns total number of test cases generated by the implementations of this interface.
   */
  public long size();

}
