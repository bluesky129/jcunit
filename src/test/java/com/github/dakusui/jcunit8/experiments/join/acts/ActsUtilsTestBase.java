package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;
import com.github.dakusui.jcunit8.testutils.UTUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.github.dakusui.crest.Crest.*;
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

    Optional<ConstraintComposer> constraintComposer();

    class Builder {
      File               baseDir;
      int                numLevels          = -1;
      int                numFactors         = -1;
      int                strength           = -1;
      ConstraintComposer constraintComposer = null;

      Builder() {
      }

      Builder baseDir(File baseDir) {
        this.baseDir = baseDir;
        return this;
      }

      Builder numLevels(int numLevels) {
        this.numLevels = numLevels;
        return this;
      }

      Builder numFactors(int numFactors) {
        this.numFactors = numFactors;
        return this;
      }

      Builder strength(int strength) {
        this.strength = strength;
        return this;
      }

      Builder constraintComposer(ConstraintComposer constraintComposer) {
        this.constraintComposer = constraintComposer;
        return this;
      }

      TestSpec build() {
        assertThat(baseDir, asObject().isNotNull().$());
        assertThat(numFactors, asInteger().ge(0).$());
        assertThat(numLevels, asInteger().ge(0).$());
        assertThat(strength, asInteger().ge(0).$());
        return ActsUtilsTestBase.createSpec(baseDir, numLevels, numFactors, strength, constraintComposer);
      }
    }
  }

  TestSpec.Builder specBuilder() {
    return new TestSpec.Builder()
        .baseDir(this.baseDir)
        .numLevels(4)
        .constraintComposer(createConstraintComposer());
  }

  TestSpec createSpec(int numFactors, int strength) {
    return createSpec(baseDir, 4, numFactors, strength);
  }

  TestSpec createSpec(File baseDir, int numLevels, int numFactors, int strength) {
    return createSpec(baseDir, numLevels, numFactors, strength, createConstraintComposer());
  }

  static TestSpec createSpec(File baseDir, int numLevels, int numFactors, int strength, TestSpec.ConstraintComposer composer) {
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
      public Optional<ConstraintComposer> constraintComposer() {
        return Optional.ofNullable(composer);
      }
    };
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

  void generateAndReportWithConstraints(int numFactors, int strength) {
    executeSession(createSpec(numFactors, strength));
  }

  void executeSession(TestSpec spec) {
    generateAndReport(
        spec.baseDir(),
        spec.numLevels(),
        spec.numFactors(),
        spec.strength(),
        createConstraintComposers(spec)
    );
  }

  private Function<List<String>, NormalizedConstraint>[] createConstraintComposers(TestSpec spec) {
    if (spec.constraintComposer().isPresent()) {
      List<Function<List<String>, NormalizedConstraint>> constraints = new LinkedList<>();
      for (int i = 0; i < spec.numFactors() / 10; i++) {
        constraints.add(createConstraint(i * 10, spec.constraintComposer().get()));
      }
      //noinspection unchecked
      return constraints.toArray((Function<List<String>, NormalizedConstraint>[]) new Function[0]);
    } else
      //noinspection unchecked
      return new Function[0];
  }
}
