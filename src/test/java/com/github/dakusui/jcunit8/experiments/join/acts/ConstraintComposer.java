package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;

import java.util.List;
import java.util.function.Function;

public interface ConstraintComposer extends Function<List<String>, NormalizedConstraint> {
  static ConstraintComposer createConstraintComposer(final String name, final Function<List<String>, NormalizedConstraint> composer) {
    return new ConstraintComposer() {
      @Override
      public String name() {
        return name;
      }

      @Override
      public NormalizedConstraint apply(List<String> factorNames) {
        return composer.apply(factorNames);
      }
    };
  }

  String name();
}
