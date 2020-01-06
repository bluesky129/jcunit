package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Test;

import java.io.File;
import java.util.stream.Stream;

import static com.github.dakusui.crest.Crest.*;
import static com.github.dakusui.jcunit8.extras.generators.Acts.readTestSuiteFromCsv;

public class ActsUtilsTestStrength1 extends ActsUtilsTestBase {
  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors10() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 10, 1);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors20() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 20, 1);
  }

  @Test
  public void testGenerateAndReportWithConstraintsStrength1Factors30() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 30, 1);
  }
}
