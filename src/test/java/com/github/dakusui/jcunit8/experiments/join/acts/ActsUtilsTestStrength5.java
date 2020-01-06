package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsUtilsTestStrength5 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor10() {
    executeSession(specBuilder().numFactors(10).strength(5).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor20() {
    executeSession(specBuilder().numFactors(20).strength(5).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor30() {
    executeSession(specBuilder().numFactors(30).strength(5).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor40() {
    executeSession(specBuilder().numFactors(40).strength(5).build());
  }
}
