package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecWithConstraints;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;
import org.junit.Before;
import org.junit.Test;

import static com.github.dakusui.crest.utils.InternalUtils.requireArgument;
import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.basic;
import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.basicPlus;

public class GeneralActsExperiments extends ActsExperimentsBase {
  @Before
  public void warmUp() {
    System.out.print("Warming up...");
    for (int strength : new int[] { 2, 3 })
      executeSession(2, 10, null, strength);
    System.out.println("done");
  }

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
    for (ConstraintComposer constraintModel : new ConstraintComposer[] { basic() })
      for (int numFactors = 10; numFactors <= 20; numFactors += 10)
        for (int strength : new int[] { 2 })
          for (int numLevels : new int[] { 4 })
            if (condition(numLevels, numFactors, constraintModel, strength))
              executeSession(numLevels, numFactors, constraintModel, strength);
  }

  @Test
  public void exerciseMixed() {
    for (ConstraintComposer constraintModel : new ConstraintComposer[] { basic() })
      for (int numFactors = 200; numFactors <= 400; numFactors += 20)
        for (int strength : new int[] { -2 })
          for (int numLevels : new int[] { 4 })
            if (condition(numLevels, numFactors, constraintModel, strength))
              executeSession(numLevels, numFactors, constraintModel, strength);
  }

  @Test
  public void exerciseIncrementally() {
    int numTotalFactors = 200;
    for (int strength : new int[] { 2, 3 })
      for (ConstraintComposer constraintModel : new ConstraintComposer[] { null })
        for (int numLevels : new int[] { 4 })
          for (int numFactors = 10; numFactors <= numTotalFactors / 2; numFactors += 10)
            if (condition(numLevels, numFactors, constraintModel, strength))
              executeIncrementalSession(numLevels, numFactors, numTotalFactors - numFactors, constraintModel, strength);
  }

  void executeSession(int numLevels, int numFactors, ConstraintComposer constraintModel, int strength) {
    executeSession(specBuilder().constraintComposer(constraintModel).strength(strength).numFactors(numFactors).numLevels(numLevels).build());
  }

  void executeIncrementalSession(int numLevels, int numSeedFactors, int numFactors, ConstraintComposer constraintModel, int strength) {
    requireArgument(numFactors, i -> i >= numSeedFactors);
    executeSession(specBuilder().seedSpec(seedSpec(numLevels, numSeedFactors, constraintModel))
        .constraintComposer(constraintModel)
        .strength(strength)
        .numFactors(numFactors)
        .numLevels(numLevels)
        .build());
  }

  private static boolean condition(int numLevels, int strength, @SuppressWarnings("unused") ConstraintComposer constraintModel, int numFactors) {
    //    if (numLevels > 4)
    //      return strength == 2 && numFactors <= 20;
    return true;
  }

  static FactorSpaceSpecWithConstraints seedSpec(int numLevels, int numFactors, ConstraintComposer constraintModel) {
    return new CompatFactorSpaceSpecWithConstraints("p").addFactors(numLevels, numFactors).constraintComposer(constraintModel);
  }
}
