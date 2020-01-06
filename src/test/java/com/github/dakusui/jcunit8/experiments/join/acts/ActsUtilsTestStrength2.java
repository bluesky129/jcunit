package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsUtilsTestStrength2 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors10() {
    generateAndReportWithConstraints(10, 2);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors20() {
    generateAndReportWithConstraints(20, 2);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors30() {
    generateAndReportWithConstraints(30, 2);
  }

}
