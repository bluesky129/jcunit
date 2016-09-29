package com.github.dakusui.jcunit.plugins.caengines.ipo2;

import com.github.dakusui.jcunit.core.factor.Factor;
import com.github.dakusui.jcunit.core.factor.Factors;
import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit.core.tuples.TupleUtils;
import com.github.dakusui.jcunit.core.utils.Checks;
import com.github.dakusui.jcunit.core.utils.Utils;
import com.github.dakusui.jcunit.plugins.constraints.ConstraintChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class Ipo {
  public static class Result {
    private final List<Tuple> generatedTuples;
    private final List<Tuple> remainderTuples;

    public Result(List<Tuple> generatedTuples, List<Tuple> remainderTuples) {
      this.generatedTuples = generatedTuples;
      this.remainderTuples = remainderTuples;
    }

    public List<Tuple> getGeneratedTuples() {
      return Collections.unmodifiableList(this.generatedTuples);
    }

    public List<Tuple> getRemainderTuples() {
      return Collections.unmodifiableList(this.remainderTuples);
    }

    Result deduplicate() {
      ////
      // As a result of replacing don't care values, multiple test cases can be identical.
      // By registering all the members to a new temporary set and adding them back to
      // the original one, I'm removing those duplicates.
      LinkedHashSet<Tuple> tmp = new LinkedHashSet<Tuple>(generatedTuples);
      generatedTuples.clear();
      generatedTuples.addAll(tmp);
      return this;
    }
  }

  public static final Object DontCare = new Object() {
    @Override
    public String toString() {
      return "D/C";
    }
  };
  protected final ConstraintChecker constraintChecker;
  protected final Factors           factors;
  protected final int               strength;

  public Ipo(int strength, ConstraintChecker constraintChecker, Factors factors) {
    this.strength = strength;
    this.constraintChecker = constraintChecker;
    this.factors = factors;
  }

  /**
   * This method is responsible for assigning generated generatedTuples to {@code generatedTuples}
   * field and also set of tuples that couldn't be covered to {@code remainderTuples}
   * field.
   */
  public abstract Result ipo();

  protected static List<Tuple> generateAllPossibleTuples(
      List<Factor> factors, Utils.Predicate<Tuple> predicate) {
    Checks.checknotnull(predicate);
    TupleUtils.CartesianTuples initialTestCases = TupleUtils.enumerateCartesianProduct(
        new Tuple.Impl(),
        factors.toArray(new Factor[factors.size()]));
    List<Tuple> ret = new ArrayList<Tuple>((int) initialTestCases.size());
    for (Tuple tuple : initialTestCases) {
      if (predicate.apply(tuple)) {
        ret.add(tuple);
      }
    }
    return ret;
  }
}
