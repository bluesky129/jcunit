package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;
import com.github.dakusui.jcunit8.testutils.UTUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static com.github.dakusui.jcunit8.extras.generators.ActsUtils.generateAndReport;

public abstract class ActsUtilsTestBase {
  final File baseDir = createTempDirectory();

  private File createTempDirectory() {
    return UTUtils.createTempDirectory("target/acts");
  }

  interface TestSpec {
    interface ConstraintComposer extends Function<List<String>, NormalizedConstraint> {
      String name();
    }

    File baseDir();

    int numFactors();

    int numLevels();

    int strength();

    ConstraintComposer constraintComposer();
  }

  TestSpec createSpec(int numFactors, int strength) {
    return createSpec(4, numFactors, strength);
  }

  TestSpec createSpec(int numLevels, int numFactors, int strength) {
    return createSpec(numLevels, numFactors, strength, createConstraintComposer());
  }

  TestSpec.ConstraintComposer createConstraintComposer() {
    return createConstraintComposer("basic", ActsConstraints::basic);
  }

  static TestSpec.ConstraintComposer createConstraintComposer(final String name, final Function<List<String>, NormalizedConstraint> composer) {
    return new TestSpec.ConstraintComposer() {
      @Override
      public String name() {
        return name;
      }

      @Override
      public NormalizedConstraint apply(List<String> factorNames) {
        return composer.apply(factorNames);
      }
    };
  }

  TestSpec createSpec(int numLevels, int numFactors, int strength, TestSpec.ConstraintComposer composer) {
    return new TestSpec() {
      @Override
      public File baseDir() {
        return baseDir;
      }

      @Override
      public int numFactors() {
        return numFactors;
      }

      @Override
      public int numLevels() {
        return numLevels;
      }

      @Override
      public int strength() {
        return strength;
      }

      @Override
      public ConstraintComposer constraintComposer() {
        return composer;
      }
    };
  }

  public Function<List<String>, NormalizedConstraint> createConstraint(int offset, TestSpec.ConstraintComposer constraintComposer) {
    return strings -> createConstraint(strings.subList(offset, offset + 10), constraintComposer);
  }

  /**
   * <pre>
   *     <Constraints>
   *       <Constraint text="l01 &lt;= l02 || l03 &lt;= l04 || l05 &lt;= l06 || l07&lt;= l08 || l09 &lt;= l02">
   *       <Parameters>
   *         <Parameter name="l01" />
   *         <Parameter name="l02" />
   *         <Parameter name="l03" />
   *         <Parameter name="l04" />
   *         <Parameter name="l05" />
   *         <Parameter name="l06" />
   *         <Parameter name="l07" />
   *         <Parameter name="l08" />
   *         <Parameter name="l09" />
   *         <Parameter name="l02" />
   *       </Parameters>
   *     </Constraint>
   *   </Constraints>
   * </pre>
   * <pre>
   *   p i,1 > p i,2 ∨ p i,3 > p i,4 ∨ p i,5 > p i,6 ∨ p i,7 > p i,8 ∨ p i,9 > p i,2
   * </pre>
   *
   * @param factorNames        A list of factor names.
   * @param constraintComposer A constraint composer, which creates a constraint
   *                           from a list of factor names
   */
  public final NormalizedConstraint createConstraint(List<String> factorNames, TestSpec.ConstraintComposer constraintComposer) {
    return constraintComposer.apply(factorNames);
  }

  void generateAndReportWithConstraints(File baseDir, int numFactors, int strength) {
    TestSpec spec = createSpec(numFactors, strength);
    Function<List<String>, NormalizedConstraint>[] constraintComposers = createConstraintComposers(numFactors, spec);
    generateAndReport(baseDir, 4, numFactors, strength, constraintComposers);
  }

  private Function<List<String>, NormalizedConstraint>[] createConstraintComposers(int numFactors, TestSpec spec) {
    List<Function<List<String>, NormalizedConstraint>> constraints = new LinkedList<>();
    for (int i = 0; i < numFactors / 10; i++) {
      constraints.add(createConstraint(i * 10, spec.constraintComposer()));
    }
    //noinspection unchecked
    return constraints.toArray((Function<List<String>, NormalizedConstraint>[]) new Function[0]);
  }
}
