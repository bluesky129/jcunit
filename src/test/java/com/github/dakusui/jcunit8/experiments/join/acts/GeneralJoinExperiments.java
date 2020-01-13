package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.experiments.join.JoinReport;
import com.github.dakusui.jcunit8.experiments.join.basic.JoinExperimentUtils;
import org.junit.Test;

import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.*;

public class GeneralJoinExperiments {
  @Test
  public void exercise() {
    System.out.println(JoinReport.header());
    for (int strength : new int[] { 2, 3, 4 })
      for (int numFactors = 10; numFactors <= 10; numFactors += 10)
        for (ConstraintComposer constraintModel : new ConstraintComposer[] { null, basic(), basicPlus(), basicPlusPlus() })
          for (int numLevels : new int[] { 4, 6, 8, 10, 16 })
            if (condition(numLevels, strength, constraintModel, numFactors))
              JoinExperimentUtils.exerciseSession(numLevels, numFactors, constraintModel, strength);
  }

  private static boolean condition(int numLevels, int strength, @SuppressWarnings("unused") ConstraintComposer constraintModel, int numFactors) {
    if (numLevels > 4)
      return strength == 2 && numFactors == 10;
    return true;
  }
}
