package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsUtilsTestStrength6 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor10() {
    executeSession(specBuilder().numFactors(10).strength(6).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor20() {
    executeSession(specBuilder().numFactors(10).strength(6).build());
  }
}
