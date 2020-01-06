package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;

import java.util.List;

import static com.github.dakusui.jcunit8.experiments.join.acts.ActsUtilsTestBase.*;

public enum ActsConstraints {
  ;

  static NormalizedConstraint doubleBasic(List<String> factorNames) {
    String[] p = factorNames.toArray(new String[0]);
    //noinspection PointlessArithmeticExpression
    return and(
        or(
            ge(p[0], p[1]),
            gt(p[2], p[3]),
            eq(p[4], p[5]),
            gt(p[6], p[7]),
            gt(p[8], p[1])
        ),
        or(
            ge(p[9 - 0], p[9 - 1]),
            gt(p[9 - 2], p[9 - 3]),
            eq(p[9 - 4], p[9 - 5]),
            gt(p[9 - 6], p[9 - 7]),
            gt(p[9 - 8], p[9 - 1])
        )
    );
  }

  static NormalizedConstraint basicPlus(List<String> factorNames) {
    String[] p = factorNames.toArray(new String[0]);
    return and(
        or(
            ge(p[0], p[1]),
            gt(p[2], p[3]),
            eq(p[4], p[5]),
            gt(p[6], p[7]),
            gt(p[8], p[1])
        ),
        or(
            gt(p[9], p[0])
        ),
        or(
            gt(p[8], p[1])
        ),
        or(
            gt(p[7], p[2])
        ),
        or(
            gt(p[6], p[3])
        ),
        or(
            gt(p[5], p[4])
        )
    );
  }

  static NormalizedConstraint basic(List<String> factorNames) {
    String[] p = factorNames.toArray(new String[0]);
    return or(
        ge(p[0], p[1]),
        gt(p[2], p[3]),
        eq(p[4], p[5]),
        gt(p[6], p[7]),
        gt(p[8], p[1]));
  }
}
