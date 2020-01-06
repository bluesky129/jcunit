package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.generators.ActsUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class ActsExperimentsExample extends ActsExperimentsBase {
  @Test
  @Ignore
  public void testGenerateAndReport() {
    File baseDir = new File("target");
    ActsUtils.generateAndReport(baseDir, 4, 90, 3, TestSpec.CHandler.SOLVER);
    ActsUtils.generateAndReport(baseDir, 4, 180, 3, TestSpec.CHandler.SOLVER);
  }

  @Test
  @Ignore
  public void testGenerateAndReportWithConstraints() {
    generateAndReportWithConstraints(10, 2);
    generateAndReportWithConstraints(20, 2);
    generateAndReportWithConstraints(30, 2);
    generateAndReportWithConstraints(40, 2);
    generateAndReportWithConstraints(50, 2);
    generateAndReportWithConstraints(60, 2);
    generateAndReportWithConstraints(70, 2);
    generateAndReportWithConstraints(80, 2);
    generateAndReportWithConstraints(90, 2);
    generateAndReportWithConstraints(100, 2);
  }

  @Test
  @Ignore
  public void testGenerateAndReportWithConstraintsWithStrength3() {
    generateAndReportWithConstraints(10, 3);
    generateAndReportWithConstraints(20, 3);
    generateAndReportWithConstraints(30, 3);
    generateAndReportWithConstraints(40, 3);
  }
}
