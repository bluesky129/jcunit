package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Test;

import java.io.File;

public class ActsUtilsTestStrength4 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor10() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 10, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor20() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 20, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor30() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 30, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor40() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 40, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor50() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 50, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor60() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 60, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor70() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 70, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor80() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 80, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor90() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 90, 4);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor100() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 100, 4);
  }
}
