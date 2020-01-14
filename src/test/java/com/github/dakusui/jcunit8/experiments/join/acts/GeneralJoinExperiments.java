package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.experiments.join.JoinReport;
import com.github.dakusui.jcunit8.experiments.join.basic.JoinExperimentUtils;
import org.junit.Test;

import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.basic;

public class GeneralJoinExperiments {
  @Test
  public void exercise() {
    System.out.println(JoinReport.header());
    for (int strength : new int[] { 2 })
      for (int numFactors = 20; numFactors <= 20; numFactors += 10)
        for (ConstraintComposer constraintModel : new ConstraintComposer[] { basic() })
          for (int numLevels : new int[] { 4 })
            if (condition(numLevels, strength, constraintModel, numFactors))
              JoinExperimentUtils.exerciseSession(numLevels, numFactors, constraintModel, strength);
  }

  @Test
  public void exerciseMixed() {
    System.out.println(JoinReport.header());
    for (int strength : new int[] { 2 })
      for (int numFactors = 130; numFactors <= 200; numFactors += 10)
        for (ConstraintComposer constraintModel : new ConstraintComposer[] { basic() })
          for (int numLevels : new int[] { 4 })
            if (condition(numLevels, strength, constraintModel, numFactors))
              JoinExperimentUtils.exerciseSession(numLevels, numFactors, constraintModel, strength + 1, strength + 1, strength);
  }

  private static boolean condition(int numLevels, int strength, @SuppressWarnings("unused") ConstraintComposer constraintModel, int numFactors) {
    if (numLevels > 4)
      return strength == 2 && numFactors == 10;
    return true;
  }
}
