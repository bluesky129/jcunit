package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecForExperiments;

import java.io.File;
import java.util.Optional;

import static com.github.dakusui.crest.Crest.*;

public interface TestSpec {
  static TestSpec createSpec(File baseDir, int numLevels, int numFactors, int strength, ConstraintComposer composer, CHandler chandler, FactorSpaceSpecForExperiments seedSpec) {
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
      return createSpec(baseDir, numLevels, numFactors, strength, constraintComposer, chandler, seedSpec);
    }

    public Builder chandler(CHandler chandler) {
      this.chandler = chandler;
      return this;
    }

    static Builder create(File baseDir) {
      return new Builder()
          .baseDir(baseDir)
          .numLevels(4)
          .constraintComposer(ConstraintComposer.createConstraintComposer("basic", ActsConstraints::basic))
          .chandler(CHandler.SOLVER);
    }
  }
}
