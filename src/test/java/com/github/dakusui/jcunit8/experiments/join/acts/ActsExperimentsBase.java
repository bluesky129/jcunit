package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.extras.generators.Acts;
import com.github.dakusui.jcunit8.extras.generators.ActsUtils;
import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecForExperiments;
import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;
import com.github.dakusui.jcunit8.testutils.UTUtils;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecForExperiments;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CoveringArrayGenerationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.dakusui.crest.Crest.*;
import static com.github.dakusui.jcunit.core.utils.ProcessStreamerUtils.streamFile;

public abstract class ActsExperimentsBase {
  private static Logger LOGGER  = LoggerFactory.getLogger(ActsExperimentsBase.class);
  final          File   baseDir = createTempDirectory();

  private File createTempDirectory() {
    return UTUtils.createTempDirectory("target/acts");
  }

  public interface TestSpec {
    enum CHandler {
      NO("no"),
      SOLVER("solver"),
      FORBIDDENTUPLES("forbiddentuples");

      private final String actsName;

      CHandler(String actsName) {
        this.actsName = actsName;
      }

      public String actsName() {
        return this.actsName;
      }
    }

    interface ConstraintComposer extends Function<List<String>, NormalizedConstraint> {
      String name();
    }

    CHandler chandler();

    File baseDir();

    int numFactors();

    int numLevels();

    int strength();

    Optional<ConstraintComposer> constraintComposer();

    Optional<FactorSpaceSpecForExperiments> seedSpec();

    class Builder {
      File               baseDir;
      int                numLevels          = -1;
      int                numFactors         = -1;
      int                strength           = -1;
      ConstraintComposer constraintComposer = null;
      private CHandler                      chandler = null;
      private FactorSpaceSpecForExperiments seedSpec = null;

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

      Builder seedSpec(FactorSpaceSpecForExperiments seedSpec) {
        this.seedSpec = seedSpec;
        return this;
      }

      TestSpec build() {
        assertThat(baseDir, asObject().isNotNull().$());
        assertThat(numFactors, asInteger().ge(0).$());
        assertThat(numLevels, asInteger().ge(0).$());
        assertThat(strength, asInteger().ge(0).$());
        assertThat(chandler, asObject().isNotNull().$());
        return ActsExperimentsBase.createSpec(baseDir, numLevels, numFactors, strength, constraintComposer, chandler, seedSpec);
      }

      public Builder chandler(CHandler chandler) {
        this.chandler = chandler;
        return this;
      }
    }
  }

  TestSpec.Builder specBuilder() {
    return new TestSpec.Builder()
        .baseDir(this.baseDir)
        .numLevels(4)
        .constraintComposer(createConstraintComposer())
        .chandler(constraintHandler());
  }

  TestSpec createSpec(int numFactors, int strength) {
    return createSpec(baseDir, 4, numFactors, strength, createConstraintComposer(), constraintHandler(), null);
  }

  TestSpec createSpec(File baseDir, int numLevels, int numFactors, int strength, TestSpec.CHandler chandler) {
    return createSpec(baseDir, numLevels, numFactors, strength, createConstraintComposer(), chandler, null);
  }

  static TestSpec createSpec(File baseDir, int numLevels, int numFactors, int strength, TestSpec.ConstraintComposer composer, TestSpec.CHandler chandler, FactorSpaceSpecForExperiments seedSpec) {
    return new TestSpec() {
      @Override
      public CHandler chandler() {
        return chandler;
      }

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

      @Override
      public Optional<FactorSpaceSpecForExperiments> seedSpec() {
        return Optional.ofNullable(seedSpec);
      }
    };
  }

  TestSpec.ConstraintComposer createConstraintComposer() {
    return createConstraintComposer("basic", ActsConstraints::basic);
  }

  TestSpec.CHandler constraintHandler() {
    return TestSpec.CHandler.SOLVER;
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
    int numLevels = spec.numLevels();
    int numFactors = spec.numFactors();
    int strength = spec.strength();
    CoveringArrayGenerationUtils.StopWatch stopWatch = new CoveringArrayGenerationUtils.StopWatch();
    List<Tuple> generated;
    File baseDir = spec.baseDir();
    FactorSpaceSpecForExperiments factorSpaceSpec = new CompatFactorSpaceSpecForExperiments("L").addFactors(numLevels, numFactors);
    for (Function<List<String>, NormalizedConstraint> each : createConstraintComposers(spec))
      factorSpaceSpec = factorSpaceSpec.addConstraint(each);
    Optional<TestSpec.ConstraintComposer> constraintComposerOptional = spec.constraintComposer();
    if (constraintComposerOptional.isPresent())
      factorSpaceSpec = factorSpaceSpec.constraintSetName(constraintComposerOptional.get().name());
    FactorSpace factorSpace = factorSpaceSpec.build();
    LOGGER.debug("Directory:{} was created: {}", baseDir, baseDir.mkdirs());
    Acts.Builder actsBuilder = new Acts.Builder().baseDir(baseDir)
        .factorSpace(factorSpace)
        .strength(strength)
        .algorithm("ipog")
        .constraintHandler(spec.chandler().actsName());
    if (spec.seedSpec().isPresent())
      actsBuilder = actsBuilder.seedComposer(spec.seedSpec().get(), strength);
    actsBuilder
        .build()
        .run();
    List<Tuple> ret = new LinkedList<>();
    try (Stream<String> data = streamFile(Acts.outFile(baseDir)).peek(LOGGER::trace)) {
      ret.addAll(ActsUtils.readTestSuiteFromCsv(data));
    }
    generated = ret;
    System.out.println("model=" + numLevels + "^" + numFactors + " t=" + strength + " size=" + generated.size() + " time=" + stopWatch.get() + "[msec]");
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
