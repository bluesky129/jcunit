package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static com.github.dakusui.jcunit8.extras.generators.ActsUtils.generateAndReport;

public abstract class ActsUtilsTestBase {
  public static Function<List<String>, NormalizedConstraint> createConstraint(int offset) {
    return strings -> ActsUtilsTest.createConstraint(strings.subList(offset, offset + 10));
  }

  void generateAndReportWithConstraints(File baseDir, int numFactors, int strength) {
    Function<List<String>, NormalizedConstraint>[] constraintComposers = createConstraintComposers(numFactors);
    generateAndReport(baseDir, 4, numFactors, strength, constraintComposers);
  }

  private Function<List<String>, NormalizedConstraint>[] createConstraintComposers(int numFactors) {
    List<Function<List<String>, NormalizedConstraint>> constraints = new LinkedList<>();
    for (int i = 0; i < numFactors / 10; i++) {
      constraints.add(createConstraint(i * 10));
    }
    //noinspection unchecked
    return constraints.toArray((Function<List<String>, NormalizedConstraint>[]) new Function[0]);
  }
}
