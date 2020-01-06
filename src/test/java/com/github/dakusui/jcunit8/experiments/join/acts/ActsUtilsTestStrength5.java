package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Test;

import java.io.File;

public class ActsUtilsTestStrength5 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor10() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 10, 5);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor20() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 20, 5);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor30() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 30, 5);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength5Factor40() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 40, 5);
  }
}
