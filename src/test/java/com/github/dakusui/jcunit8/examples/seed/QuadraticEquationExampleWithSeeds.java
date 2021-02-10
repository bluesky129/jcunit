package com.github.dakusui.jcunit8.examples.seed;

import com.github.dakusui.jcunitx.core.tuples.Tuple;
import com.github.dakusui.jcunit8.examples.quadraticequation.QuadraticEquationExample;
import com.github.dakusui.jcunitx.pipeline.Requirement;
import com.github.dakusui.jcunitx.pipeline.stages.ConfigFactory;
import com.github.dakusui.jcunitx.annotations.ConfigureWith;

@ConfigureWith(QuadraticEquationExampleWithSeeds.Config.class)
public class QuadraticEquationExampleWithSeeds extends QuadraticEquationExample {
  public static class Config extends ConfigFactory.Base {
    @Override
    protected Requirement defineRequirement(Requirement.Builder defaultValues) {
      return defaultValues.withNegativeTestGeneration(
          true
      ).addSeed(
          // Positive test
          Tuple.builder().put("a", 1).put("b", -2).put("c", 1).build()
      ).addSeed(
          // Negative test
          Tuple.builder().put("a", 1).put("b", 1).put("c", 1).build()
      ).build();
    }
  }
}
