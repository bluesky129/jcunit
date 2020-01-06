package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.generators.ActsUtils;
import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;
import com.github.dakusui.jcunit8.testutils.UTUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.dakusui.crest.Crest.*;
import static com.github.dakusui.jcunit8.extras.generators.Acts.readTestSuiteFromCsv;

public class ActsUtilsTest {

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
    File baseDir = UTUtils.createTempDirectory("target/acts");
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
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 10, 3);
    generateAndReportWithConstraints(baseDir, 20, 3);
    generateAndReportWithConstraints(baseDir, 30, 3);
    generateAndReportWithConstraints(baseDir, 40, 3);
  }

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

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors10() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 10, 3);
  }
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors20() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 20, 3);
  }
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors30() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 30, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors40() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 40, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors50() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 50, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor60() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 60, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor70() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 70, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor80() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 80, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor90() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 90, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor100() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 100, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor110() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 110, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor120() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 120, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor130() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 130, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor140() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 140, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor150() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 150, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor160() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 160, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor170() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 170, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor180() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 180, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor190() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 190, 3);
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor200() {
    File baseDir = UTUtils.createTempDirectory("target/acts");
    generateAndReportWithConstraints(baseDir, 200, 3);
  }

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

  @SuppressWarnings("unchecked")
  private void generateAndReportWithConstraints(File baseDir, int numFactors, int strength) {
    List<Function<List<String>, NormalizedConstraint>> constraints = new LinkedList<>();
    for (int i = 0; i < numFactors / 10; i++) {
      constraints.add(ActsUtils.createConstraint(i * 10));
    }
    ActsUtils.generateAndReport(baseDir, 4, numFactors, strength,
        constraints.toArray(new Function[0])
    );
  }


  @Test
  public void testReadTestSuiteFromCsv() {
    assertThat(
        readTestSuiteFromCsv(readCsv()),
        allOf(
            asInteger("size").eq(10).$(),
            asString(call("get", 0).andThen("get", "PREFIX-0").$()).equalTo("0").$(),
            asString(call("get", 9).andThen("get", "PREFIX-9").$()).equalTo("1").$()
        ));
  }

  private static Stream<String> readCsv() {
    return Stream.of(
        "# ACTS Test Suite Generation: Sun Apr 07 17:57:26 JST 2019",
        "#  '*' represents don't care value ",
        "# Degree of interaction coverage: ",
        "# Number of parameters: 10",
        "# Maximum number of values per parameter: 2",
        "# Number ofconfigurations: 10",
        "PREFIX-0,PREFIX-1,PREFIX-2,PREFIX-3,PREFIX-4,PREFIX-5,PREFIX-6,PREFIX-7,PREFIX-8,PREFIX-9",
        "0,0,1,1,1,1,1,1,1,1",
        "0,1,0,0,0,0,0,0,0,0",
        "1,0,0,1,0,1,0,1,0,1",
        "1,1,1,0,1,0,1,0,1,0",
        "1,0,1,0,0,1,0,0,1,1",
        "1,1,0,1,1,0,1,1,0,0",
        "0,0,1,1,1,0,0,0,0,1",
        "1,1,0,0,0,1,1,1,1,0",
        "1,0,0,1,0,1,1,0,0,0",
        "0,1,1,1,1,1,0,1,0,1");
  }

}
