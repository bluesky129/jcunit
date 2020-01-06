package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Test;

import java.io.File;

public class ActsUtilsTestStrength2 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors10() {
    generateAndReportWithConstraints(baseDir, 10, 2);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors20() {
    generateAndReportWithConstraints(baseDir, 20, 2);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength2Factors30() {
    generateAndReportWithConstraints(baseDir, 30, 2);
  }

}
