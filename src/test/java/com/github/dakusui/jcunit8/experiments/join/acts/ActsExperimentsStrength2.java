package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;
import org.junit.Test;

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
  public void testGenerateAndReportWithConstraintsWithStrength2Factor10Levels16basicPlus() {
    executeSession(specBuilder().constraintComposer(basicPlus()).numFactors(10).numLevels(16).strength(2).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor10Levels8basicPlus() {
    executeSession(specBuilder().constraintComposer(basicPlus()).numFactors(10).numLevels(8).strength(4).build());
  }
}
