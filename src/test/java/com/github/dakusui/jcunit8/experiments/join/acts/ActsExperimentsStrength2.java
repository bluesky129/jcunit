package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

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

}
