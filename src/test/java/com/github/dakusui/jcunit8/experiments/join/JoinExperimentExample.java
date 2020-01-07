package com.github.dakusui.jcunit8.experiments.join;

import com.github.dakusui.jcunit8.pipeline.stages.Joiner;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;
import org.junit.Test;

public class JoinExperimentExample {
  @Test
  public void example1() {
    new JoinExperiment.Builder()
        .lhs(new CompatFactorSpaceSpecWithConstraints("L").addFactors(2, 20))
        .rhs(new CompatFactorSpaceSpecWithConstraints("R").addFactors(2, 20))
        .strength(4)
        .times(10)
        .joiner(Joiner.WeakenProduct::new)
        .verification(false)
        .build().exercise();
  }

  @Test
  public void example1_1() {
    new JoinExperiment.Builder()
        .lhs(new CompatFactorSpaceSpecWithConstraints("L").addFactors(2, 20))
        .rhs(new CompatFactorSpaceSpecWithConstraints("R").addFactors(2, 20))
        .strength(5)
        .times(10)
        .joiner(Joiner.WeakenProduct::new)
        .verification(false)
        .build().exercise();
  }

  @Test
  public void example2() {
    new JoinExperiment.Builder()
        .lhs(new CompatFactorSpaceSpecWithConstraints("L").addFactors(2, 20), 4)
        .rhs(new CompatFactorSpaceSpecWithConstraints("R").addFactors(2, 20), 4)
        .strength(3)
        .times(2)
        .joiner(Joiner.Standard::new)
        .verification(false)
        .build().exercise();
  }
}
