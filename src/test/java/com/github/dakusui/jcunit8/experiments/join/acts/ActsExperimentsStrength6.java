package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsExperimentsStrength6 extends ActsExperimentsBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor10() {
    executeSession(specBuilder().numFactors(10).strength(6).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor20() {
    executeSession(specBuilder().numFactors(20).strength(6).build());
  }
}
