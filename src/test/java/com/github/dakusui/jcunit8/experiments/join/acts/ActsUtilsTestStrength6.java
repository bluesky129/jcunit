package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Test;

import java.io.File;

public class ActsUtilsTestStrength6 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor10() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 10, 6);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength6Factor20() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 20, 6);
  }
}
