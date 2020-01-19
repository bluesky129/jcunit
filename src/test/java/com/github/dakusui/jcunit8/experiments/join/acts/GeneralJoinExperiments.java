package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.experiments.join.JoinReport;
import com.github.dakusui.jcunit8.experiments.join.basic.JoinExperimentUtils;
import org.junit.Before;
import org.junit.Test;

import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.basic;
import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.basicPlus;

public class GeneralJoinExperiments {
  @Before
  public void warmUp() {
    System.out.print("Warming up...");
    for (int strength : new int[] { 2, 3 })
      JoinExperimentUtils.exerciseSession(2, 20, null, strength);
    System.out.println("done");
  }

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
    for (ConstraintComposer constraintModel : new ConstraintComposer[] { null, basic(), basicPlus() })
      for (int strength : new int[] { 2 })
        for (int numFactors = 10; numFactors <= 200; numFactors += 10)
          for (int numLevels : new int[] { 4 })
            if (condition(numLevels, strength, constraintModel, numFactors))
              JoinExperimentUtils.exerciseSession(numLevels, numFactors, constraintModel, strength + 1, strength + 1, strength);
  }

  @Test
  public void exerciseUneven() {
    System.out.println(JoinReport.header());
    for (int numTotalFactors : new int[] { 100, 200 })
      for (int strength : new int[] { 2, 3 })
        for (int numRhsFactors = 10; numRhsFactors <= numTotalFactors - 10; numRhsFactors += 10)
          for (ConstraintComposer constraintModel : new ConstraintComposer[] { basicPlus() })
            for (int numLevels : new int[] { 4 })
              JoinExperimentUtils.exerciseSession(numLevels, numTotalFactors - numRhsFactors, numRhsFactors, constraintModel, strength);
  }

  private static boolean condition(int numLevels, int strength, @SuppressWarnings("unused") ConstraintComposer constraintModel, int numFactors) {
    if (numLevels > 4)
      return strength == 2 && numFactors == 10;
    return true;
  }
}
