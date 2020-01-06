package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsUtilsTestStrength1 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors10() {
    generateAndReportWithConstraints(10, 1);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors20() {
    generateAndReportWithConstraints(20, 1);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors30() {
    generateAndReportWithConstraints(30, 1);
  }
}
