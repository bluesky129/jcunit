package com.github.dakusui.jcunit8.experiments.join.basic;

import com.github.dakusui.jcunit8.experiments.join.JoinExperiment;
import com.github.dakusui.jcunit8.experiments.join.acts.TestSpec;
import com.github.dakusui.jcunit8.extras.generators.Acts;
import com.github.dakusui.jcunit8.pipeline.Requirement;
import com.github.dakusui.jcunit8.pipeline.stages.Joiner;
import com.github.dakusui.jcunit8.testutils.UTUtils;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecForExperiments;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.function.Function;

@RunWith(Parameterized.class)
public class JoinExperimentBase {
  private final JoinExperiment experiment;

  static JoinExperiment createExperiment(int lhsNumFactors, int rhsNumFactors, int strength, Function<Requirement, Joiner> joinerFactory) {
    UTUtils.createTempDirectory("target/acts");
    return new JoinExperiment.Builder()
        .lhs(new CompatFactorSpaceSpecForExperiments("L").addFactors(2, lhsNumFactors))
        .rhs(new CompatFactorSpaceSpecForExperiments("R").addFactors(2, rhsNumFactors))
        .strength(strength)
        .times(2)
        .joiner(joinerFactory)
        .generator((factorSpace, t) ->
            Acts.generateWithActs(
                new File("target/acts"),
                factorSpace,
                t,
                TestSpec.CHandler.SOLVER.actsName()))
        .verification(false)
        .build();
  }

  JoinExperimentBase(JoinExperiment experiment) {
    this.experiment = experiment;
  }

  @Test
  public void exercise() {
    this.experiment.exercise();
  }

  protected void joinAndPrint() {
    this.experiment.joinAndPrint();
  }
}
