package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecWithConstraints;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;
import org.junit.Test;

import static com.github.dakusui.crest.utils.InternalUtils.requireArgument;
import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.*;

public class ActsExperimentsStrength2 extends ActsExperimentsBase {
  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors10() {
    executeSession(createSpec(10, 2));
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors20() {
    executeSession(createSpec(20, 2));
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors30() {
    executeSession(createSpec(30, 2));
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factors20Incrementally() {
    executeSession(specBuilder()
        .numFactors(20)
        .strength(2)
        .seedSpec(
            new CompatFactorSpaceSpecWithConstraints("p")
                .constraintComposer(basic())
                .addFactors(4, 10))
        .build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor80Levels8_basicPlus() {
    executeSession(specBuilder().constraintComposer(basicPlus()).numLevels(8).numFactors(80).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor20Levels8_basic() {
    executeSession(specBuilder().constraintComposer(basic()).numLevels(8).numFactors(20).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor40Levels8_basic() {
    executeSession(specBuilder().constraintComposer(basic()).numLevels(8).numFactors(40).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor40Levels8_doubleBasic() {
    executeSession(specBuilder().constraintComposer(doubleBasic()).numLevels(8).numFactors(40).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factor10Levels8() {
    executeSession(specBuilder().constraintComposer(basicPlus()).numLevels(8).numFactors(10).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factor10Levels10() {
    executeSession(specBuilder().constraintComposer(basic()).numLevels(10).numFactors(10).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factor12Levels16() {
    executeSession(specBuilder().constraintComposer(basic()).numLevels(12).numFactors(10).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factor10Levels16() {
    executeSession(specBuilder().constraintComposer(basic()).numLevels(16).numFactors(10).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength2Factor20Levels16() {
    executeSession(specBuilder().constraintComposer(basicPlus()).numLevels(16).numFactors(20).strength(2).build());
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
  public void testGenerateAndReportWithConstraintsWithStrength2Factor10Levels16basicPlus() {
    executeSession(specBuilder().constraintComposer(basicPlus()).numFactors(10).numLevels(16).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor10Levels8basicPlus() {
    executeSession(specBuilder().constraintComposer(basicPlus()).numFactors(10).numLevels(8).strength(4).build());
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
    int numLevels = 16;
    int numFactors = 50;
    int strength = 2;
    ConstraintComposer constraintModel = basicPlus();
    executeSession(numLevels, numFactors, constraintModel, strength);
  }

  @Test
  public void exerciseIncrementally() {
    int numLevels = 16;
    int numSeedFactors = 50;
    int numFactors = 100;
    int strength = 2;
    ConstraintComposer constraintModel = basicPlus();
    executeIncrementalSession(numLevels, numSeedFactors, numFactors, constraintModel, strength);
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
