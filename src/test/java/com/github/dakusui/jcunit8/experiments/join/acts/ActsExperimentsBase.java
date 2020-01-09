package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.extras.generators.Acts;
import com.github.dakusui.jcunit8.extras.generators.ActsUtils;
import com.github.dakusui.jcunit8.extras.normalizer.bak.FactorSpaceSpec;
import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecWithConstraints;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;
import com.github.dakusui.jcunit8.testutils.UTUtils;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CoveringArrayGenerationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.github.dakusui.jcunit.core.utils.IoUtils.streamFile;

public abstract class ActsExperimentsBase {
  private static Logger LOGGER  = LoggerFactory.getLogger(ActsExperimentsBase.class);
  final          File   baseDir = createTempDirectory();

  private File createTempDirectory() {
    return UTUtils.createTempDirectory("target/acts");
  }

  TestSpec.Builder specBuilder() {
    return TestSpec.Builder.create(baseDir);
  }

  TestSpec createSpec(int numFactors, int strength) {
    return specBuilder().numFactors(numFactors).strength(strength).build();
  }

  void executeSession(TestSpec spec) {
    int numLevels = spec.numLevels();
    int numFactors = spec.numFactors();
    int strength = spec.strength();
    List<Tuple> generated;
    File baseDir = spec.baseDir();
    FactorSpaceSpecWithConstraints factorSpaceSpec =
        new CompatFactorSpaceSpecWithConstraints("L").addFactors(numLevels, numFactors);
    if (spec.constraintComposer().isPresent())
      factorSpaceSpec = factorSpaceSpec.constraintComposer(spec.constraintComposer().get());
    FactorSpace factorSpace = factorSpaceSpec.build();
    LOGGER.debug("Directory:{} was created: {}", baseDir, baseDir.mkdirs());
    Acts.Builder actsBuilder = new Acts.Builder().baseDir(baseDir)
        .factorSpace(factorSpace)
        .strength(strength)
        .algorithm("ipog")
        .constraintHandler(spec.chandler().actsName());
    if (spec.seedSpec().isPresent())
      actsBuilder = actsBuilder.seedComposer(headerTuple(factorSpace), spec.seedSpec().get(), strength);
    Acts acts = actsBuilder.build();
    String actsModel = acts.composeActsModel();
    CoveringArrayGenerationUtils.StopWatch stopWatch = new CoveringArrayGenerationUtils.StopWatch();
    acts.run(actsModel);
    List<Tuple> ret = new LinkedList<>();
    try (Stream<String> data = streamFile(Acts.outFile(baseDir)).peek(LOGGER::trace)) {
      ret.addAll(ActsUtils.readTestSuiteFromCsv(data));
    }
    generated = ret;
    System.out.print("model=" + createSignature(spec) + " t=" + strength + " size=" + generated.size() + " time=" + stopWatch.get() + "[msec]");
    if (spec.seedSpec().isPresent())
      System.out.printf("(from seed:'%s')", spec.seedSpec().get().createSignature());
    System.out.println();
  }

  private String createSignature(TestSpec spec) {
    return spec.numLevels() + "^" + spec.numFactors() + "-" + (spec.constraintComposer().map(ConstraintComposer::name).orElse("(none)"));
  }

  private static Tuple headerTuple(FactorSpace factorSpace) {
    Tuple.Builder b = Tuple.builder();
    factorSpace.getFactors().forEach(
        f -> b.put(f.getName(), FactorSpaceSpec.DONT_CARE)
    );
    return b.build();
  }
}