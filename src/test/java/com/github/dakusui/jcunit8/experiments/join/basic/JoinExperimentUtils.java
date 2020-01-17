package com.github.dakusui.jcunit8.experiments.join.basic;

import com.github.dakusui.jcunit8.experiments.join.JoinExperiment;
import com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer;
import com.github.dakusui.jcunit8.pipeline.stages.Joiner;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;

public enum JoinExperimentUtils {
  ;

  public static void exerciseSession(int numLevels, int numFactors, ConstraintComposer constraintModel, int lhsStrength, int rhsStrength, int strength) {
    exerciseSession(numLevels, numFactors, numFactors, constraintModel, lhsStrength, rhsStrength, strength);
  }

  public static void exerciseSession(int numLevels, int numLhsFactors, int numRhsFactors, ConstraintComposer constraintModel, int strength) {
    exerciseSession(numLevels, numLhsFactors, numRhsFactors, constraintModel, strength, strength, strength);
  }


  static void exerciseSession(int numLevels, int numLhsFactors, int numRhsFactors, ConstraintComposer constraintModel, int lhsStrength, int rhsStrength, int strength) {
    new JoinExperiment.Builder()
        .lhs(new CompatFactorSpaceSpecWithConstraints("L")
                .addFactors(numLevels, numLhsFactors)
                .constraintComposer(constraintModel),
            lhsStrength)
        .rhs(new CompatFactorSpaceSpecWithConstraints("R")
                .addFactors(numLevels, numRhsFactors)
                .constraintComposer(constraintModel),
            rhsStrength)
        .strength(strength)
        .times(1)
        .joiner(Joiner.WeakenProduct::new)
        .generator(JoinExperimentBase.actsGenerator())
        .verification(false)
        .build().exercise();
  }

  public static void exerciseSession(int numLevels, int numFactors, ConstraintComposer constraintModel, int strength) {
    exerciseSession(numLevels, numFactors, constraintModel, strength, strength, strength);
  }
}
