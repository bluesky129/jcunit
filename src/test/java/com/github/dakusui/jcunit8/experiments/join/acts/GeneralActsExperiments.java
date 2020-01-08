package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecWithConstraints;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;
import org.junit.Test;

import static com.github.dakusui.crest.utils.InternalUtils.requireArgument;
import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.*;

public class GeneralActsExperiments extends ActsExperimentsBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factor20Levels16Incrementally() {
    ConstraintComposer constraintModel = basicPlus();
    int numLevels = 16;
    executeSession(
        specBuilder()
            .seedSpec(seedSpec(numLevels, 10, constraintModel))
            .constraintComposer(constraintModel).numLevels(numLevels).numFactors(20).strength(2)
            .build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factor20Levels6basic() {
    executeSession(6, 20, basic(), 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor10Levels6basic() {
    executeSession(6, 10, basic(), 4);
  }

  @Test
  public void exercise() {
    int numLevels = 8;
    int strength = 3;
    ConstraintComposer constraintModel = null;
    for (int numFactors = 20; numFactors <= 200; numFactors += 20)
      executeSession(numLevels, numFactors, constraintModel, strength);
  }

  @Test
  public void exerciseIncrementally() {
    int numLevels = 4;
    int strength = 3;
    ConstraintComposer constraintModel = null;
    for (int numFactors = 20; numFactors <= 200; numFactors += 20)
      executeIncrementalSession(numLevels, numFactors / 2, numFactors, constraintModel, strength);
  }

  void executeSession(int numLevels, int numFactors, ConstraintComposer constraintModel, int strength) {
    executeSession(specBuilder().constraintComposer(constraintModel).strength(strength).numFactors(numFactors).numLevels(numLevels).build());
  }

  void executeIncrementalSession(int numLevels, int numSeedFactors, int numFactors, ConstraintComposer constraintModel, int strength) {
    requireArgument(numFactors, i -> i > numSeedFactors);
    executeSession(specBuilder().seedSpec(seedSpec(numLevels, numSeedFactors, constraintModel))
        .constraintComposer(constraintModel)
        .strength(strength)
        .numFactors(numFactors)
        .numLevels(numLevels)
        .build());
  }

  static FactorSpaceSpecWithConstraints seedSpec(int numLevels, int numFactors, ConstraintComposer constraintModel) {
    return new CompatFactorSpaceSpecWithConstraints("p").addFactors(numLevels, numFactors).constraintComposer(constraintModel);
  }
}
