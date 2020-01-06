package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsUtilsTestStrength5 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor10() {
    generateAndReportWithConstraints(baseDir, 10, 5);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor20() {
    generateAndReportWithConstraints(baseDir, 20, 5);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor30() {
    generateAndReportWithConstraints(baseDir, 30, 5);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor40() {
    generateAndReportWithConstraints(baseDir, 40, 5);
  }
}
