package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.generators.ActsUtils;
import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class ActsUtilsTestExample extends ActsUtilsTestBase {
  @Test
  @Ignore
  public void testGenerateAndReport() {
    File baseDir = new File("target");
    ActsUtils.generateAndReport(baseDir, 4, 90, 3);
    ActsUtils.generateAndReport(baseDir, 4, 180, 3);
  }

  @Test
  @Ignore
  public void testGenerateAndReportWithConstraints() {
    generateAndReportWithConstraints(baseDir, 10, 2);
    generateAndReportWithConstraints(baseDir, 20, 2);
    generateAndReportWithConstraints(baseDir, 30, 2);
    generateAndReportWithConstraints(baseDir, 40, 2);
    generateAndReportWithConstraints(baseDir, 50, 2);
    generateAndReportWithConstraints(baseDir, 60, 2);
    generateAndReportWithConstraints(baseDir, 70, 2);
    generateAndReportWithConstraints(baseDir, 80, 2);
    generateAndReportWithConstraints(baseDir, 90, 2);
    generateAndReportWithConstraints(baseDir, 100, 2);
  }

  @Test
  @Ignore
  public void testGenerateAndReportWithConstraintsWithStrength3() {
    generateAndReportWithConstraints(baseDir, 10, 3);
    generateAndReportWithConstraints(baseDir, 20, 3);
    generateAndReportWithConstraints(baseDir, 30, 3);
    generateAndReportWithConstraints(baseDir, 40, 3);
  }
}
