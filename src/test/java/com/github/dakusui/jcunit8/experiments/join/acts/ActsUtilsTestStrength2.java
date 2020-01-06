package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Test;

import java.io.File;

public class ActsUtilsTestStrength2 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors10() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 10, 2);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors20() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 20, 2);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors30() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 30, 2);
  }

}
