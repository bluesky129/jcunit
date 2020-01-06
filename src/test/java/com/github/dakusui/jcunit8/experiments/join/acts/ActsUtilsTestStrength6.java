package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

import java.io.File;

public class ActsUtilsTestStrength6 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor10() {
    generateAndReportWithConstraints(baseDir, 10, 6);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor20() {
    generateAndReportWithConstraints(baseDir, 20, 6);
  }
}
