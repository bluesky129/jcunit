package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsExperimentsStrength1 extends ActsExperimentsBase {
  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors10() {
    executeSession(createSpec(10, 1));
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors20() {
    executeSession(createSpec(20, 1));
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors30() {
    executeSession(createSpec(30, 1));
  }
}
